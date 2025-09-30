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
@Table(name = "PUNTO")
public class Punto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "punto_seq")
    @SequenceGenerator(name = "punto_seq", sequenceName = "ALPESCAB_SEQUENCE", allocationSize = 1)
    @Column(name = "IDPUNTO")
    private Long idPunto;
    @Column(name = "IDCUIDAD")
    private Long idCiudad;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "LONGITUD")
    private Double longitud;
    @Column(name = "LATITUD")
    private Double latitud;
    @Column(name = "ORDEN")
    private Integer orden;


    @ManyToOne
    @JoinColumn(name = "IDSERVICIO")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "IDCIUDAD", nullable = false)
    private Ciudad ciudad;



    public Long getIdPunto() { return idPunto; }
    public void setIdPunto(Long idPunto) { this.idPunto = idPunto; }

    public Long getIdCiudad() { return idCiudad; }
    public void setIdCiudad(Long idCiudad) { this.idCiudad = idCiudad; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }

    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }

    public Servicio getServicio() { return servicio; }
    public void setServicio(Servicio servicio) { this.servicio = servicio; }
}
