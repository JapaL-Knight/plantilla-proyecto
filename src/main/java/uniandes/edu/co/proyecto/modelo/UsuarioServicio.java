package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIO_SERVICIO")
public class UsuarioServicio extends Usuario {

    @Column(nullable = false, unique = true)
    private String numTarjeta;

    @Column(nullable = false)
    private String nombreTarjeta;

    @Column(nullable = false)
    private String vencimiento; 

    @Column(nullable = false)
    private int codigoSeguridad;

    // Getters y Setters
    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(int codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }
}
