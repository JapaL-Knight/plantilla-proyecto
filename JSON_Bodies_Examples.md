# JSON Bodies para Operaciones CRUD

## 1. CIUDAD

### POST - Crear Ciudad
```json
{
  "nombre": "Bogotá"
}
```

### PUT/PATCH - Actualizar Ciudad
```json
{
  "idCiudad": 1,
  "nombre": "Medellín"
}
```

### GET Response - Obtener Ciudad
```json
{
  "idCiudad": 1,
  "nombre": "Bogotá"
}
```

### DELETE - Solo requiere ID en la URL: `/ciudades/{id}`

---

## 2. USUARIO

### POST - Crear Usuario
```json
{
  "cedula": "1234567890",
  "nombre": "Juan Pérez",
  "correo": "juan.perez@email.com",
  "celular": "3001234567",
  "calificacion": 5
}
```

### PUT/PATCH - Actualizar Usuario
```json
{
  "idUsuario": 1,
  "cedula": "1234567890",
  "nombre": "Juan Carlos Pérez",
  "correo": "juan.carlos@email.com",
  "celular": "3007654321",
  "calificacion": 4
}
```

### GET Response - Obtener Usuario
```json
{
  "idUsuario": 1,
  "cedula": "1234567890",
  "nombre": "Juan Pérez",
  "correo": "juan.perez@email.com",
  "celular": "3001234567",
  "calificacion": 5
}
```

---

## 3. USUARIO_CONDUCTOR

### POST - Crear Usuario Conductor
```json
{
  "cedula": "9876543210",
  "nombre": "María García",
  "correo": "maria.garcia@email.com",
  "celular": "3109876543",
  "calificacion": 5,
  "vehiculos": []
}
```

### PUT/PATCH - Actualizar Usuario Conductor
```json
{
  "idUsuario": 2,
  "cedula": "9876543210",
  "nombre": "María Elena García",
  "correo": "maria.elena@email.com",
  "celular": "3109876543",
  "calificacion": 4,
  "vehiculos": [
    {
      "idVehiculo": 1,
      "placa": "ABC123"
    }
  ]
}
```

### GET Response - Obtener Usuario Conductor
```json
{
  "idUsuario": 2,
  "cedula": "9876543210",
  "nombre": "María García",
  "correo": "maria.garcia@email.com",
  "celular": "3109876543",
  "calificacion": 5,
  "vehiculos": [
    {
      "idVehiculo": 1,
      "placa": "ABC123",
      "marca": "Toyota",
      "modelo": "Corolla",
      "capacidadPasajeros": 4,
      "nivel": "ESTANDAR"
    }
  ]
}
```

---

## 4. USUARIO_SERVICIO

### POST - Crear Usuario Servicio
```json
{
  "cedula": "1122334455",
  "nombre": "Carlos López",
  "correo": "carlos.lopez@email.com",
  "celular": "3201122334",
  "calificacion": 5,
  "numTarjeta": "4111111111111111",
  "nombreTarjeta": "Carlos López",
  "vencimiento": "12/25",
  "codigoSeguridad": 123
}
```

### PUT/PATCH - Actualizar Usuario Servicio
```json
{
  "idUsuario": 3,
  "cedula": "1122334455",
  "nombre": "Carlos Andrés López",
  "correo": "carlos.andres@email.com",
  "celular": "3201122334",
  "calificacion": 4,
  "numTarjeta": "4111111111111111",
  "nombreTarjeta": "Carlos Andrés López",
  "vencimiento": "06/26",
  "codigoSeguridad": 456
}
```

### GET Response - Obtener Usuario Servicio
```json
{
  "idUsuario": 3,
  "cedula": "1122334455",
  "nombre": "Carlos López",
  "correo": "carlos.lopez@email.com",
  "celular": "3201122334",
  "calificacion": 5,
  "numTarjeta": "4111111111111111",
  "nombreTarjeta": "Carlos López",
  "vencimiento": "12/25",
  "codigoSeguridad": 123
}
```

---

## 5. VEHICULO

### POST - Crear Vehículo
```json
{
  "placa": "ABC123",
  "marca": "Toyota",
  "modelo": "Corolla",
  "capacidadPasajeros": 4,
  "nivel": "ESTANDAR",
  "usuarioConductor": {
    "idUsuario": 2
  }
}
```

### PUT/PATCH - Actualizar Vehículo
```json
{
  "idVehiculo": 1,
  "placa": "ABC123",
  "marca": "Toyota",
  "modelo": "Corolla 2024",
  "capacidadPasajeros": 5,
  "nivel": "COMFORT",
  "usuarioConductor": {
    "idUsuario": 2
  }
}
```

### GET Response - Obtener Vehículo
```json
{
  "idVehiculo": 1,
  "placa": "ABC123",
  "marca": "Toyota",
  "modelo": "Corolla",
  "capacidadPasajeros": 4,
  "nivel": "ESTANDAR",
  "usuarioConductor": {
    "idUsuario": 2,
    "nombre": "María García",
    "cedula": "9876543210"
  }
}
```

---

## 6. PUNTO

### POST - Crear Punto
```json
{
  "idPunto": 1,
  "idCiudad": 1,
  "direccion": "Calle 123 # 45-67",
  "longitud": -74.0721,
  "latitud": 4.7110,
  "orden": 1,
  "servicio": {
    "idServicio": 1
  }
}
```

