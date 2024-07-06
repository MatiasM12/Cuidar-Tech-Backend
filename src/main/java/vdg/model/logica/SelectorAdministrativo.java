package vdg.model.logica;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import vdg.model.domain.Grupo;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.domain.RolDeUsuario;
import vdg.model.domain.Usuario;
import vdg.repository.GrupoRepository;
import vdg.repository.RestriccionPerimetralRepository;
import vdg.repository.UsuarioRepository;
import vdg.model.domain.EstadoUsuario;


@Component
public class SelectorAdministrativo {
	
	
	
	
	public RestriccionPerimetral seleccionarAdminstrativo(RestriccionPerimetral res,RestriccionPerimetralRepository resRepo, UsuarioRepository repoUsuario) throws Exception{
		ArrayList<Usuario> usuariosDelGrupo = (ArrayList<Usuario>) repoUsuario.findAllByIdGrupo(res.getIdGrupo());
		if(usuariosDelGrupo.size()==0) throw new Exception("Este grupo no tiene administrativos asignados");
		
		
		System.out.println(usuariosDelGrupo.toString());
		
		ArrayList<Usuario> administrativosDelGrupo =(ArrayList<Usuario>)
													usuariosDelGrupo.stream()
													.filter(usuario -> usuario.getRolDeUsuario()==RolDeUsuario.ADMINISTRATIVO)
													.collect(Collectors.toList());;
		
		int contador = resRepo.findAllByidUsuario(administrativosDelGrupo.get(0).getIdUsuario()).size();
		System.out.println(usuariosDelGrupo.get(0).getEmail()+" Soy el primero");
		
		Usuario  usuarioADevolver = null; 
		for(Usuario usuario : usuariosDelGrupo  ){
			System.out.println(usuario.getEmail());
			if(usuario.getEstadoUsuario()==EstadoUsuario.CONECTADO && usuario.getRolDeUsuario() == RolDeUsuario.ADMINISTRATIVO) {
				System.out.println(usuario.getEmail());
				int conteoActual = resRepo.findAllByidUsuario(usuario.getIdUsuario()).size();	
				System.out.println(usuario.getEmail() + conteoActual);
				if(conteoActual<=contador) {
					usuarioADevolver = usuario;
					contador = conteoActual;
				}
				
			}	
		}
		
		if(usuarioADevolver==null) {
			for(Usuario usuario : usuariosDelGrupo  ){
				if(usuario.getEstadoUsuario()==EstadoUsuario.AUSENTE && usuario.getRolDeUsuario() == RolDeUsuario.ADMINISTRATIVO) {
					int conteoActual = resRepo.findAllByidUsuario(usuario.getIdUsuario()).size();	
					if(conteoActual<=contador) {
						usuarioADevolver = usuario;
						contador = conteoActual;
					}
				}	
			}
		}
		
		System.out.println(usuarioADevolver);
		
		if(usuarioADevolver!=null) {
			res.setIdUsuario(usuarioADevolver.getIdUsuario());
			return res;
		}
		
		
		
		
		throw new Exception("Este grupo no tiene administrativos disponibles");
		
	}
	
	

}
