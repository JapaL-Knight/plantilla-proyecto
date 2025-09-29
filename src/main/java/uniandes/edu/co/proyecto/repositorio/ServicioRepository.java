package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

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
    Collection<Object[]> gananciasPorConductor(Long idConductor);

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

    @Query(value = "SELECT V.PLACA, S.TIPOSERVICIO, SUM(S.COSTO) " +
                   "FROM SERVICIO S JOIN VEHICULO V ON S.IDVEHICULO = V.IDVEHICULO " +
                   "WHERE S.IDUSUARIOCONDUCTOR = :idConductor " +
                   "GROUP BY V.PLACA, S.TIPOSERVICIO",
           nativeQuery = true)
    Collection<Object[]> gananciasPorVehiculoYServicio(@Param("idConductor") Long idConductor);

    @Query(value = "SELECT P.CIUDAD, COUNT(S.IDSERVICIO) " +
                   "FROM SERVICIO S JOIN PUNTO P ON S.IDSERVICIO = P.IDSERVICIO " +
                   "WHERE P.CIUDAD = :ciudad AND S.FECHA BETWEEN :inicio AND :fin " +
                   "GROUP BY P.CIUDAD",
           nativeQuery = true)
    Collection<Object[]> utilizacionPorCiudad(@Param("ciudad") String ciudad,
                                              @Param("inicio") java.sql.Date inicio,
                                              @Param("fin") java.sql.Date fin);

}
