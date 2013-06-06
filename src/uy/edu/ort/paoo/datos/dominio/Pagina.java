package uy.edu.ort.paoo.datos.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PAGINAS")
public class Pagina extends EntidadPersistente {

    @Column(name="NOMBRE")
    private String nombre;
    @Column(name="BODY")
    private String body;
    @Column(name="PESO")
    private long peso;
    @Column(name="LINEAS")
    private long lineas;

    public Pagina() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getPeso() {
        return peso;
    }

    public void setPeso(long peso) {
        this.peso = peso;
    }

    /**
     * @return the lineas
     */
    public long getLineas() {
        return lineas;
    }

    /**
     * @param lineas the lineas to set
     */
    public void setLineas(long lineas) {
        this.lineas = lineas;
    }

    @Override
    public String toString() {
        return "Pagina [nombre: " + nombre + "]";
    }
}
