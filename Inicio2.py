from py4j.clientserver import ClientServer, JavaParameters, PythonParameters
import numpy as np
import pandas as pd;
import logging
import sys
from sklearn.preprocessing import MinMaxScaler
from sklearn.metrics.pairwise import haversine_distances
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import LSTM, Dense
from tensorflow.keras.optimizers import RMSprop, Adam
import tensorflow as tf
import matplotlib.pyplot as plt;


logging.basicConfig(level=logging.DEBUG)




def degrees_to_radians(degrees):
    return degrees * np.pi / 180




def armarEntradaDeDatos(datos):
    entrada = {
            'latitude' : [],
            'longitude' : [],
            'time' : [],
            "latitude_radians" : [],
            "longitude_radians" : [],
            "sen_latitude" : [],
            "cos_latitude" : [],
            "sen_longitude" : [],
            "cos_longitude" : []
    }

    for dato in datos:
        if len(datos) == 25:
            with open("Bucle.txt","a") as file:
                file.write("\n Hola, funciono \n"+str(float(dato.getLatitud())))
        entrada["latitude"].append(float(str(dato.getLatitud())))
        entrada["longitude"].append(float(str(dato.getLongitud())))
        entrada["time"].append(dato.getFecha().toString())
        entrada["latitude_radians"].append(degrees_to_radians(float(str(dato.getLatitud()))))
        entrada["longitude_radians"].append(degrees_to_radians(float(str(dato.getLongitud()))))
        entrada["sen_latitude"].append(np.sin(degrees_to_radians(float(str(dato.getLatitud())))))
        entrada["cos_latitude"].append(np.cos(degrees_to_radians(float(str((dato.getLatitud()))))))
        entrada["sen_longitude"].append(np.sin(degrees_to_radians(float(str((dato.getLongitud()))))))
        entrada["cos_longitude"].append(np.cos(degrees_to_radians(float(str((dato.getLongitud()))))))
    return entrada

def crearYConfigurarDataFrame(data):
    df = pd.DataFrame(data)
    df = df.set_index("time")
    df.sort_index(inplace=True)
    df.drop_duplicates(keep='first',inplace=True,ignore_index=False)
    return df


def crearParticionesDeEntrenamiento(dataframe, train_size = 0.8, validate_size = 0.1, ts_size = 0.1):
        N = dataframe.shape[0]
        NTrain = int(train_size * N)
        NValidate = int(validate_size * N)
        NTest = N - NTrain - NValidate

        train = dataframe[0:NTrain]
        validation = dataframe[NTrain : NTrain+NValidate]
        test = dataframe[NTrain+NValidate:]

        return train,validation,test


def partirDatos(array,input_length,output_length,nroColumnaPredecir):
        '''
        Parte los sets de datos en secuencias que la red LSTM pueda aprender. Devuelve datos de entrada(parametros de entrada)  y de salida(resultados esperados)
        '''
        X, Y = [], []    
        shape = array.shape
        if len(shape)==1:
                fils, cols = array.shape[0], 1
                array = array.reshape(fils,cols)
        else: # Multivariado <-- <--- ¡esta parte de la función se ejecuta en este caso!
                fils, cols = array.shape

        # Generar los arreglos
        for i in range(fils-input_length-output_length):
                X.append(array[i:i+input_length,0:cols])
                # Salida (el índice 1 corresponde a la columna con la variable a predecir)
                Y.append(array[i+input_length:i+input_length+output_length,nroColumnaPredecir].reshape(output_length,1))
        # Convertir listas a arreglos de NumPy
        X = np.array(X)
        Y = np.array(Y)
        return X, Y

