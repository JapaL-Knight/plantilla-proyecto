package uniandes.edu.co.proyecto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uniandes.edu.co.proyecto.modelo.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Vehiculo findByPlaca(String placa);
    
    // RFC3: Total de dinero obtenido por cada vehículo del conductor
    @Query("SELECT v.placa, s.tipoServicio, SUM(s.costo) " +
           "FROM Servicio s JOIN s.vehiculo v " +
           "WHERE s.usuarioConductor.idUsuario = :idConductor " +
           "GROUP BY v.placa, s.tipoServicio")
    List<Object[]> gananciasPorVehiculoYServicio(Long idConductor);
}
