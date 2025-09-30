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
public interface UsuarioServicioRepository extends JpaRepository<UsuarioServicio, Long> {

    boolean existsByNumTarjeta(String numTarjeta);
    

    @Query(value = "SELECT * FROM USUARIO_SERVICIO", nativeQuery = true)
    Collection<UsuarioServicio> darUsuariosServicio();


    @Query(value = "SELECT * FROM USUARIO_SERVICIO WHERE CEDULA = :cedula", nativeQuery = true)
    UsuarioServicio darUsuarioServicio(@Param("cedula") String cedula);


    @Query(value = "SELECT * FROM USUARIO_SERVICIO WHERE NUMTARJETA = :numTarjeta", nativeQuery = true)
    UsuarioServicio darUsuarioPorTarjeta(@Param("numTarjeta") String numTarjeta);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO USUARIO_SERVICIO (CEDULA, NOMBRE, CORREO, CELULAR, CALIFICACION, NUMTARJETA, NOMBRETARJETA, VENCIMIENTO, CODIGOSEGURIDAD) " +
                   "VALUES (:cedula, :nombre, :correo, :celular, :calificacion, :numTarjeta, :nombreTarjeta, :vencimiento, :codigoSeguridad)",
            nativeQuery = true)
    void insertarUsuarioServicio(@Param("cedula") String cedula,
                                 @Param("nombre") String nombre,
                                 @Param("correo") String correo,
                                 @Param("celular") String celular,
                                 @Param("calificacion") int calificacion,
                                 @Param("numTarjeta") String numTarjeta,
                                 @Param("nombreTarjeta") String nombreTarjeta,
                                 @Param("vencimiento") String vencimiento,
                                 @Param("codigoSeguridad") int codigoSeguridad);


    @Modifying
    @Transactional
    @Query(value = "UPDATE USUARIO_SERVICIO SET NUMTARJETA = :numTarjeta, NOMBRETARJETA = :nombreTarjeta, " +
                   "VENCIMIENTO = :vencimiento, CODIGOSEGURIDAD = :codigoSeguridad " +
                   "WHERE CEDULA = :cedula",
            nativeQuery = true)
    void actualizarTarjeta(@Param("cedula") String cedula,
                           @Param("numTarjeta") String numTarjeta,
                           @Param("nombreTarjeta") String nombreTarjeta,
                           @Param("vencimiento") String vencimiento,
                           @Param("codigoSeguridad") int codigoSeguridad);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM USUARIO_SERVICIO WHERE CEDULA = :cedula", nativeQuery = true)
    void eliminarUsuarioServicio(@Param("cedula") Long cedula);
}