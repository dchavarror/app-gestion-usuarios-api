package co.com.dxc.gestion.usuario.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import co.com.dxc.gestion.usuario.dtos.RespuestaDto;
import co.com.dxc.gestion.usuario.dtos.UsuarioDto;
import co.com.dxc.gestion.usuario.service.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/usuario")
@Tag(name = "Usuario - Controller", description = "Controller encargado de gestionar las operaciones del usuario.")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final IUsuarioService usuario;
	
	@Operation(summary = "Operación que permite autenticar el usuario ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se realizo la autenticación exitosamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }),
			@ApiResponse(responseCode = "400", description = "La petición no puede ser entendida por el servidor debido a errores de sintaxis, el cliente no debe repetirla no sin antes hacer modificaciones", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }),
			@ApiResponse(responseCode = "404", description = "El recurso solicitado no puede ser encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Se presento una condición inesperada que impidió completar la petición", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }), })
	@PostMapping("/autenticar")
	public ResponseEntity<RespuestaDto> autenticar(@RequestBody UsuarioDto usuario) {
		return this.usuario.autenticar(usuario);
	}
	
	@Operation(summary = "Operación que permite guardar el usuario ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se guarda exitosamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }),
			@ApiResponse(responseCode = "400", description = "La petición no puede ser entendida por el servidor debido a errores de sintaxis, el cliente no debe repetirla no sin antes hacer modificaciones", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }),
			@ApiResponse(responseCode = "404", description = "El recurso solicitado no puede ser encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Se presento una condición inesperada que impidió completar la petición", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }), })
	@PostMapping("/guardar")
	public ResponseEntity<RespuestaDto> guardarUsuario(@RequestBody UsuarioDto usuario) {
		return this.usuario.guardarUsuario(usuario);
	}
	
	@Operation(summary = "Operación que permite eliminar el usuario ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se elimina exitosamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }),
			@ApiResponse(responseCode = "400", description = "La petición no puede ser entendida por el servidor debido a errores de sintaxis, el cliente no debe repetirla no sin antes hacer modificaciones", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }),
			@ApiResponse(responseCode = "404", description = "El recurso solicitado no puede ser encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Se presento una condición inesperada que impidió completar la petición", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }), })
	@PostMapping("/eliminar")
	public ResponseEntity<RespuestaDto> eliminarUsuario(@RequestBody UsuarioDto usuario) {
		return this.usuario.eliminarUsuario(usuario);
	}
	
	@Operation(summary = "Operación que permite consultar los usuarios")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se consulta exitosamente", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }),
			@ApiResponse(responseCode = "400", description = "La petición no puede ser entendida por el servidor debido a errores de sintaxis, el cliente no debe repetirla no sin antes hacer modificaciones", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }),
			@ApiResponse(responseCode = "404", description = "El recurso solicitado no puede ser encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Se presento una condición inesperada que impidió completar la petición", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RespuestaDto.class)) }), })
	@GetMapping("/{pagina}/{cantidad}")
	public ResponseEntity<RespuestaDto> consultarUsuarios(@PathVariable Integer pagina, @PathVariable Integer cantidad) {
		return this.usuario.obtenerUsuarios(pagina, cantidad);
	}
}