def scale_dataset(df,data_input,col_ref,x_tr,x_vl,x_ts,y_tr,y_vl,y_ts):
    '''Escala el dataset en el rango de -1 a 1.'''
    print("Columna de referencia")
    print(col_ref)
    col_ref = df.columns.get_loc(col_ref)
    # Número de instantes de tiempo de entrada y de covariables
    NFEATS = data_input['x_tr'].shape[2]
    # Generar listado con "scalers" (1 por cada covariable de entrada)
    scalers = [MinMaxScaler(feature_range=(-1,1)) for i in range(NFEATS)]
    # Arreglos que contendrán los datasets escalados
    x_tr_s = np.zeros(data_input['x_tr'].shape)
    x_vl_s = np.zeros(data_input['x_vl'].shape)
    x_ts_s = np.zeros(data_input['x_ts'].shape)
    y_tr_s = np.zeros(data_input['y_tr'].shape)
    y_vl_s = np.zeros(data_input['y_vl'].shape)
    y_ts_s = np.zeros(data_input['y_ts'].shape)

    for i in range(NFEATS):
        x_tr_s[:,:,i] = scalers[i].fit_transform(x_tr[:,:,i])
        x_vl_s[:,:,i] = scalers[i].transform(x_vl[:,:,i])
        x_ts_s[:,:,i] = scalers[i].transform(x_ts[:,:,i])
    
    y_tr_s[:,:,0] = scalers[col_ref].fit_transform(y_tr[:,:,0])
    y_vl_s[:,:,0] = scalers[col_ref].transform(y_vl[:,:,0])
    y_ts_s[:,:,0] = scalers[col_ref].transform(y_ts[:,:,0])
 
    data_scaled = {
        'x_tr_s': x_tr_s, 'y_tr_s': y_tr_s,
        'x_vl_s': x_vl_s, 'y_vl_s': y_vl_s,
        'x_ts_s': x_ts_s, 'y_ts_s': y_ts_s,
    }
    return data_scaled, scalers[col_ref]



def scale(df,col_ref,x_tr,x_vl,x_ts,y_tr,y_vl,y_ts):
    #Diccionario de entrada
    data_in = {
        'x_tr': x_tr, 'y_tr': y_tr,
        'x_vl': x_vl, 'y_vl': y_vl,
        'x_ts': x_ts, 'y_ts': y_ts,
    }

    return scale_dataset(df,data_in, col_ref,x_tr,x_vl,x_ts,y_tr,y_vl,y_ts)


def root_mean_squared_error(y_true, y_pred):
    '''Pérdida: se usará el RMSE (root mean squared error) para el entrenamiento'''
    rmse = tf.math.sqrt(tf.math.reduce_mean(tf.square(y_pred-y_true)))
    return rmse

def entrenarModelo(N_UNITS,OUTPUT_LENGTH,EPOCHS,BATCH_SIZE,scaledDataset):
    # Ajustar parámetros para reproducibilidad del entrenamiento
    tf.random.set_seed(123)
    tf.config.experimental.enable_op_determinism()
    # El modelo
    #N_UNITS = 128 # Tamaño del estado oculto (h) y de la celdad de memoria (c) (128)
    INPUT_SHAPE = (scaledDataset["x_tr_s"].shape[1], scaledDataset["x_tr_s"].shape[2]) # 24 (horas) x 13 (features)
    modelo = Sequential()
    modelo.add(LSTM(N_UNITS, input_shape=INPUT_SHAPE))
    # Y lo único que cambia con respecto al modelo multivariado + uni-step es
    # el tamaño deldato de salida
    modelo.add(Dense(OUTPUT_LENGTH, activation='linear')) # activation = 'linear' pues queremos pronosticar (regresión)
    # Compilación
    optimizador = RMSprop(learning_rate=5e-4) # 5e-5
    modelo.compile(
        optimizer = optimizador,
        loss = root_mean_squared_error,
    )
    # Entrenamiento (aproximadamente 1 min usando GPU)
    historia = modelo.fit(
        x = scaledDataset["x_tr_s"],
        y = scaledDataset["y_tr_s"],
        batch_size = BATCH_SIZE,
        epochs = EPOCHS,
        validation_data = (scaledDataset["x_vl_s"],scaledDataset["y_vl_s"]),
        verbose=2
    )

    args = sys.argv[1:]
    plt.plot(historia.history['loss'],label='RMSE train')
    plt.plot(historia.history['val_loss'],label='RMSE val')
    plt.xlabel('Iteración')
    plt.ylabel('RMSE')
    plt.legend();
    plt.savefig("curva"+args[len(args)-1]+".jpg")

    return modelo

