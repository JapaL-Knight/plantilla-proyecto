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

import uniandes.edu.co.proyecto.modelo.Vehiculo;
import uniandes.edu.co.proyecto.repositorio.VehiculoRepository;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @GetMapping
    public Collection<Vehiculo> darVehiculos() {
        return vehiculoRepository.darVehiculos();
    }

    @GetMapping("/{placa}")
    public ResponseEntity<?> darVehiculo(@PathVariable("placa") String placa) {
        Vehiculo vehiculo = vehiculoRepository.darVehiculo(placa);
        if (vehiculo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Vehículo no encontrado");
        }
        return ResponseEntity.ok(vehiculo);
    }

    @PostMapping
    public ResponseEntity<?> crearVehiculo(@RequestBody Vehiculo vehiculo) {
        try {
            vehiculoRepository.insertarVehiculo(
                    vehiculo.getPlaca(),
                    vehiculo.getMarca(),
                    vehiculo.getModelo(),
                    vehiculo.getCapacidadPasajeros(),
                    vehiculo.getNivel(),
                    vehiculo.getUsuarioConductor().getIdUsuario()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body("✅ Vehículo creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error al crear Vehículo: " + e.getMessage());
        }
    }

    @PutMapping("/{placa}/marca")
    public ResponseEntity<?> actualizarMarca(@PathVariable("placa") String placa, @RequestParam String marca) {
        try {
            vehiculoRepository.actualizarMarca(placa, marca);
            return ResponseEntity.ok("✅ Marca actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error al actualizar marca: " + e.getMessage());
        }
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<?> eliminarVehiculo(@PathVariable("placa") String placa) {
        try {
            vehiculoRepository.eliminarVehiculo(placa);
            return ResponseEntity.ok("✅ Vehículo eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error al eliminar Vehículo: " + e.getMessage());
        }
    }
}
