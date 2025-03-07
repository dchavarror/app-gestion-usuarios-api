CREATE TABLE ptecnica.dbo.usuario (
	id uniqueidentifier NOT NULL,
	nombre varchar(100) COLLATE Modern_Spanish_CI_AS NOT NULL,
	contrasenia varchar(200) COLLATE Modern_Spanish_CI_AS NOT NULL,
	correo_electronico varchar(100) COLLATE Modern_Spanish_CI_AS NOT NULL,
	CONSTRAINT correo_UN UNIQUE (correo_electronico),
	CONSTRAINT usuario_PK PRIMARY KEY (id),
	CONSTRAINT usuario_UN UNIQUE (nombre)
);