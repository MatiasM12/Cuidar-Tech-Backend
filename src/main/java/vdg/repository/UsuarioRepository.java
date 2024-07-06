package vdg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import vdg.model.domain.EstadoUsuario;
import vdg.model.domain.RolDeUsuario;
import vdg.model.domain.Usuario;

public interface UsuarioRepository extends Repository<Usuario, Integer>{
	
	public List<Usuario> findAll();
	public Usuario save(Usuario usuario);
	public void delete(Usuario usuario);
	@Query(value = "SELECT * FROM Usuario u WHERE u.email = :email", nativeQuery = true)
	public List<Usuario> findAllByEmail(String email);
	public List<Usuario> findAllByIdUsuario(int idUsuario);
	@Query(value = "SELECT * FROM Usuario u WHERE u.rolDeUsuario='ADMINISTRATIVO' OR u.rolDeUsuario='SUPERVISOR' OR u.rolDeUsuario='SUPERVISOR_GENERAL'", nativeQuery = true)
	public List<Usuario> findEmpleados();
	public List<Usuario> findAllByestadoUsuario(EstadoUsuario estadoUsuario);
	public List<Usuario> findAllByrolDeUsuario(RolDeUsuario rolDeUsuario);
	public List<Usuario> findAllByIdGrupo(Integer idGrupo);
    @Query(value = "SELECT * FROM Usuario u WHERE LOWER(u.email) = LOWER(:email)", nativeQuery = true)
    public List<Usuario> findByEmailIgnoreCase(@Param("email") String email);

}
