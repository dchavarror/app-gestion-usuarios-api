package co.com.dxc.gestion.usuario.repositories;


import co.com.dxc.gestion.usuario.entities.UsuarioEntity;
import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
	
	Page<UsuarioEntity>  findAll(Pageable page);
	
	Optional<UsuarioEntity> findByNombre(String usuario);
	
	Optional<UsuarioEntity> findByNombreAndContrasenia(String usuario, String contrasenia);
	Optional<UsuarioEntity> findByCorreoAndContrasenia(String usuario, String contrasenia);
	
	@Transactional
	void deleteById(String id);
	
	@Query(nativeQuery = true, value = "SELECT count(nombre) FROM dbo.usuario")
	Integer obtenerCantidadUsuarios();
}
