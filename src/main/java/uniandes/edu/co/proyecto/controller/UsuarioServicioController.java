package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.UsuarioServicio;
import uniandes.edu.co.proyecto.repositorio.UsuarioServicioRepository;

import java.util.Collection;

@RestController
@RequestMapping("/usuarios_servicio")
public class UsuarioServicioController {

    @Autowired
    private UsuarioServicioRepository usuarioServicioRepository;

    // ✅ Dar todos los usuarios de servicio
    @GetMapping
    public Collection<UsuarioServicio> darUsuariosServicio() {
        return usuarioServicioRepository.darUsuariosServicio();
    }

    // ✅ Dar un usuario por cédula
    @GetMapping("/{cedula}")
    public ResponseEntity<?> darUsuarioServicio(@PathVariable("cedula") String cedula) {
        UsuarioServicio usuario = usuarioServicioRepository.darUsuarioServicio(cedula);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Usuario de servicio no encontrado");
        }
        return ResponseEntity.ok(usuario);
    }

    // ✅ Crear un nuevo usuario de servicio (RF2)
    @PostMapping
    public ResponseEntity<?> crearUsuarioServicio(@RequestBody UsuarioServicio usuario) {
        try {
            usuarioServicioRepository.insertarUsuarioServicio(
                    usuario.getCedula(),
                    usuario.getNombre(),
                    usuario.getCorreo(),
                    usuario.getCelular(),
                    usuario.getCalificacion(),
                    usuario.getNumTarjeta(),
                    usuario.getNombreTarjeta(),
                    usuario.getVencimiento(),
                    usuario.getCodigoSeguridad()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Usuario de servicio creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("❌ Error al crear Usuario de servicio: " + e.getMessage());
        }
    }

    // ✅ Actualizar tarjeta de un usuario
    @PutMapping("/{cedula}/tarjeta")
    public ResponseEntity<?> actualizarTarjeta(@PathVariable("cedula") String cedula,
                                               @RequestBody UsuarioServicio usuario) {
        try {
            usuarioServicioRepository.actualizarTarjeta(
                    cedula,
                    usuario.getNumTarjeta(),
                    usuario.getNombreTarjeta(),
                    usuario.getVencimiento(),
                    usuario.getCodigoSeguridad()
            );
            return ResponseEntity.ok("✅ Tarjeta actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("❌ Error al actualizar tarjeta: " + e.getMessage());
        }
    }

    // ✅ Eliminar un usuario de servicio
    @DeleteMapping("/{cedula}")
    public ResponseEntity<?> eliminarUsuarioServicio(@PathVariable("cedula") Long cedula) {
        try {
            usuarioServicioRepository.eliminarUsuarioServicio(cedula);
            return ResponseEntity.ok("✅ Usuario de servicio eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("❌ Error al eliminar Usuario de servicio: " + e.getMessage());
        }
    }
}