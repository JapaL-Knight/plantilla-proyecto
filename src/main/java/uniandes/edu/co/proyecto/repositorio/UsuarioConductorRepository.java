package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.UsuarioServicio;

@Repository
public interface UsuarioConductorRepository extends JpaRepository<UsuarioServicio, Long> {

    //  Dar todos los usuarios de servicio
    @Query(value = "SELECT * FROM USUARIO_SERVICIO", nativeQuery = true)
    Collection<UsuarioServicio> darUsuariosServicio();

    //  Buscar un usuario de servicio por cédula
    @Query(value = "SELECT * FROM USUARIO_SERVICIO WHERE CEDULA = :cedula", nativeQuery = true)
    UsuarioServicio darUsuarioServicio(@Param("cedula") long cedula);

    //  Insertar un nuevo usuario de servicio (RF2 exitoso)
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO USUARIO_SERVICIO (CEDULA, NOMBRE, CORREO, CELULAR, CALIFICACION, " +
                   "NUMTARJETA, NOMBRETARJETA, VENCIMIENTO, CODIGOSEGURIDAD) " +
                   "VALUES (:cedula, :nombre, :correo, :celular, :calificacion, " +
                   ":numTarjeta, :nombreTarjeta, :vencimiento, :codigoSeguridad)",
           nativeQuery = true)
    void insertarUsuarioServicio(@Param("cedula") Long cedula,
                                 @Param("nombre") String nombre,
                                 @Param("correo") String correo,
                                 @Param("celular") Long celular,
                                 @Param("calificacion") Integer calificacion,
                                 @Param("numTarjeta") String numTarjeta,
                                 @Param("nombreTarjeta") String nombreTarjeta,
                                 @Param("vencimiento") String vencimiento,
                                 @Param("codigoSeguridad") int codigoSeguridad);

    //  Actualizar tarjeta de un usuario de servicio
    @Modifying
    @Transactional
    @Query(value = "UPDATE USUARIO_SERVICIO " +
                   "SET NUMTARJETA = :numTarjeta, NOMBRETARJETA = :nombreTarjeta, " +
                   "VENCIMIENTO = :vencimiento, CODIGOSEGURIDAD = :codigoSeguridad " +
                   "WHERE CEDULA = :cedula",
           nativeQuery = true)
    void actualizarTarjeta(@Param("cedula") Long cedula,
                           @Param("numTarjeta") String numTarjeta,
                           @Param("nombreTarjeta") String nombreTarjeta,
                           @Param("vencimiento") String vencimiento,
                           @Param("codigoSeguridad") int codigoSeguridad);

    //  Eliminar un usuario de servicio
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM USUARIO_SERVICIO WHERE CEDULA = :cedula", nativeQuery = true)
    void eliminarUsuarioServicio(@Param("cedula") long cedula);

    //  RFC2: Top 20 conductores con más servicios
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



    
    