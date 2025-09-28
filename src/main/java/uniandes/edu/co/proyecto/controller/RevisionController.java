package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.Revision;
import uniandes.edu.co.proyecto.repositorio.RevisionRepository;

import java.util.List;

@RestController
@RequestMapping("/revisiones")
public class RevisionController {

    @Autowired
    private RevisionRepository repo;

    @GetMapping
    public List<Revision> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Revision crear(@RequestBody Revision r) {
        return repo.save(r);
    }

    @GetMapping("/{id}")
    public Revision obtener(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Revision actualizar(@PathVariable Long id, @RequestBody Revision r) {
        r.setIdRevision(id);
        return repo.save(r);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
