package co.com.dxc.gestion.usuario.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
	
    private String id;
	private String nombre;
	private String contrasenia;
	private String correo;
}
