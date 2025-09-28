package uniandes.edu.co.proyecto.repositorio;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uniandes.edu.co.proyecto.modelo.UsuarioConductor;

@Repository
public interface UsuarioConductorRepository extends JpaRepository<UsuarioConductor, Long> {
    // RFC2: Top 20 conductores que más servicios han prestado
    @Query("SELECT s.usuarioConductor, COUNT(s) AS totalServicios " +
           "FROM Servicio s " +
           "GROUP BY s.usuarioConductor " +
           "ORDER BY totalServicios DESC")
    List<Object[]> top20Conductores(Pageable pageable);
}
