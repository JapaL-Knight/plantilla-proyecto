package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
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

    @Query(value = "SELECT * FROM SERVICIO", nativeQuery = true)
    Collection<Servicio> darServicios();

    @Query(value = "SELECT * FROM SERVICIO WHERE IDSERVICIO = :id", nativeQuery = true)
    Servicio darServicio(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SERVICIO (TIPOSERVICIO, FECHA, COSTO, IDUSUARIOSERVICIO, IDUSUARIOCONDUCTOR, IDVEHICULO) " +
                   "VALUES (:tipo, :fecha, :costo, :idUsuarioServicio, :idConductor, :idVehiculo)", nativeQuery = true)
    void insertarServicio(@Param("tipo") String tipo,
                          @Param("fecha") String fecha,
                          @Param("costo") double costo,
                          @Param("idUsuarioServicio") Long idUsuarioServicio,
                          @Param("idConductor") Long idConductor,
                          @Param("idVehiculo") Long idVehiculo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE SERVICIO SET COSTO = :costo WHERE IDSERVICIO = :id", nativeQuery = true)
    void actualizarCosto(@Param("id") Long id, @Param("costo") double costo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SERVICIO WHERE IDSERVICIO = :id", nativeQuery = true)
    void eliminarServicio(@Param("id") Long id);

}
