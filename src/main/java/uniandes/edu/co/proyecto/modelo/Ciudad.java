package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CIUDAD")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ciudad_seq")
    @SequenceGenerator(name = "ciudad_seq", sequenceName = "CIUDAD_SEQ", allocationSize = 1)
    private Long idCiudad;

    private String nombre;

    // Getters y Setters
    public Long getIdCiudad() { return idCiudad; }
    public void setIdCiudad(Long idCiudad) { this.idCiudad = idCiudad; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
