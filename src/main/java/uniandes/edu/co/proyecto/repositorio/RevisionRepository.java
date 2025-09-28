package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import uniandes.edu.co.proyecto.modelo.Revision;

@Repository
public interface RevisionRepository extends JpaRepository<Revision, Long> {

    @Query("SELECT r FROM Revision r WHERE r.usuarioRevisado.idUsuario = :idConductor")
    List<Revision> revisionesDeConductor(Long idConductor);

    @Query("SELECT r FROM Revision r WHERE r.usuarioRevisado.idUsuario = :idUsuarioServicio")
    List<Revision> revisionesDeUsuario(Long idUsuarioServicio);

    @Query(value = "SELECT * FROM REVISION", nativeQuery = true)
    Collection<Revision> darRevisiones();

    @Query(value = "SELECT * FROM REVISION WHERE IDREVISION = :id", nativeQuery = true)
    Revision darRevision(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO REVISION (IDSERVICIO, IDUSUARIOREVISADO, CALIFICACION, COMENTARIO) " +
                   "VALUES (:idServicio, :idUsuarioRevisado, :calificacion, :comentario)", nativeQuery = true)
    void insertarRevision(@Param("idServicio") Long idServicio,
                          @Param("idUsuarioRevisado") Long idUsuarioRevisado,
                          @Param("calificacion") int calificacion,
                          @Param("comentario") String comentario);

    @Modifying
    @Transactional
    @Query(value = "UPDATE REVISION SET CALIFICACION = :calificacion WHERE IDREVISION = :id", nativeQuery = true)
    void actualizarCalificacion(@Param("id") Long id, @Param("calificacion") int calificacion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM REVISION WHERE IDREVISION = :id", nativeQuery = true)
    void eliminarRevision(@Param("id") Long id);
}
