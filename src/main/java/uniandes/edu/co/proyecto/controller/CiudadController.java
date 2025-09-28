package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.repositorio.CiudadRepository;
import uniandes.edu.co.proyecto.modelo.Ciudad;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    private CiudadRepository ciudadRepository;

    // Registrar ciudad
    @PostMapping
    public String crearCiudad(@RequestBody Ciudad ciudad) {
        if (ciudad.getNombre() == null || ciudad.getNombre().isBlank()) {
            return "❌ Error: el nombre de la ciudad no puede ser vacío";
        }
        ciudadRepository.insertarCiudad(ciudad.getNombre());
        return "Ciudad registrada exitosamente";
    }

    // Consultar todas las ciudades
    @GetMapping
    public Iterable<Ciudad> listarCiudades() {
        return ciudadRepository.darCiudades();
    }

    // Consultar ciudad por id
    @GetMapping("/{id}")
    public Ciudad getCiudad(@PathVariable Long id) {
        return ciudadRepository.darCiudad(id);
    }
}
