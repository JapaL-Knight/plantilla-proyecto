package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Vehiculo findByPlaca(String placa);

    @Query("SELECT v.placa, s.tipoServicio, SUM(s.costo) " +
           "FROM Servicio s JOIN s.vehiculo v " +
           "WHERE s.usuarioConductor.idUsuario = :idConductor " +
           "GROUP BY v.placa, s.tipoServicio")
    List<Object[]> gananciasPorVehiculoYServicio(Long idConductor);

    @Query(value = "SELECT * FROM VEHICULO", nativeQuery = true)
    Collection<Vehiculo> darVehiculos();

    @Query(value = "SELECT * FROM VEHICULO WHERE PLACA = :placa", nativeQuery = true)
    Vehiculo darVehiculo(@Param("placa") String placa);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO VEHICULO (PLACA, MARCA, MODELO, CAPACIDADPASAJEROS, NIVEL, IDUSUARIOCONDUCTOR) " +
                   "VALUES (:placa, :marca, :modelo, :capacidad, :nivel, :idConductor)", nativeQuery = true)
    void insertarVehiculo(@Param("placa") String placa,
                          @Param("marca") String marca,
                          @Param("modelo") String modelo,
                          @Param("capacidad") int capacidad,
                          @Param("nivel") String nivel,
                          @Param("idConductor") Long idConductor);

    @Modifying
    @Transactional
    @Query(value = "UPDATE VEHICULO SET MARCA = :marca WHERE PLACA = :placa", nativeQuery = true)
    void actualizarMarca(@Param("placa") String placa, @Param("marca") String marca);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM VEHICULO WHERE PLACA = :placa", nativeQuery = true)
    void eliminarVehiculo(@Param("placa") String placa);

}
