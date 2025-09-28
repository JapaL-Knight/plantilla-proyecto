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
@Table(name = "REVISION")
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIdRevision'");
    }

    // getters y setters
}
