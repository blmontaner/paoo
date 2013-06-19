package uy.edu.ort.paoo.datos.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
@Entity
@Table(name = "PAGINAS")
public class Pagina extends EntidadPersistente {

    @Column(name="NOMBRE")
    private String nombre;
    @Column(name="BODY", columnDefinition="text")
    private String body;
    @Column(name="PESO")
    private long peso;
    @Column(name="LINEAS")
    private long lineas;

    /**
     *
     */
    public Pagina() {
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     *
     * @return
     */
    public long getPeso() {
        return peso;
    }

    /**
     *
     * @param peso
     */
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
