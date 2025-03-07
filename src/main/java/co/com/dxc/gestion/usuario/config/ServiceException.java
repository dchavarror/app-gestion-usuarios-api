package co.com.dxc.gestion.usuario.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String mensaje;
    private Integer codigo;
    private String info;

    public ServiceException(String mensaje, Integer codigo, String info) {
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.info = info;
    }
    
    public ServiceException(String mensaje) {
        this.mensaje = mensaje;
    }

}
