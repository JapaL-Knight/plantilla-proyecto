package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.modelo.Usuario; 

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // RFC1: Historial de servicios pedidos por un usuario de servicio
    @Query("SELECT s FROM Servicio s WHERE s.usuarioServicio.idUsuario = :idUsuario")
    List<Servicio> historialServiciosUsuario(Long idUsuario);
    
    Usuario findByCedula(String cedula);
    
    //  Consultar todos los usuarios
    @Query(value = "SELECT * FROM USUARIO", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    //  Consultar usuario por id
    @Query(value = "SELECT * FROM USUARIO WHERE idusuario = :id", nativeQuery = true)
    Usuario darUsuario(@Param("id") String cedula);

    //  Eliminar usuario por id
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM USUARIO WHERE idusuario = :id", nativeQuery = true)
    void eliminarUsuario(@Param(value = "id")
            String cedula);
    
    //  Insertar usuario
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO USUARIO (CEDULA, NOMBRE, CORREO, CELULAR, CALIFICACION) " +
                   "VALUES (:cedula, :nombre, :correo, :celular, :calificacion)",
           nativeQuery = true)
    void insertarUsuario(@Param("cedula") String cedula,
                         @Param("nombre") String nombre,
                         @Param("correo") String correo,
                         @Param("celular") String celular,
                         @Param("calificacion") int calificacion);

    //  Actualizar usuario
    @Modifying
    @Transactional
    @Query(value = "UPDATE USUARIO SET nombre = :nombre, correo = :correo, celular = :celular, calificacion = :calificacion WHERE idusuario = :id", nativeQuery = true)
    void actualizarUsuario(@Param("id") long id, 
                           @Param("nombre") String nombre, 
                           @Param("correo") String correo, 
                           @Param("celular") String celular,
                           @Param("calificacion") int calificacion);

       // RFC 1
    @Query(value = "SELECT s.IDSERVICIO, " +
                   "       v.PLACA, v.MARCA, v.MODELO, v.COLOR, v.CAPACIDADPASAJEROS, v.TIPOVEHICULO, v.NIVEL, " +
                   "       uc.IDUSUARIOCONDUCTOR, u.NOMBRE AS NOMBRECONDUCTOR, u.CEDULA AS CEDULACONDUCTOR, " +
                   "       u.CORREO AS CORREOCONDUCTOR, u.CELULAR AS CELULARCONDUCTOR " +
                   "FROM SERVICIO s " +
                   "INNER JOIN USUARIO_SERVICIO us ON s.IDUSUARIOSERVICIO = us.IDUSUARIOSERVICIO " +
                   "INNER JOIN USUARIO_CONDUCTOR uc ON s.IDUSUARIOCONDUCTOR = uc.IDUSUARIOCONDUCTOR " +
                   "INNER JOIN USUARIO u ON uc.IDUSUARIO = u.IDUSUARIO " +
                   "INNER JOIN VEHICULO v ON s.IDVEHICULO = v.IDVEHICULO " +
                   "WHERE us.IDUSUARIO = :idUsuario",
           nativeQuery = true)
    Collection<Object[]> consultarServiciosPorUsuario(@Param("idUsuario") Long idUsuario);

    @Modifying
    @Transactional
    @Query(value = "UPDATE USUARIO SET CORREO = :correo WHERE CEDULA = :cedula", nativeQuery = true)
    void actualizarCorreo(@Param("cedula") String cedula, @Param("correo") String correo);

}
