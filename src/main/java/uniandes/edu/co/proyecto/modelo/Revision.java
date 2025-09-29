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
@Table(name = "REVISION")
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revision_seq")
    @SequenceGenerator(name = "revision_seq", sequenceName = "REVISION_SEQ", allocationSize = 1)
    private Long idRevision;

    @Column(nullable = false)
    private int calificacion;

    @Column(nullable = false, length = 200)
    private String comentario;

    // relación con Servicio
    @ManyToOne
    @JoinColumn(name = "idServicio", nullable = false)
    private Servicio servicio;

    // relación con Usuario revisado
    @ManyToOne
    @JoinColumn(name = "idUsuarioRevisado", nullable = false)
    private Usuario usuarioRevisado;

    public void setIdRevision(Long id) {
        this.idRevision = id;
    }

    // getters y setters
        public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Usuario getUsuarioRevisado() {
        return usuarioRevisado;
    }

    public void setUsuarioRevisado(Usuario usuarioRevisado) {
        this.usuarioRevisado = usuarioRevisado;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getIdRevision() {
        return idRevision;
    }
}
