package vdg.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.domain.EstadoNotificacion;
import vdg.model.domain.EstadoPruebaDeVida;
import vdg.model.domain.FotoIdentificacion;
import vdg.model.domain.FotoPruebaDeVida;
import vdg.model.domain.FotoPruebaDeVida2;
import vdg.model.domain.Notificacion;
import vdg.repository.FotoIdentificacionRepository;
import vdg.repository.FotoPruebaDeVidaRepository;
import vdg.repository.NotificacionRepository;
import vdg.repository.PruebaDeVidaRepository;

@RestController
@RequestMapping("/FotoPruebaDeVida")
@CrossOrigin
public class FotoPruebaDeVidaController {

	@Autowired
	private FotoPruebaDeVidaRepository fotoPruebaDeVidaRepo;
	@Autowired
	private NotificacionRepository notificacionRepo;
	@Autowired
	private PruebaDeVidaRepository pruebaDeVidaRepo;
	@Autowired
	private FotoIdentificacionRepository fotoIdentificacionRepo;


	@GetMapping("/getFotoPruebaDeVida/{idPruebaDeVida}")
	public FotoPruebaDeVida2 getProbando(@PathVariable("idPruebaDeVida") int idPruebaDeVida) {
		FotoPruebaDeVida foto = fotoPruebaDeVidaRepo.findByIdPruebaDeVida(idPruebaDeVida);
		FotoPruebaDeVida2 foto2 = new FotoPruebaDeVida2();
		if (foto == null)
			return null;
		foto2.setFoto("data:image/jpg;base64," + Base64.getEncoder().encodeToString(foto.getFoto()));
		foto2.setIdFoto(foto.getIdFoto());
		foto2.setIdPruebaDeVida(idPruebaDeVida);
		return foto2;
	}

	@PostMapping("/{idPruebaDeVida}")
	public Map<String, Object> agregarFotoPruebaDeVida(
			@PathVariable("idPruebaDeVida") int idPruebaDeVida,
			@RequestBody Map<String, Object> requestBody) {

		String foto = (String) requestBody.get("foto");
		String accionRealizada = (String) requestBody.get("accionRealizada");
		Integer idUsuario = (Integer) requestBody.get("idUsuario");
		Integer idPersona = (Integer) requestBody.get("idPersona");

		// Decodificar la foto desde Base64
		byte[] decodedByte = Base64.getDecoder().decode(foto.split(",")[1]);

		// Crear y guardar la nueva instancia de FotoPruebaDeVida
		FotoPruebaDeVida fotoPruebaDeVida = new FotoPruebaDeVida();
		fotoPruebaDeVida.setIdPruebaDeVida(idPruebaDeVida);
		fotoPruebaDeVida.setFoto(decodedByte);
		fotoPruebaDeVida.setAccionRealizada(accionRealizada);

		fotoPruebaDeVidaRepo.save(fotoPruebaDeVida);

		Map<String, Object> result = validarRostro(idPruebaDeVida,idPersona);

		// Acceder al valor asociado a la clave "data"
		Map<String, Object> dataMap = (Map<String, Object>) result.get("data");

		System.out.println("Resultado de validación: " + result.toString());
		System.out.println("Data map: " + dataMap);
		Notificacion notificacion = new Notificacion();	
		notificacion.setIdUsuario(idUsuario);
		notificacion.setFecha(new Timestamp(new Date().getTime()));

		if (dataMap != null && Boolean.TRUE.equals(result.get("success"))) {
			// Iterar sobre el mapa data y mostrar sus claves y valores
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();

				// Usar equals para comparar cadenas											
				if (accionRealizada.equals(key) && "true".equalsIgnoreCase(value.toString())) {     							
					notificacion.setAsunto("Prueba de vida realizada correctamente");
					notificacion.setDescripcion("Se aprobó la validación automática de la prueba de vida");
					notificacion.setEstado(EstadoNotificacion.NoVista);
					notificacionRepo.save(notificacion);
					pruebaDeVidaRepo.updateEstado(idPruebaDeVida, EstadoPruebaDeVida.AceptadaAutomaticamente);
					System.out.println("Notificación enviada: " + notificacion.toString());
					System.out.println("Clave: " + key + ", Valor: " + value);
				} 
				if (accionRealizada.equals(key) && "false".equalsIgnoreCase(value.toString())) {
					System.out.println("Valor no es true. Clave: " + key + ", Valor: " + value);
					notificacion.setAsunto("Prueba de vida realizada incorrectamente");
					notificacion.setDescripcion("Se rechazo la validación automática de la prueba de vida por no realizar correctamente la expresión");
					notificacion.setEstado(EstadoNotificacion.NoVista);
					notificacionRepo.save(notificacion);
					pruebaDeVidaRepo.updateEstado(idPruebaDeVida, EstadoPruebaDeVida.RechazadaAutomaticamente);
				}
			}
		} else {
			System.out.println("No se encontró la clave 'data' en el mapa result o 'success' es false.");
			notificacion.setAsunto("Prueba de vida realizada incorrectamente");
			notificacion.setDescripcion("Se rechazo la validación automática de la prueba de vida porque la identidad no coincide con la registrada");
			notificacion.setEstado(EstadoNotificacion.NoVista);
			notificacionRepo.save(notificacion);
			pruebaDeVidaRepo.updateEstado(idPruebaDeVida, EstadoPruebaDeVida.RechazadaAutomaticamente);
		}

