package uniandes.edu.co.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.UsuarioServicio;
import uniandes.edu.co.proyecto.repositorio.UsuarioServicioRepository;

@RestController
@RequestMapping("/usuarios-servicio")
public class UsuarioServicioController {

    @Autowired
    private UsuarioServicioRepository repo;

    @GetMapping
    public List<UsuarioServicio> listar() {
        return repo.findAll();
    }

    @PostMapping
    public UsuarioServicio crear(@RequestBody UsuarioServicio u) {
        return repo.save(u);
    }

    @GetMapping("/{id}")
    public UsuarioServicio obtener(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public UsuarioServicio actualizar(@PathVariable Long id, @RequestBody UsuarioServicio u) {
        u.setIdUsuario(id);
        return repo.save(u);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
