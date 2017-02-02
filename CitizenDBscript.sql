CREATE TABLE IF NOT EXISTS Citizen (
	id BIGINT NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    apellidos VARCHAR(20) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    residencia VARCHAR(40) NOT NULL,
    nacionalidad VARCHAR(15) NOT NULL,
    dni VARCHAR(9) NOT NULL,
    nombre_usuario VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    CONSTRAINT PK_CITIZEN PRIMARY KEY (id)
);