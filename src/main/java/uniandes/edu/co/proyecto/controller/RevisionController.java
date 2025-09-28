package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Revision;
import uniandes.edu.co.proyecto.repositorio.RevisionRepository;

@RestController
@RequestMapping("/revisiones")
public class RevisionController {

    @Autowired
    private RevisionRepository revisionRepository;

    @GetMapping
    public Collection<Revision> darRevisiones() {
        return revisionRepository.darRevisiones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> darRevision(@PathVariable("id") Long id) {
        Revision revision = revisionRepository.darRevision(id);
        if (revision == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Revisión no encontrada");
        }
        return ResponseEntity.ok(revision);
    }

    @PostMapping
    public ResponseEntity<?> crearRevision(@RequestBody Revision revision) {
        try {
            revisionRepository.insertarRevision(
                    revision.getServicio().getIdServicio(),
                    revision.getUsuarioRevisado().getIdUsuario(),
                    revision.getCalificacion(),
                    revision.getComentario()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Revisión creada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error al crear Revisión: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/calificacion")
    public ResponseEntity<?> actualizarCalificacion(@PathVariable("id") Long id, @RequestParam int calificacion) {
        try {
            revisionRepository.actualizarCalificacion(id, calificacion);
            return ResponseEntity.ok("✅ Calificación actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error al actualizar calificación: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRevision(@PathVariable("id") Long id) {
        try {
            revisionRepository.eliminarRevision(id);
            return ResponseEntity.ok("✅ Revisión eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error al eliminar Revisión: " + e.getMessage());
        }
    }
}
