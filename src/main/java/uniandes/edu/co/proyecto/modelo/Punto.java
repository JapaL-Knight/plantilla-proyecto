package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PUNTO")
public class Punto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double longitud;

    @Column(nullable = false)
    private double latitud;

    @Column(nullable = false, length = 200)
    private String direccion;

    @Column(nullable = false, length = 100)
    private String ciudad;

    @Column(nullable = false)
    private int orden;

    // relación con Servicio
    @ManyToOne
    @JoinColumn(name = "idServicio", nullable = false)
    private Servicio servicio;

    public void setId(Long id2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }

    // getters y setters
}