		return result;
	}


	public Map<String, Object> validarRostro(int idPruebaDeVida, Integer idPersona) {
		Map<String, Object> result = new HashMap<>();
		try {
			System.out.println("id persona es igual a "+idPersona);
			FotoIdentificacion fotoRef = fotoIdentificacionRepo.findByIdPersona(idPersona);

			// Obtener la foto de la prueba de vida desde la base de datos
			FotoPruebaDeVida foto = fotoPruebaDeVidaRepo.findByIdPruebaDeVida(idPruebaDeVida);
			if (foto == null) {
				result.put("success", false);
				result.put("message", "No se encontró la foto de la prueba de vida.");
				return result;
			}

			// Obtener el directorio base del proyecto
			String baseDir = System.getProperty("user.dir");
			System.out.println("Base directory: " + baseDir);

			// Construir rutas relativas
			String outputPath = baseDir + "/face_recognition/input_image.jpg";
			String scriptPath = baseDir + "/face_recognition/facial_recognition.py";
			String folderReference = baseDir + "/face_recognition/"+idPersona;

			// Verificar que el directorio de referencia existe
			Path referenceDirPath = Paths.get(folderReference);
			if (!Files.exists(referenceDirPath)) {
				Files.createDirectories(referenceDirPath);
			}

			byte[] fotoReferenciaBytes = fotoRef.getFoto();
			Path pathReference = referenceDirPath.resolve("reference.jpg");
			Files.write(pathReference, fotoReferenciaBytes);

			byte[] fotoBytes = foto.getFoto();
			Path path = Paths.get(outputPath);
			Files.write(path, fotoBytes);

			String imagePath = outputPath; 

			// Comando para ejecutar el script de Python con la imagen como argumento
			String command = "py " + scriptPath + " " + folderReference + " " + imagePath;

			// Crear un proceso para ejecutar el comando
			ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
			processBuilder.redirectErrorStream(true); // Redirigir errores al flujo de salida

			// Iniciar el proceso
			Process process = processBuilder.start();

			// Leer la salida del proceso
			InputStream inputStream = process.getInputStream();
			String output = new BufferedReader(new InputStreamReader(inputStream))
					.lines().collect(Collectors.joining("\n"));

			System.out.println(output);

			// Esperar a que el proceso termine
			int exitCode = process.waitFor();

			// Comprobar el código de salida del proceso
			if (exitCode == 0) {
				// Limpiar el mensaje de INFO y procesar el JSON resultante
				String cleanOutput = output.replaceAll("(?i)INFO: Created TensorFlow Lite XNNPACK delegate for CPU.", "").trim();
				JSONObject json = new JSONObject(cleanOutput);

				// Convertir el JSON a un Map
				result.put("data", json.toMap());

				// Acceder al valor asociado a la clave "data"
				Map<String, Object> dataMap = (Map<String, Object>) result.get("data");

				if (dataMap != null) {
					// Iterar sobre el mapa data
					for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
						String key = entry.getKey();
						Object value = entry.getValue();
						System.out.println(key);
						// Usar equals para comparar cadenas
						if ("match".equals(key) && Boolean.TRUE.equals(value)) {
							result.put("success", true);
							break;
						}
					}
					if (!result.containsKey("success")) {
						result.put("success", false);
					}
				} else {
					result.put("success", false);
					result.put("message", "No se encontró el objeto data en el resultado.");
				}
			} else {
				result.put("success", false);
				result.put("message", "Error al ejecutar el script de validación de rostro.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("message", "Excepción en la validación de rostro: " + e.getMessage());
		}
		return result;
	}


}
