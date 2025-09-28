package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("SELECT v FROM Viaje v WHERE v.servicio.idServicio = :idServicio")
    List<Viaje> viajesPorServicio(Long idServicio);

    @Query(value = "SELECT * FROM VIAJE", nativeQuery = true)
    Collection<Viaje> darViajes();

    @Query(value = "SELECT * FROM VIAJE WHERE IDVIAJE = :id", nativeQuery = true)
    Viaje darViaje(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO VIAJE (IDSERVICIO, HORAINICIO, HORAFIN, COSTO) " +
                   "VALUES (:idServicio, :horaInicio, :horaFin, :costo)", nativeQuery = true)
    void insertarViaje(@Param("idServicio") Long idServicio,
                       @Param("horaInicio") String horaInicio,
                       @Param("horaFin") String horaFin,
                       @Param("costo") double costo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE VIAJE SET COSTO = :costo WHERE IDVIAJE = :id", nativeQuery = true)
    void actualizarCosto(@Param("id") Long id, @Param("costo") double costo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM VIAJE WHERE IDVIAJE = :id", nativeQuery = true)
    void eliminarViaje(@Param("id") Long id);
}
