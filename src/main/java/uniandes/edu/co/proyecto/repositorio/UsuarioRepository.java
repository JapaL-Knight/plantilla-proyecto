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
    Usuario darUsuario(@Param("id") long id);

    //  Eliminar usuario por id
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM USUARIO WHERE idusuario = :id", nativeQuery = true)
    void eliminarUsuario(@Param("id") long id);

    //  Actualizar usuario
    @Modifying
    @Transactional
    @Query(value = "UPDATE USUARIO SET nombre = :nombre, correo = :correo, celular = :celular, calificacion = :calificacion WHERE idusuario = :id", nativeQuery = true)
    void actualizarUsuario(@Param("id") long id, 
                           @Param("nombre") String nombre, 
                           @Param("correo") String correo, 
                           @Param("celular") String celular,
                           @Param("calificacion") int calificacion);
}
