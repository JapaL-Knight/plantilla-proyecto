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
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.UsuarioServicio;
import uniandes.edu.co.proyecto.repositorio.UsuarioServicioRepository;

@RestController
@RequestMapping("/usuarios_servicio")
public class UsuarioServicioController {

    @Autowired
    private UsuarioServicioRepository usuarioServicioRepository;


    @GetMapping
    public Collection<UsuarioServicio> darUsuariosServicio() {
        return usuarioServicioRepository.darUsuariosServicio();
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<?> darUsuarioServicio(@PathVariable("cedula") String cedula) {
        UsuarioServicio usuario = usuarioServicioRepository.darUsuarioServicio(cedula);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Usuario de servicio no encontrado");
        }
        return ResponseEntity.ok(usuario);
    }


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