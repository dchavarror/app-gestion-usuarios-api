package co.com.dxc.gestion.usuario.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.com.dxc.gestion.usuario.config.ServiceException;
import co.com.dxc.gestion.usuario.config.security.JwtTokenUtil;
import co.com.dxc.gestion.usuario.dtos.RespuestaDto;
import co.com.dxc.gestion.usuario.dtos.UsuarioDto;
import co.com.dxc.gestion.usuario.entities.UsuarioEntity;
import co.com.dxc.gestion.usuario.maps.UsuarioMapper;
import co.com.dxc.gestion.usuario.repositories.UsuarioRepository;
import co.com.dxc.gestion.usuario.service.IUsuarioService;
import co.com.dxc.gestion.usuario.utils.Constantes;
import co.com.dxc.gestion.usuario.utils.EncriptarDesancriptar;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UserDetailsService,IUsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final JwtTokenUtil jwtTokenUtil;
	private final EncriptarDesancriptar encriptarDesancriptar;
	
	@Override
	public ResponseEntity<RespuestaDto> obtenerUsuarios(Integer pagina, Integer cantidad) {
		log.info("Inicio metodo obtenerUsuarios:{},{} ", pagina, cantidad);
		Pageable pageable = PageRequest.of(pagina, cantidad);
		List<UsuarioDto> usuarios =  UsuarioMapper.INSTANCE.beanListToDtoList(this.usuarioRepository.findAll(pageable).toList());
		for(int i=0;i<usuarios.size();i++) {
			usuarios.get(i).setContrasenia(encriptarDesancriptar.desencriptar(usuarios.get(i).getContrasenia()));
		}
		log.info("Fin metodo obtenerUsuarios:{},{} ", usuarios.size());
		return new ResponseEntity<RespuestaDto>(RespuestaDto.builder().codigo(HttpStatus.OK.value()).mensaje(HttpStatus.OK.name()).totalRegistros(this.usuarioRepository.obtenerCantidadUsuarios()).respuesta(usuarios).build(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RespuestaDto> guardarUsuario(UsuarioDto usuario) {
		log.info("Inicio metodo guardarUsuario :{} " , usuario.getNombre());
		usuario.setContrasenia(encriptarDesancriptar.encriptar(usuario.getContrasenia()));
		this.usuarioRepository.save(UsuarioMapper.INSTANCE.dtoToEntity(usuario));
		log.info("Fin metodo guardarUsuario :{} " , usuario.getNombre());
		
		return new ResponseEntity<RespuestaDto>(RespuestaDto.builder().codigo(HttpStatus.CREATED.value()).mensaje(HttpStatus.CREATED.name()).build(), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<RespuestaDto> eliminarUsuario(UsuarioDto usuario) {
		log.info("Inicio metodo eliminarUsuario :{} " , usuario.getNombre());
		this.usuarioRepository.deleteById(usuario.getId());
		log.info("Fin metodo eliminarUsuario :{} " , usuario.getNombre());
		return new ResponseEntity<RespuestaDto>(RespuestaDto.builder().codigo(HttpStatus.OK.value()).mensaje(HttpStatus.OK.name()).build(), HttpStatus.OK);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuarioEntity> usuario = this.usuarioRepository.findByNombre(username);
	
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder
				.encode(usuario.get().getContrasenia());
		return new User(username, hashedPassword, new ArrayList<>());
	}

	@Override
	public ResponseEntity<RespuestaDto> autenticar(UsuarioDto usuario) {
		log.info("Inicio metodo autenticar: {} ", usuario);
		Optional<UsuarioEntity> usuarioOpt = this.usuarioRepository.findByNombreAndContrasenia(usuario.getNombre(),encriptarDesancriptar.encriptar(usuario.getContrasenia())   );
		if(!usuarioOpt.isPresent()) {
			 usuarioOpt = this.usuarioRepository.findByCorreoAndContrasenia(usuario.getNombre(),encriptarDesancriptar.encriptar(usuario.getContrasenia())   );
		}
		if(usuarioOpt.isPresent()) {
			final UserDetails userDetails = loadUserByUsername(usuario.getNombre());
			final String token = jwtTokenUtil.generateToken(userDetails);
			Map<String, String>  map = new HashMap<String, String>();
			map.put(Constantes.ACCESS_TOKEN, token);
			return new ResponseEntity<RespuestaDto>(RespuestaDto.builder().codigo(HttpStatus.OK.value()).mensaje(Constantes.AUTENTACION_EXITOSA).respuesta(map).build(), HttpStatus.OK);
		}
		log.info("Fin metodo autenticar: {} ", usuario);
		throw new ServiceException(Constantes.POR_FAVOR_VERIFICAR_DATOS_INCORRECTO);
	}

}
