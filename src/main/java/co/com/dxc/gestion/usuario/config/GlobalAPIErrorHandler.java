package co.com.dxc.gestion.usuario.config;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.com.dxc.gestion.usuario.dtos.RespuestaDto;
import co.com.dxc.gestion.usuario.utils.Constantes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@ControllerAdvice
@Slf4j
public class GlobalAPIErrorHandler  extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(value = {ConstraintViolationException.class, DataIntegrityViolationException.class})
	public ResponseEntity<RespuestaDto> contraintViolationError(ConstraintViolationException ex) {
		log.error("contraintViolationError: {}", ex);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(RespuestaDto.builder().codigo(HttpStatus.ACCEPTED.value())
						.mensaje(Constantes.POR_FAVOR_VALIDAR_DATOS_NO_PUEDE_DUPLICAR_VALORES)
						.build());
	}
	
	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class, JsonProcessingException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<RespuestaDto> errorPeticion(RuntimeException ex) {
		log.error("errorPeticion: {}", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(RespuestaDto.builder().codigo(HttpStatus.BAD_REQUEST.value())
						.mensaje(ex.getMessage())
						.build());
	}

	@ExceptionHandler(value = { NoSuchElementException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<RespuestaDto> errorNoEncontrado(RuntimeException ex) {
		log.error("errorNoEncontrado: {}", ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(RespuestaDto.builder().codigo(HttpStatus.NOT_FOUND.value())
						.mensaje(ex.getMessage()).build());
	}

	@ExceptionHandler(value = { Exception.class, IOException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<RespuestaDto> internalError(Exception ex) {
		log.error("internalError: {}", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(RespuestaDto.builder().codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.mensaje(ex.getMessage()).build());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("handleHttpMessageNotReadable: {}", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(RespuestaDto.builder().codigo(HttpStatus.BAD_REQUEST.value())
						.mensaje(ex.getMessage())
						.build());
	}

	@ExceptionHandler(value =ServiceException.class)
	public ResponseEntity<RespuestaDto> servicerError(ServiceException ex) {
		log.error("servicerError: {}", ex);
		return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(RespuestaDto.builder().codigo(HttpStatus.CONFLICT.value())
				.mensaje(ex.getMensaje()).build());
	}

}