def predecirConModelo(x, model, scaler,verboseindeX):
    '''Genera la predicción de OUTPUT_LENGTH instantes
    de tiempo a futuro con el modelo entrenado.
    Entrada:
    - x: batch (o batches) de datos para ingresar al modelo
      (tamaño: BATCHES X INPUT_LENGTH X FEATURES)
    - model: Red LSTM entrenada
    - scaler: escalador (requerido para llevar la predicción a la escala original)
    Salida:
    - y_pred: la predicción en la escala original (tamaño: BATCHES X OUTPUT_LENGTH X FEATURES)
    '''
    # Calcular predicción escalada en el rango de -1 a 1
    y_pred_s = model.predict(x,verbose=verboseindeX)
    # Llevar la predicción a la escala original
    y_pred = scaler.inverse_transform(y_pred_s)
    return y_pred.flatten()

def escalarDatosPrediccion(df,data_input,col_ref,entrada,salidaEsperada):
        '''Escala el dataset en el rango de -1 a 1.'''
        print("Columna de referencia")
        print(col_ref)
        col_ref = df.columns.get_loc(col_ref)
        # Número de instantes de tiempo de entrada y de covariables
        NFEATS = data_input['entrada'].shape[2]
        # Generar listado con "scalers" (1 por cada covariable de entrada)
        scalers = [MinMaxScaler(feature_range=(-1,1)) for i in range(NFEATS)]
        # Arreglos que contendrán los datasets escalados
        entradaConCeros = np.zeros(data_input['entrada'].shape)
        salidaEsperadaConCeros = np.zeros(data_input['salida'].shape)

        for i in range(NFEATS):
            entradaConCeros[:,:,i] = scalers[i].fit_transform(entrada[:,:,0])
        
   
        salidaEsperadaConCeros[:,:,0] = scalers[col_ref].fit_transform(salidaEsperada[:,:,0])
    
        data_scaled = {
            'entrada': entradaConCeros,
            'salida': salidaEsperadaConCeros,
        }
        return data_scaled, scalers[col_ref]

def armarDataSetEntrada(df,col_ref,entrada,salidaEsperada):
    data_input = {
            'entrada': entrada,
            'salida': salidaEsperada, 
        }

    return escalarDatosPrediccion(df,data_input,col_ref,entrada,salidaEsperada)

def distanciaSuperior(punto):

    DISTANCIA_PERMITIDA = 3.00

    with open('LogPredecir.txt',"a") as file:
                file.write("\n\n"+str(round(punto, 5)))
                file.write("\n\n"+str(DISTANCIA_PERMITIDA))
                file.write("\n\n"+str(round(punto, 5)>DISTANCIA_PERMITIDA))

    return round(punto, 5)>DISTANCIA_PERMITIDA

def predecir(x, model, scaler,verboseindeX):
    '''Genera la predicción de OUTPUT_LENGTH instantes
    de tiempo a futuro con el modelo entrenado.
    Entrada:
    - x: batch (o batches) de datos para ingresar al modelo
      (tamaño: BATCHES X INPUT_LENGTH X FEATURES)
    - model: Red LSTM entrenada
    - scaler: escalador (requerido para llevar la predicción a la escala original)
    Salida:
    - y_pred: la predicción en la escala original (tamaño: BATCHES X OUTPUT_LENGTH X FEATURES)
    '''
    # Calcular predicción escalada en el rango de -1 a 1
    y_pred_s = model.predict(x,verbose=verboseindeX)
    # Llevar la predicción a la escala original
    y_pred = scaler.inverse_transform(y_pred_s)
    return y_pred.flatten()

