package uniandes.edu.co.proyecto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uniandes.edu.co.proyecto.modelo.Revision;

@Repository
public interface RevisionRepository extends JpaRepository<Revision, Long> {

    @Query("SELECT r FROM Revision r WHERE r.usuarioRevisado.idUsuario = :idConductor")
    List<Revision> revisionesDeConductor(Long idConductor);

    @Query("SELECT r FROM Revision r WHERE r.usuarioRevisado.idUsuario = :idUsuarioServicio")
    List<Revision> revisionesDeUsuario(Long idUsuarioServicio);

}
