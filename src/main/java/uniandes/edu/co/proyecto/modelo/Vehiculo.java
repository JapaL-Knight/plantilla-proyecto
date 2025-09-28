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
        this.idVehiculo = id;
    }

    // getters y setters
    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public String getNivel() {
        return nivel;
    }

    public UsuarioConductor getUsuarioConductor() {
        return usuarioConductor;
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }
}
