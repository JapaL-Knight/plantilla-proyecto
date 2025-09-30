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

import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping
    public Collection<Servicio> darServicios() {
        return servicioRepository.darServicios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> darServicio(@PathVariable("id") Long id) {
        Servicio servicio = servicioRepository.darServicio(id);
        if (servicio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Servicio no encontrado");
        }
        return ResponseEntity.ok(servicio);
    }

    @PostMapping
    public ResponseEntity<?> crearServicio(@RequestBody Servicio servicio) {
        try {
            servicioRepository.insertarServicio(
                    servicio.getDistanciaKm(),
                    servicio.getIdTarifa(),
                    servicio.getTipoServicio(),
                    servicio.getFecha().toString(),
                    servicio.getCosto(),
                    servicio.getUsuarioServicio().getIdUsuario(),
                    servicio.getUsuarioConductor().getIdUsuario(),
                    servicio.getVehiculo().getIdVehiculo()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Servicio creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error al crear Servicio: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/costo")
    public ResponseEntity<?> actualizarCosto(@PathVariable("id") Long id, @RequestParam double costo) {
        try {
            servicioRepository.actualizarCosto(id, costo);
            return ResponseEntity.ok("✅ Costo actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error al actualizar costo: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarServicio(@PathVariable("id") Long id) {
        try {
            servicioRepository.eliminarServicio(id);
            return ResponseEntity.ok("✅ Servicio eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error al eliminar Servicio: " + e.getMessage());
        }
    }

    @GetMapping("/ganancias/{idConductor}")
    public Collection<Object[]> gananciasPorConductor(@PathVariable("idConductor") Long idConductor) {
        return servicioRepository.gananciasPorVehiculoYServicio(idConductor);
    }

    @GetMapping("/utilizacion")
    public Collection<Object[]> utilizacionPorCiudad(@RequestParam String ciudad,
                                                     @RequestParam String inicio,
                                                     @RequestParam String fin) {
        return servicioRepository.utilizacionPorCiudad(ciudad, java.sql.Date.valueOf(inicio), java.sql.Date.valueOf(fin));
    }
}