### PUT/PATCH - Actualizar Punto
```json
{
  "idPunto": 1,
  "idCiudad": 1,
  "direccion": "Carrera 7 # 32-16",
  "longitud": -74.0721,
  "latitud": 4.7110,
  "orden": 2,
  "servicio": {
    "idServicio": 1
  }
}
```

### GET Response - Obtener Punto
```json
{
  "idPunto": 1,
  "idCiudad": 1,
  "direccion": "Calle 123 # 45-67",
  "longitud": -74.0721,
  "latitud": 4.7110,
  "orden": 1,
  "servicio": {
    "idServicio": 1,
    "tipoServicio": "TRANSPORTE"
  }
}
```

---

## 7. SERVICIO

### POST - Crear Servicio
```json
{
  "tipoServicio": "TRANSPORTE",
  "fecha": "2024-03-15T10:30:00.000+00:00",
  "costo": 15000.0,
  "usuarioConductor": {
    "idUsuario": 2
  },
  "usuarioServicio": {
    "idUsuario": 3
  },
  "vehiculo": {
    "idVehiculo": 1
  }
}
```

### PUT/PATCH - Actualizar Servicio
```json
{
  "idServicio": 1,
  "tipoServicio": "DOMICILIO",
  "fecha": "2024-03-15T11:30:00.000+00:00",
  "costo": 20000.0,
  "usuarioConductor": {
    "idUsuario": 2
  },
  "usuarioServicio": {
    "idUsuario": 3
  },
  "vehiculo": {
    "idVehiculo": 1
  }
}
```

### GET Response - Obtener Servicio
```json
{
  "idServicio": 1,
  "tipoServicio": "TRANSPORTE",
  "fecha": "2024-03-15T10:30:00.000+00:00",
  "costo": 15000.0,
  "usuarioConductor": {
    "idUsuario": 2,
    "nombre": "María García",
    "cedula": "9876543210"
  },
  "usuarioServicio": {
    "idUsuario": 3,
    "nombre": "Carlos López",
    "cedula": "1122334455"
  },
  "vehiculo": {
    "idVehiculo": 1,
    "placa": "ABC123",
    "marca": "Toyota",
    "modelo": "Corolla"
  },
  "puntos": [
    {
      "idPunto": 1,
      "direccion": "Calle 123 # 45-67",
      "orden": 1
    }
  ],
  "revisiones": [],
  "viajes": [
    {
      "idViaje": 1,
      "horaInicio": "10:30:00",
      "horaFin": "11:15:00",
      "costo": 15000.0
    }
  ]
}
```

---

## 8. VIAJE

### POST - Crear Viaje
```json
{
  "horaInicio": "10:30:00",
  "horaFin": "11:15:00",
  "costo": 15000.0,
  "servicio": {
    "idServicio": 1
  }
}
```

### PUT/PATCH - Actualizar Viaje
```json
{
  "idViaje": 1,
  "horaInicio": "10:30:00",
  "horaFin": "11:45:00",
  "costo": 18000.0,
  "servicio": {
    "idServicio": 1
  }
}
```

### GET Response - Obtener Viaje
```json
{
  "idViaje": 1,
  "horaInicio": "10:30:00",
  "horaFin": "11:15:00",
  "costo": 15000.0,
  "servicio": {
    "idServicio": 1,
    "tipoServicio": "TRANSPORTE",
    "fecha": "2024-03-15T10:30:00.000+00:00"
  }
}
```

---

## 9. REVISION

### POST - Crear Revisión
```json
{
  "calificacion": 5,
  "comentario": "Excelente servicio, muy puntual y amable",
  "servicio": {
    "idServicio": 1
  },
  "usuarioRevisado": {
    "idUsuario": 2
  }
}
```

### PUT/PATCH - Actualizar Revisión
```json
{
  "idRevision": 1,
  "calificacion": 4,
  "comentario": "Buen servicio, llegó a tiempo",
  "servicio": {
    "idServicio": 1
  },
  "usuarioRevisado": {
    "idUsuario": 2
  }
}
```

### GET Response - Obtener Revisión
```json
{
  "idRevision": 1,
  "calificacion": 5,
  "comentario": "Excelente servicio, muy puntual y amable",
  "servicio": {
    "idServicio": 1,
    "tipoServicio": "TRANSPORTE",
    "fecha": "2024-03-15T10:30:00.000+00:00"
  },
  "usuarioRevisado": {
    "idUsuario": 2,
    "nombre": "María García",
    "cedula": "9876543210"
  }
}
```

---

## Notas Importantes:

1. **DELETE Operations**: Generalmente solo requieren el ID en la URL (ej: `DELETE /ciudades/1`)

2. **Fechas**: Usar formato ISO 8601 (`YYYY-MM-DDTHH:mm:ss.sssZ`)

3. **Relaciones**: 
   - Para POST/PUT, generalmente solo se envía el ID de la entidad relacionada
   - Para GET, se puede incluir información completa de las entidades relacionadas

4. **IDs Autogenerados**: 
   - En POST, no incluir IDs autogenerados
   - En PUT/PATCH, siempre incluir el ID de la entidad a actualizar

5. **Validaciones**:
   - Campos `nullable = false` son obligatorios
   - Campos `unique = true` deben ser únicos
   - Respetar las longitudes máximas definidas

6. **Tipos de Servicio**: TRANSPORTE, DOMICILIO, MERCANCIA
7. **Niveles de Vehículo**: LARGE, COMFORT, ESTANDAR