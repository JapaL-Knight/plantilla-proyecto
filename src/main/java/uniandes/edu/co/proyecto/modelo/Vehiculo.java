package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "VEHICULO")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehiculo_seq")
    @SequenceGenerator(name = "vehiculo_seq", sequenceName = "VEHICULO_SEQ", allocationSize = 1)
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

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public UsuarioConductor getUsuarioConductor() {
        return usuarioConductor;
    }

    public void setUsuarioConductor(UsuarioConductor usuarioConductor) {
        this.usuarioConductor = usuarioConductor;
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }
}
