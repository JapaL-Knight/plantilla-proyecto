package uniandes.edu.co.proyecto.modelo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "SERVICIO")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;

    @Column(nullable = false)
    private String tipoServicio; // TRANSPORTE, DOMICILIO, MERCANCIA

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private double costo;

    // relaciones
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIdServicio'");
    }

    // getters y setters
    public String getTipoServicio() {
        return tipoServicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getCosto() {
        return costo;
    }

    public UsuarioServicio getUsuarioServicio() {
        return usuarioServicio;
    }

    public UsuarioConductor getUsuarioConductor() {
        return usuarioConductor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Long getIdServicio() {
        return idServicio;
    }
}
