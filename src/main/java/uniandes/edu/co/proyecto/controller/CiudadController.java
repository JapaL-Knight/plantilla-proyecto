package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Ciudad> getCiudad(@PathVariable Integer id) {
        try {
            Ciudad ciudad = ciudadRepository.darCiudad(id);
            return new ResponseEntity<>( ciudad,  HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
       

    // ACA
    @PostMapping
    public ResponseEntity<Ciudad> crearCiudad(@RequestBody Ciudad ciudad) {
        System.out.println("Creando ciudad: " + ciudad);
        
        try {
            ciudadRepository.insertarCiudad(ciudad.getNombre());
            Ciudad ciudadCreada = ciudadRepository.darUltimaCiudadCreada();
            return new ResponseEntity<>(ciudadCreada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ciudad> actualizarCiudad(@PathVariable("id") Integer id, @RequestBody Ciudad ciudad) {
    ciudad.setId(id);
    Ciudad actualizada = ciudadRepository.save(ciudad);
    return ResponseEntity.ok(actualizada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCiudad(@PathVariable("id") Long id) {
        ciudadRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
