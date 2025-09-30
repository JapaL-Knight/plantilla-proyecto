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

import uniandes.edu.co.proyecto.modelo.Punto;
import uniandes.edu.co.proyecto.repositorio.PuntoRepository;

@RestController
@RequestMapping("/puntos")
public class PuntoController {

    @Autowired
    private PuntoRepository puntoRepository;

    @GetMapping
    public Collection<Punto> darPuntos() {
        return puntoRepository.darPuntos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> darPunto(@PathVariable("id") Long id) {
        System.out.println("Buscando punto con ID: " + id);
        Punto p = puntoRepository.darPunto(id);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Punto no encontrado");
        }
        return ResponseEntity.ok(p);
    }

    @GetMapping("/ciudad/{idCiudad}")
    public Collection<Punto> darPuntosPorCiudad(@PathVariable("idCiudad") Long idCiudad) {
        return puntoRepository.darPuntosPorCiudad(idCiudad);
    }

    @PostMapping
    public ResponseEntity<?> crearPunto(@RequestBody Punto punto) {
        System.out.println("Creando punto: " + punto.getIdCiudad() + ", " + punto.getDireccion() + ", " + 
                           punto.getLongitud() + ", " + punto.getLatitud() + ", " + punto.getOrden());
        try { 
            if (punto.getIdCiudad() == null || 
                punto.getDireccion() == null || punto.getDireccion().isBlank() ||
                punto.getLongitud() == null || punto.getLatitud() == null ||
                punto.getOrden() == null) {
                return ResponseEntity.badRequest().body("❌ Datos incompletos para crear el punto");
            }

            puntoRepository.insertarPunto(
                    punto.getIdCiudad(),
                    punto.getDireccion(),
                    punto.getLongitud(),
                    punto.getLatitud(),
                    punto.getOrden(),
                    punto.getServicio().getIdServicio()
                );

            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Punto creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("❌ Error al crear punto: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPunto(@PathVariable("id") Long id, @RequestBody Punto punto) {
        try {
            puntoRepository.actualizarPunto(
                id,
                punto.getIdCiudad(),
                punto.getDireccion(),
                punto.getLongitud(),
                punto.getLatitud(),
                punto.getOrden()
            );
            return ResponseEntity.ok("✅ Punto actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("❌ Error al actualizar punto: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPunto(@PathVariable("id") Long id) {
        try {
            puntoRepository.eliminarPunto(id);
            return ResponseEntity.ok("✅ Punto eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("❌ Error al eliminar punto: " + e.getMessage());
        }
    }
}