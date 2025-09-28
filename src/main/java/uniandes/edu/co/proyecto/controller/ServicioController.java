package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository;

import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioRepository repo;

    @GetMapping
    public List<Servicio> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Servicio crear(@RequestBody Servicio s) {
        return repo.save(s);
    }

    @GetMapping("/{id}")
    public Servicio obtener(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Servicio actualizar(@PathVariable Long id, @RequestBody Servicio s) {
        s.setIdServicio(id);
        return repo.save(s);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
