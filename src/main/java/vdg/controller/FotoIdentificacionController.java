package vdg.controller;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vdg.model.domain.FotoIdentificacion;
import vdg.model.domain.FotoIdentificacion2;
import vdg.repository.FotoIdentificacionRepository;

@RestController
@RequestMapping("/FotoIdentificacion")
@CrossOrigin
public class FotoIdentificacionController {

	@Autowired
	private FotoIdentificacionRepository fotoIdentificacionRepo;

	//ESTE METODO CREA LA IMAGEN COMO JPG Y FUNCIONA
	@GetMapping
	public List<FotoIdentificacion> listar() {
		/*
		List<FotoIdentificacion> fotos = fotoIdentificacionRepo.findAll();
		for (FotoIdentificacion foto : fotos) {
			byte[] byte_array = foto.getFoto();
			ByteArrayInputStream input_stream = new ByteArrayInputStream(byte_array);
			BufferedImage final_buffered_image;
			try {
				final_buffered_image = ImageIO.read(input_stream);
				ImageIO.write(final_buffered_image, "jpg", new File("final_file.jpg"));
				System.out.println("Converted Successfully!");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return fotoIdentificacionRepo.findAll();
	}

	@GetMapping("{idPersona}")
	public FotoIdentificacion getFotoIdentificacionPersona(@PathVariable("idPersona") int idPersona) {
		return fotoIdentificacionRepo.findByIdPersona(idPersona);
	}
	
	//ESTE METODO DEVUELVE EL STRING DE LA FOTO CON EL ENCABEZADO PARA INDICAR AL FRONT
	//QUE ES UNA IMAGEN
	@GetMapping("/getFoto/{idVictimario}")
	public FotoIdentificacion2 getProbando(@PathVariable("idVictimario") int idVictimario) {
		FotoIdentificacion foto = fotoIdentificacionRepo.findByIdPersona(idVictimario);
		FotoIdentificacion2 foto2= new FotoIdentificacion2();
		foto2.setFoto("data:image/png;base64,"+ Base64.getEncoder().encodeToString(foto.getFoto()));
		foto2.setIdFoto(foto.getIdFoto());
		foto2.setIdPersona(idVictimario);
		return foto2;
	}
	
	@PostMapping
	public FotoIdentificacion agregar(String foto, int idPersona) {
		Decoder decoder = Base64.getDecoder();
		byte[] decodedByte = decoder.decode(foto.split(",")[1]);
		FotoIdentificacion fotoIdentificacion = new FotoIdentificacion();
		fotoIdentificacion.setIdPersona(idPersona);
		fotoIdentificacion.setFoto(decodedByte);
		return fotoIdentificacionRepo.save(fotoIdentificacion);
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") int idPersona) {
		fotoIdentificacionRepo.deleteByIdPersona(idPersona);
	}
	
}
