package uniandes.edu.co.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.UsuarioConductor;
import uniandes.edu.co.proyecto.repositorio.UsuarioConductorRepository;

@RestController
@RequestMapping("/usuarios-conductor")
public class UsuarioConductorController {

    @Autowired
    private UsuarioConductorRepository repo;

    @GetMapping
    public List<UsuarioConductor> listar() {
        return repo.findAll();
    }

    @PostMapping
    public UsuarioConductor crear(@RequestBody UsuarioConductor u) {
        return repo.save(u);
    }

    @GetMapping("/{id}")
    public UsuarioConductor obtener(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public UsuarioConductor actualizar(@PathVariable Long id, @RequestBody UsuarioConductor u) {
        u.setIdUsuario(id);
        return repo.save(u);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        repo.deleteById(id);
    }
    @GetMapping("/top20")
    public ResponseEntity<?> top20Conductores() {
        return ResponseEntity.ok(repo.top20Conductores());
    }
    
}
