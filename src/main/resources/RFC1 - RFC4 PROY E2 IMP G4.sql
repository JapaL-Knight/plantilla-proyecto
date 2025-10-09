--RFC 1
SELECT s.*, 
       v.placa,
       v.marca, 
       v.modelo, 
       v.color, 
       v.capacidadPasajeros, 
       v.tipoVehiculo, 
       v.nivel,
       uc.idUsuarioConductor, 
       u.nombre AS nombreConductor, 
       u.cedula AS cedulaConductor, 
       u.correo AS correoConductor, 
       u.celular AS celularConductor
FROM Servicio s
INNER JOIN Usuario_Servicio us ON s.idUsuarioServicio = us.idUsuarioServicio
INNER JOIN Usuario_Conductor uc ON s.idUsuarioConductor = uc.idUsuarioConductor
INNER JOIN Usuario u ON uc.idUsuario = u.idUsuario
INNER JOIN Vehiculo v ON s.idVehiculo = v.idVehiculo
WHERE us.idUsuario = :idUsuario;

--RFC 2
SELECT uc.idUsuarioConductor, 
    u.nombre, 
    u.cedula, 
    u.correo, 
    u.celular, 
    COUNT(s.idServicio) AS q_servicios
FROM Usuario_Conductor uc
INNER JOIN Usuario u ON uc.idUsuario = u.idUsuario
LEFT JOIN Servicio s ON s.idUsuarioConductor = uc.idUsuarioConductor
GROUP BY uc.idUsuarioConductor, u.nombre, u.cedula, u.correo, u.celular
ORDER BY q_servicios DESC
FETCH FIRST 20 ROWS ONLY;

--RFC 3
SELECT 
    uc.idUsuarioConductor,
    v.idVehiculo,
    v.placa,
    v.marca,
    v.modelo,
    COUNT(s.idServicio) AS cantidad_servicios,
    SUM(s.costoTotal) AS valor_total_servicios,
    SUM(s.costoTotal) * 0.6 AS valor_neto_conductor
FROM Usuario_Conductor uc
INNER JOIN Vehiculo v ON v.idUsuarioConductor = uc.idUsuarioConductor
INNER JOIN Servicio s ON s.idVehiculo = v.idVehiculo AND s.idUsuarioConductor = uc.idUsuarioConductor
GROUP BY uc.idUsuarioConductor, v.idVehiculo, v.placa, v.marca, v.modelo
ORDER BY uc.idUsuarioConductor, v.idVehiculo;

--RFC 4
SELECT
    c.nombre AS ciudad,
    t.tipoServicio,
    t.nivel,
    COUNT(s.idServicio) AS cantidad_servicios,
    ROUND(100.0 * COUNT(s.idServicio) / SUM(COUNT(s.idServicio)) OVER (), 2) AS porcentaje,
    SUM(s.costoTotal) AS valor_total
FROM Servicio s
INNER JOIN Vehiculo v ON s.idVehiculo = v.idVehiculo
INNER JOIN Ciudad c ON v.idCiudadExpedicion = c.idCiudad
INNER JOIN Tarifa t ON s.idTarifa = t.idTarifa
WHERE c.idCiudad = :idCiudad
  AND s.fechaInicio BETWEEN TO_DATE(:fechaInicio, 'YYYY-MM-DD') AND TO_DATE(:fechaFin, 'YYYY-MM-DD')
GROUP BY c.nombre, t.tipoServicio, t.nivel
ORDER BY cantidad_servicios DESC;