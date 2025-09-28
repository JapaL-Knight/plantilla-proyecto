package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uniandes.edu.co.proyecto.modelo.UsuarioConductor;

@Repository
public interface UsuarioConductorRepository extends JpaRepository<UsuarioConductor, Long> {

    
    // ✅ RFC2: Top 20 conductores con más servicios
    @Query(value = "SELECT uc.IDUSUARIOCONDUCTOR, u.NOMBRE, u.CEDULA, u.CORREO, u.CELULAR, " +
                   "       COUNT(s.IDSERVICIO) AS Q_SERVICIOS " +
                   "FROM USUARIO_CONDUCTOR uc " +
                   "INNER JOIN USUARIO u ON uc.IDUSUARIO = u.IDUSUARIO " +
                   "LEFT JOIN SERVICIO s ON s.IDUSUARIOCONDUCTOR = uc.IDUSUARIOCONDUCTOR " +
                   "GROUP BY uc.IDUSUARIOCONDUCTOR, u.NOMBRE, u.CEDULA, u.CORREO, u.CELULAR " +
                   "ORDER BY Q_SERVICIOS DESC " +
                   "FETCH FIRST 20 ROWS ONLY",
           nativeQuery = true)
    Collection<Object[]> top20Conductores();
}
