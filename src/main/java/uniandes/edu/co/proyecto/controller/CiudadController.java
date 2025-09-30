package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.repositorio.CiudadRepository;
import uniandes.edu.co.proyecto.modelo.Ciudad;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping
    public Iterable<Ciudad> listarCiudades() {
        return ciudadRepository.darCiudades();
    }

    @GetMapping("/{id}")
    public Ciudad getCiudad(@PathVariable Long id) {
        return ciudadRepository.darCiudad(id);
    }

    @PostMapping
    public ResponseEntity<Ciudad> crearCiudad(@RequestBody Ciudad ciudad) {
        System.out.println("Creando ciudad: " + ciudad);
        ciudadRepository.save(ciudad);
        return ResponseEntity.ok(ciudad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ciudad> actualizarCiudad(@PathVariable("id") Long id,
                                               @RequestBody Ciudad ciudad) {
    ciudad.setIdCiudad(id);
    Ciudad actualizada = ciudadRepository.save(ciudad);
    return ResponseEntity.ok(actualizada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCiudad(@PathVariable("id") Long id) {
        ciudadRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
