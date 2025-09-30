package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "SERVICIO")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servicio_seq")
    @SequenceGenerator(name = "servicio_seq", sequenceName = "SERVICIO_SEQ", allocationSize = 1)
    @Column(name = "IDSERVICIO")
    private Long idServicio;

    @Column(name = "DISTANCIAKM")
    private Double distanciaKm;

    @Column(name = "IDTARIFA")
    private Long idTarifa; 

    @Column(nullable = false)
    private String tipoServicio;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @Column(nullable = false)
    private double costo;

    @ManyToOne
    @JoinColumn(name = "idUsuarioConductor")
    private UsuarioConductor usuarioConductor;

    @ManyToOne
    @JoinColumn(name = "idUsuarioServicio")
    private UsuarioServicio usuarioServicio;

    @ManyToOne
    @JoinColumn(name = "idVehiculo")
    private Vehiculo vehiculo;

    @OneToMany(mappedBy = "servicio")
    private List<Punto> puntos;

    @OneToMany(mappedBy = "servicio")
    private List<Revision> revisiones;

    @OneToMany(mappedBy = "servicio")
    private List<Viaje> viajes;

    public void setIdServicio(Long id) {
        this.idServicio = id;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public UsuarioServicio getUsuarioServicio() {
        return usuarioServicio;
    }

    public void setUsuarioServicio(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    public UsuarioConductor getUsuarioConductor() {
        return usuarioConductor;
    }

    public void setUsuarioConductor(UsuarioConductor usuarioConductor) {
        this.usuarioConductor = usuarioConductor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Punto> getPuntos() {
        return puntos;
    }

    public void setPuntos(List<Punto> puntos) {
        this.puntos = puntos;
    }

    public List<Revision> getRevisiones() {
        return revisiones;
    }

    public void setRevisiones(List<Revision> revisiones) {
        this.revisiones = revisiones;
    }

    public List<Viaje> getViajes() {
        return viajes;
    }

    public void setViajes(List<Viaje> viajes) {
        this.viajes = viajes;
    }

    public Long getIdServicio() {
        return idServicio;
    }
    public Double getDistanciaKm() {
        return distanciaKm;
    }
    public void setDistanciaKm(Double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }
    public Long getIdTarifa() {
        return idTarifa;
    }
    public void setIdTarifa(Long idTarifa) {
        this.idTarifa = idTarifa;
    }
}
