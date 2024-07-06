package vdg.model.rutinas;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import py4j.ClientServer;
import py4j.GatewayServer;
import py4j.Py4JNetworkException;
import vdg.model.domain.ConfiguracionLSTM;
import vdg.model.domain.CoordenadasPersona;
import vdg.model.domain.Ubicacion;
import vdg.model.domain.UbicacionesEntrenamiento;
import vdg.model.notificacionesTerceros.TelegramNotificador;

@Component
public class IniciarScript {

    @Value("${interprete}")
    String interprete;
    @Value("${path}")
    String path;
    List<ProcessBuilder> procesosBuilder; 
    List<Process> procesosActivos; 
    List<Integer> indices;

    public IniciarScript() {
        this.procesosBuilder = new ArrayList<ProcessBuilder>();
        this.procesosActivos = new ArrayList<Process>();
        this.indices = new ArrayList<Integer>();
    }

    public ProcessBuilder crearProceso(ConfiguracionLSTM config){
    	
    	if(procesosBuilder.isEmpty()==false) return procesosBuilder.get(0);
    	
        System.out.println(this.interprete);
        System.out.println("inicio2.py");
        List<String> command = new ArrayList<>();
        command.add(this.interprete);
        command.add("inicio2.py");
        command.add(config.getInput_length()+"");
        command.add(config.getOutput()+"");
        command.add(config.getDistanciaPermitida()+"");
        command.add(config.getNunits()+"");
        command.add(config.getEpochs()+"");
        command.add(config.getBatch_size()+"");
        command.add(config.getInput_length()+""+config.getOutput()+""+config.getDistanciaPermitida()+""+config.getEpochs());
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        this.procesosBuilder.add(processBuilder);
        return processBuilder;
    }

    public void iniciarProceso() throws Exception{
    	
    	System.out.println("Estoy por iniciar el proceso --------------------------------------------------------");
    	
        if(this.procesosBuilder.size() == 0) throw new Exception("No hay procesos para iniciar, por favor agregue una nuevo identificador");
        
        
        
        if(this.procesosActivos.isEmpty()==false) return;
        
        
        System.out.println("Pase las validaciones --------------------------------------------------------------");
        
        ProcessBuilder processBuilder = this.procesosBuilder.get(this.procesosBuilder.size() == 1 ? 0 : this.procesosBuilder.size() - 1);
        processBuilder.redirectOutput(new File("outputNuevo.log" + LocalDate.now().toString()));
        processBuilder.redirectError(new File("error.log" + LocalDate.now().toString()));

        try {
        	
        	System.out.println("Entre a armar el proceso --------------------------------------------------------------");
            Process process = processBuilder.start();
            this.procesosActivos.add(process);
            System.out.println("El script de Python se está ejecutando en segundo plano. La salida se está redirigiendo a output.log.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void entrenar(List<UbicacionesEntrenamiento> ubicaciones) {
    	System.out.println("Proceso---------------------------------------------------------------------");
    	System.out.println(this.procesosActivos.isEmpty());
    	System.out.println(this.procesosActivos.get(0));
    	 ClientServer clientServer = new ClientServer(null);
         PythonMethods hello = (PythonMethods) clientServer.getPythonServerEntryPoint(new Class[] { PythonMethods.class });
         try { 
        	 System.out.println("SOY LA LISTA DE ENTRENAMIENTO--------------------------------------------------------------------------------------------");
        	 this.indices.add(hello.entrenar(ubicaciones));
            
         } catch (Exception e) {
             e.printStackTrace();
         }
         clientServer.shutdown();
  }
    

    public void predecir(List<CoordenadasPersona> ubicaciones,TelegramNotificador telegram) {
    	System.out.println("Proceso---------------------------------------------------------------------");
    	System.out.println(this.procesosActivos.get(0));
    	
        ClientServer clientServer = new ClientServer(null);
        PythonMethods modelo = (PythonMethods) clientServer.getPythonServerEntryPoint(new Class[] { PythonMethods.class });
   
        this.indices.forEach(indiceModelo ->{
        	try {
        	Boolean resultado = modelo.predecir(ubicaciones,indiceModelo-1);
        	System.out.println("El resultado de la prediccion es "+ resultado+ " --------------------------------------------------");
        	if (resultado) {
        		telegram.enviarMensaje((long) 770684292, "Alerta el agresor a abandonado su rutina normal");
        	}}
        	catch(Exception e) {
        		clientServer.shutdown();
        		System.out.println(e);
        	}
        	
        });        
        clientServer.shutdown();
    }
    
    public Boolean procesoActivo() {
  	  return this.procesosActivos.size()>0;
    }
}