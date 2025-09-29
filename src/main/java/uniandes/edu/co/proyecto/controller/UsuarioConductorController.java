package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.UsuarioConductor;
import uniandes.edu.co.proyecto.repositorio.UsuarioConductorRepository;

import java.util.List;

@RestController
@RequestMapping("/usuarios_conductor")
public class UsuarioConductorController {

    @Autowired
    private UsuarioConductorRepository usuarioConductorRepository;

    // ✅ Obtener todos los usuarios conductor (usando JPA básico)
    @GetMapping
    public List<UsuarioConductor> darUsuariosConductor() {
        return usuarioConductorRepository.findAll();
    }

    // ✅ Buscar un usuario conductor por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> darUsuarioConductor(@PathVariable("id") Long id) {
        try {
            UsuarioConductor usuario = usuarioConductorRepository.findById(id).orElse(null);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Usuario conductor no encontrado");
            }
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error: " + e.getMessage());
        }
    }

    // ✅ Crear un nuevo usuario conductor (usando JPA básico)
    @PostMapping
    public ResponseEntity<?> crearUsuarioConductor(@RequestBody UsuarioConductor usuario) {
        try {
            UsuarioConductor nuevoUsuario = usuarioConductorRepository.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Usuario conductor creado correctamente. ID: " + nuevoUsuario.getIdUsuario());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("❌ Error al crear Usuario conductor: " + e.getMessage());
        }
    }

    // ✅ Actualizar un usuario conductor
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuarioConductor(@PathVariable("id") Long id,
                                                        @RequestBody UsuarioConductor usuario) {
        try {
            if (!usuarioConductorRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Usuario conductor no encontrado");
            }
            
            usuario.setIdUsuario(id);
            usuarioConductorRepository.save(usuario);
            
            return ResponseEntity.ok("✅ Usuario conductor actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("❌ Error al actualizar Usuario conductor: " + e.getMessage());
        }
    }

    // ✅ Eliminar un usuario conductor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuarioConductor(@PathVariable("id") Long id) {
        try {
            if (!usuarioConductorRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Usuario conductor no encontrado");
            }
            
            usuarioConductorRepository.deleteById(id);
            return ResponseEntity.ok("✅ Usuario conductor eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("❌ Error al eliminar Usuario conductor: " + e.getMessage());
        }
    }
}