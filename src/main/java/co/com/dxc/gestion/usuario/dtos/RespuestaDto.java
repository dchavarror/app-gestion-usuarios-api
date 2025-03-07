package co.com.dxc.gestion.usuario.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(value = Include.NON_NULL)
public class RespuestaDto {
	
	private Integer codigo;
	private String mensaje;
	private Object  respuesta;
	private Integer totalRegistros;
}
