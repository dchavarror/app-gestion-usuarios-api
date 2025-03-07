package co.com.dxc.gestion.usuario.service;

import org.springframework.http.ResponseEntity;

import co.com.dxc.gestion.usuario.dtos.RespuestaDto;
import co.com.dxc.gestion.usuario.dtos.UsuarioDto;

public interface IUsuarioService {
	
	public ResponseEntity<RespuestaDto> obtenerUsuarios(Integer pagina, Integer cantidad);
	
	public ResponseEntity<RespuestaDto> guardarUsuario(UsuarioDto usuario);
	
	public ResponseEntity<RespuestaDto> eliminarUsuario(UsuarioDto usuario);
	
	public ResponseEntity<RespuestaDto> autenticar(UsuarioDto usuario);
}
