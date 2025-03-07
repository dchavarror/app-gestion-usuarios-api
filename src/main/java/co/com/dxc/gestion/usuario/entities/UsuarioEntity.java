package co.com.dxc.gestion.usuario.entities;


import java.io.Serializable;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
@EntityListeners(AuditingEntityListener.class)
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@UuidGenerator
	@Basic(optional = false)
	@Column(name = "id")
	private String id;
	
	@Basic(optional = false)
	@Column(name = "nombre",unique = true)
	private String nombre;

	@Basic(optional = false)
	@Column(name = "contrasenia")
	private String contrasenia;
	
	@Basic(optional = false)
	@Column(name = "correo_electronico",unique = true)
	private String correo;
}
