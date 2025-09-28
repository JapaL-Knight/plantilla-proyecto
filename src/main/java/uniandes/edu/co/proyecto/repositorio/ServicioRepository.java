package uniandes.edu.co.proyecto.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uniandes.edu.co.proyecto.modelo.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    // RFC3: Ganancias por conductor, discriminadas por vehículo y tipo de servicio
    @Query("SELECT s.vehiculo.placa, s.tipoServicio, SUM(s.costo) " +
           "FROM Servicio s " +
           "WHERE s.usuarioConductor.idUsuario = :idConductor " +
           "GROUP BY s.vehiculo.placa, s.tipoServicio")
    List<Object[]> gananciasPorConductor(Long idConductor);

    // RFC4: Utilización de servicios en ciudad por rango de fechas
    @Query("SELECT p.ciudad, COUNT(s) " +
           "FROM Servicio s JOIN s.puntos p " +
           "WHERE p.ciudad = :ciudad AND s.fecha BETWEEN :inicio AND :fin " +
           "GROUP BY p.ciudad")
    List<Object[]> utilizacionPorCiudad(String ciudad, Date inicio, Date fin);
}
