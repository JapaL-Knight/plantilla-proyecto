package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Viaje;
import uniandes.edu.co.proyecto.repositorio.ViajeRepository;

import java.util.Collection;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    private ViajeRepository viajeRepository;

    @GetMapping
    public Collection<Viaje> darViajes() {
        return viajeRepository.darViajes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> darViaje(@PathVariable("id") Long id) {
        Viaje viaje = viajeRepository.darViaje(id);
        if (viaje == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Viaje no encontrado");
        }
        return ResponseEntity.ok(viaje);
    }

    @PostMapping
    public ResponseEntity<?> crearViaje(@RequestBody Viaje viaje) {
        try {
            viajeRepository.insertarViaje(
                    viaje.getServicio().getIdServicio(),
                    viaje.getHoraInicio().toString(),
                    viaje.getHoraFin().toString(),
                    viaje.getCosto()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Viaje creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error al crear Viaje: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/costo")
    public ResponseEntity<?> actualizarCosto(@PathVariable("id") Long id, @RequestParam double costo) {
        try {
            viajeRepository.actualizarCosto(id, costo);
            return ResponseEntity.ok("✅ Costo actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error al actualizar costo: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarViaje(@PathVariable("id") Long id) {
        try {
            viajeRepository.eliminarViaje(id);
            return ResponseEntity.ok("✅ Viaje eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error al eliminar Viaje: " + e.getMessage());
        }
    }
}
