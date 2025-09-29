-- 1) USUARIO
CREATE TABLE Usuario (
    idUsuario INT PRIMARY KEY,
    cedula VARCHAR(40) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    celular VARCHAR(20) NOT NULL,
    calificacion DECIMAL(2,1) CHECK (calificacion >= 0 AND calificacion <= 5)
);

-- 2) USUARIO_CONDUCTOR
CREATE TABLE Usuario_Conductor (
    idUsuarioConductor INT PRIMARY KEY,
    idUsuario INT NOT NULL UNIQUE,
    CONSTRAINT fk_uc_usuario FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);

-- 3) CIUDAD
CREATE TABLE Ciudad (
    idCiudad INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- 4) HORA
CREATE TABLE Hora (
    idHora INT PRIMARY KEY,
    inicio CHAR(5) NOT NULL,
    fin    CHAR(5) NOT NULL
);

-- 5) DISPONIBILIDAD
CREATE TABLE Disponibilidad (
    idDisponibilidad INT PRIMARY KEY,
    idUsuarioConductor INT NOT NULL,
    CONSTRAINT fk_disp_conductor FOREIGN KEY (idUsuarioConductor) REFERENCES Usuario_Conductor(idUsuarioConductor)
);

-- 6) DIA
CREATE TABLE Dia (
    idDia INT PRIMARY KEY,
    mes INT NOT NULL CHECK (mes BETWEEN 1 AND 12),
    dia INT NOT NULL CHECK (dia BETWEEN 1 AND 31),
    anio INT NOT NULL CHECK (anio > 2000),
    idDisponibilidad INT NOT NULL,
    idHora INT NOT NULL,
    CONSTRAINT fk_dia_disp FOREIGN KEY (idDisponibilidad) REFERENCES Disponibilidad(idDisponibilidad),
    CONSTRAINT fk_dia_hora FOREIGN KEY (idHora) REFERENCES Hora(idHora)
);

-- 7) VEHICULO
CREATE TABLE Vehiculo (
    idVehiculo INT PRIMARY KEY,
    placa VARCHAR(7) NOT NULL UNIQUE,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    color VARCHAR(30) NOT NULL,
    idCiudadExpedicion INT NOT NULL,
    capacidadPasajeros INT NOT NULL,
    tipoVehiculo VARCHAR(20) NOT NULL CHECK (tipoVehiculo IN ('CARRO','MOTO','VAN','CAMIONETA')),
    nivel VARCHAR(20) NOT NULL CHECK (nivel IN ('LARGE','COMFORT','ESTANDAR')),
    idUsuarioConductor INT NOT NULL,
    CONSTRAINT fk_vehiculo_ciudad FOREIGN KEY (idCiudadExpedicion) REFERENCES Ciudad(idCiudad),
    CONSTRAINT fk_vehiculo_conductor FOREIGN KEY (idUsuarioConductor) REFERENCES Usuario_Conductor(idUsuarioConductor)
);

-- 8) USUARIO_SERVICIO
CREATE TABLE Usuario_Servicio (
    idUsuarioServicio INT PRIMARY KEY,
    idUsuario INT NOT NULL,
    numTarjeta VARCHAR(20) NOT NULL,
    nombreTarjeta VARCHAR(100) NOT NULL,
    vencimiento VARCHAR(20) NOT NULL,
    codigoSeguridad VARCHAR(10) NOT NULL,
    CONSTRAINT fk_us_usuario FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);

-- 9) SERVICIO
CREATE TABLE Servicio (
    idServicio INT PRIMARY KEY,
    distanciaKm DECIMAL(10,2) NOT NULL,
    idTipoServicio VARCHAR(20) NOT NULL CHECK (idTipoServicio IN ('TRANSPORTE','DOMICILIO','MERCANCIA')),
    idUsuarioServicio INT NOT NULL,
    idUsuarioConductor INT NOT NULL,
    idVehiculo INT NOT NULL,
    CONSTRAINT fk_servicio_usuarioServicio FOREIGN KEY (idUsuarioServicio) REFERENCES Usuario_Servicio(idUsuarioServicio),
    CONSTRAINT fk_servicio_conductor FOREIGN KEY (idUsuarioConductor) REFERENCES Usuario_Conductor(idUsuarioConductor),
    CONSTRAINT fk_servicio_vehiculo FOREIGN KEY (idVehiculo) REFERENCES Vehiculo(idVehiculo)
);

-- 10) PUNTO
CREATE TABLE Punto (
    idPunto INT PRIMARY KEY,
    idServicio INT NOT NULL,
    longitud DECIMAL(12,6) NOT NULL,
    latitud DECIMAL(12,6) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    idCiudad INT NOT NULL,
    CONSTRAINT fk_punto_servicio FOREIGN KEY (idServicio) REFERENCES Servicio(idServicio),
    CONSTRAINT fk_punto_ciudad FOREIGN KEY (idCiudad) REFERENCES Ciudad(idCiudad)
);

-- 11) REVISION
CREATE TABLE Revision (
    idRevision INT PRIMARY KEY,
    idServicio INT NOT NULL,
    calificacion DECIMAL(2,1) CHECK (calificacion >= 0 AND calificacion <= 5),
    comentario VARCHAR(200) NOT NULL,
    CONSTRAINT fk_revision_servicio FOREIGN KEY (idServicio) REFERENCES Servicio(idServicio)
);

--12) TARIFA
CREATE TABLE Tarifa (
    idTarifa INT PRIMARY KEY,
    nivel VARCHAR(20) NOT NULL CHECK (nivel IN ('LARGE','COMFORT','ESTANDAR')),
    tipoServicio VARCHAR(20) NOT NULL CHECK (tipoServicio IN ('TRANSPORTE','DOMICILIO','MERCANCIA')),
    costoKm DECIMAL(10,2) NOT NULL
);

ALTER TABLE Servicio
DROP COLUMN idTipoServicio;

ALTER TABLE Servicio
ADD idTarifa INT NOT NULL;

ALTER TABLE Servicio
ADD CONSTRAINT fk_servicio_tarifa FOREIGN KEY (idTarifa) REFERENCES Tarifa(idTarifa);

ALTER TABLE Servicio
ADD costoTotal DECIMAL(12,2) NOT NULL;

ALTER TABLE Servicio ADD (fechaInicio TIMESTAMP, fechaFin TIMESTAMP);

CREATE SEQUENCE ALPESCAB_SEQUENCE
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

SELECT sequence_name FROM user_sequences WHERE sequence_name = 'ALPESCAB_SEQUENCE';
