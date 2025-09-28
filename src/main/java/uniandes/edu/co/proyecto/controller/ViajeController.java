package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.Viaje;
import uniandes.edu.co.proyecto.repositorio.ViajeRepository;

import java.util.List;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    private ViajeRepository repo;

    @GetMapping
    public List<Viaje> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Viaje crear(@RequestBody Viaje v) {
        return repo.save(v);
    }

    @GetMapping("/{id}")
    public Viaje obtener(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Viaje actualizar(@PathVariable Long id, @RequestBody Viaje v) {
        v.setIdViaje(id);
        return repo.save(v);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
