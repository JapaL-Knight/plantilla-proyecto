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

import uniandes.edu.co.proyecto.modelo.Vehiculo;
import uniandes.edu.co.proyecto.repositorio.VehiculoRepository;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoRepository repo;

    @GetMapping
    public List<Vehiculo> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Vehiculo crear(@RequestBody Vehiculo v) {
        return repo.save(v);
    }

    @GetMapping("/{id}")
    public Vehiculo obtener(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Vehiculo actualizar(@PathVariable Long id, @RequestBody Vehiculo v) {
        v.setIdVehiculo(id);
        return repo.save(v);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