class EntryPoint:

    def __init__(self):
        self.model1 = ""
        self.scale1 = ""
        self.model2 = ""
        self.scale2 = ""
        self.modelosLatitud = []
        self.modelosLongitud = []
        self.scalerLatitud = []
        self.scalerLongitud = []

    def entrenar(self,ubicaciones):
        args = sys.argv[1:]
        datosArmados = armarEntradaDeDatos(ubicaciones)
        df = crearYConfigurarDataFrame(datosArmados)
        tr, vl, ts  = crearParticionesDeEntrenamiento(df)
        xtrainLatitude , ytrainLatitude = partirDatos(tr.values,int(args[0]),int(args[1]),0)
        xvalidationLatitude , yvalidationLatitude= partirDatos(vl.values,int(args[0]),int(args[1]),0)
        xtestLatitude, ytestLatitude= partirDatos(ts.values,int(args[0]),int(args[1]),0)
        xtrainLongitude , ytrainLongitude= partirDatos(tr.values,int(args[0]),int(args[1]),1)
        xvalidationLongitude , yvalidationLongitude= partirDatos(vl.values,int(args[0]),int(args[1]),1)
        xtestLongitude , ytestLongitude= partirDatos(ts.values,int(args[0]),int(args[1]),1)
        scaledDataset1, scale1 = scale(df,"latitude",xtrainLatitude,xvalidationLatitude,xtestLatitude,ytrainLatitude,yvalidationLatitude,ytestLatitude)
        scaledDataset2, scale2 = scale(df,"longitude",xtrainLongitude,xvalidationLongitude,xtestLongitude,ytrainLongitude,yvalidationLongitude,ytestLongitude)
        modeloLatitude = entrenarModelo(int(args[3]),int(args[1]),int(args[4]),int(args[5]),scaledDataset1)
        modeloLongitude = entrenarModelo(int(args[3]),int(args[1]),int(args[4]),int(args[5]),scaledDataset2)
        prediccionLatitud = predecirConModelo(scaledDataset1["x_ts_s"],modeloLatitude,scale1,0)
        prediccionLongitud = predecirConModelo(scaledDataset2["x_ts_s"],modeloLongitude,scale2,0)

        with open("datos.txt","a") as file:
            file.write("\n Hola, funciono \n"+str(args))
            file.write("\n Hola, funciono \n"+str(df.head()))
            file.write("\n Hola, funciono \n"+str(tr.head()))
            file.write("\n Hola, funciono \n"+str(vl.head()))
            file.write("\n Hola, funciono \n"+str(ts.head()))
            file.write("\n Hola, funciono \n"+str(xtrainLatitude))
            file.write("\n Hola, funciono \n"+str(ytrainLatitude))
            file.write("\n Hola, funciono \n"+str(xvalidationLatitude))
            file.write("\n Hola, funciono \n"+str(yvalidationLatitude))
            file.write("\n Hola, funciono \n"+str(xtestLatitude))
            file.write("\n Hola, funciono \n"+str(ytestLatitude))
            file.write("\n Hola, funciono \n"+str(scaledDataset1))
            file.write("\n Hola, funciono \n"+str(scaledDataset2))
            file.write("\n Hola, funciono \n"+str(prediccionLatitud))
            file.write("\n Hola, funciono \n"+str(prediccionLongitud))

        # self.modeloLatitud = modeloLatitude
        # self.modeloLongitud = modeloLongitude
        # self.scale1 = scale1
        # self.scale2 = scale2
        self.modelosLatitud.append(modeloLatitude)
        self.modelosLongitud.append(modeloLongitude)
        self.scalerLatitud.append(scale1)
        self.scalerLongitud.append(scale2)

        return len(self.modelosLatitud)

    def predecir(self,ubicacionesPersonaReal,indice):
        args = sys.argv[1:]
        datosEntrada = armarEntradaDeDatos(ubicacionesPersonaReal)
        with open("LogPredecir.txt","a") as file:
            file.write("\n Hola, funciono \n"+str(datosEntrada))
            file.write("\n Hola, funciono \n"+str(ubicacionesPersonaReal))
            file.write("\n Hola, funciono \n"+str(ubicacionesPersonaReal.size()))
        df = crearYConfigurarDataFrame(datosEntrada)
        entradaLatitud, salidaLatitud= partirDatos(df.values,int(args[0]),int(args[1]),0)
        entradaLongitude , salidaLongitude= partirDatos(df.values,int(args[0]),int(args[1]),1)

        datosEscaladosLatitud , scalerLat = armarDataSetEntrada(df,"latitude",entradaLatitud,salidaLatitud)
        datosEscaladosLongitud , scalerLong= armarDataSetEntrada(df,"longitude",entradaLongitude,salidaLongitude)

        prediccionLatitud = predecir(datosEscaladosLatitud["entrada"],self.modelosLatitud[indice],self.scalerLatitud[indice],0)
        prediccionLongitud= predecir(datosEscaladosLongitud["entrada"],self.modelosLongitud[indice],self.scalerLongitud[indice],0)

        with open("LogPredecir.txt","a") as file:
            file.write("\n\n\nPrediccion latitud -------------------------------- \n\n\n"+str(prediccionLatitud))
            file.write("\n\n\nprediccionLongitud ---------------------------------\n\n\n"+str(prediccionLongitud))
            file.write("\n\n\nDatos de entrada -----------------------------------\n\n\n"+str(df))

        diferenciaLat = salidaLatitud.flatten() - prediccionLatitud
        difereniciaLong = salidaLongitude.flatten() - prediccionLongitud

        latPredictCoordiantesRad = [degrees_to_radians(_) for _ in prediccionLatitud]
        longPredictCoordinatesRad = [degrees_to_radians(_) for _ in prediccionLongitud]

        latTestCoordinateRad = [degrees_to_radians(_) for _ in salidaLatitud.flatten()]
        longTestCoordinateRad = [degrees_to_radians(_) for _ in salidaLongitude.flatten()]

        with open("LogPredecir.txt","a") as file:
            file.write("\n Hola, funciono \n"+str(latPredictCoordiantesRad))
            file.write("\n Hola, funciono \n"+str(longTestCoordinateRad))

        for i in range(len(latPredictCoordiantesRad)):
                coordinateOne = np.array([latPredictCoordiantesRad[i],longPredictCoordinatesRad[i]])
                coordinateTwo = np.array([latTestCoordinateRad[i],longTestCoordinateRad[i]])
                with open("LogPredecir.txt","a") as file:
                    file.write("\n Hola, funciono \n"+str(coordinateOne))
                    file.write("\n Hola, funciono \n"+str(coordinateTwo))
                result = haversine_distances([coordinateOne, coordinateTwo])
                resultadoMul = result * 6371000/1000  # multiply by Earth radius to get kilometers
                if distanciaSuperior(resultadoMul[0][1]):
                    return True 
        
        return False

    class Java:
        implements = ["vgd.model.rutinas.PythonMethods"]







def main():
    print("Hola soy el mensaje de ejecucion que deberia aparecer","asdsadasdsad")
    with open("nuevoArchivooooooooooo.txt","a") as file:
         file.write("\n Hola, funciono \n")
    
    simple_hello = EntryPoint()
    gateway = ClientServer(
    java_parameters=JavaParameters(),
    python_parameters=PythonParameters(),
    python_server_entry_point=simple_hello)
    print(f"Hola soy el mensaje de ejecucion que deberia aparecer x2")

if __name__ == "__main__":
    main()




