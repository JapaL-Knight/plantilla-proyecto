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
@Table(name = "VEHICULO")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;

    @Column(nullable = false, unique = true)
    private String placa;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private int capacidadPasajeros;

    @Column(nullable = false)
    private String nivel; // LARGE, COMFORT, ESTANDAR

    // relación con conductor
    @ManyToOne
    @JoinColumn(name = "idUsuarioConductor")
    private UsuarioConductor usuarioConductor;

    public void setIdVehiculo(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIdVehiculo'");
    }

    // getters y setters
}
