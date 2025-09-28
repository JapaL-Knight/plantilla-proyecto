package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.Punto;
import uniandes.edu.co.proyecto.repositorio.PuntoRepository;

import java.util.List;

@RestController
@RequestMapping("/puntos")
public class PuntoController {

    @Autowired
    private PuntoRepository repo;

    @GetMapping
    public List<Punto> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Punto crear(@RequestBody Punto p) {
        return repo.save(p);
    }

    @GetMapping("/{id}")
    public Punto obtener(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Punto actualizar(@PathVariable Long id, @RequestBody Punto p) {
        p.setId(id);
        return repo.save(p);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
