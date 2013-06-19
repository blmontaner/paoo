package uy.edu.ort.paoo.datos.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
@Entity
@Table(name = "CLIENTES")
@XmlRootElement
public class Cliente extends EntidadPersistente{

    @Column(name="NOMBRE",nullable = false)
    private String nombre;
    @Column(name="DIRECCION")
    private String direccion;
    @Column(name="IDENTIFICADOR", unique = true, nullable = false)
    private String identificador;
    @Column(name="TELEFONO")
    private long telefono;
    @Column(name="PAGINA")
    private String pagina;

    /**
     *
     */
    public Cliente() {
    }

    /**
     *
     * @return
     */
    @XmlElement
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
    @XmlElement
    public String getDireccion() {
        return direccion;
    }

    /**
     *
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public String getIdentificador() {
        return identificador;
    }

    /**
     *
     * @param identificador
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public long getTelefono() {
        return telefono;
    }

    /**
     *
     * @param telefono
     */
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public String getPagina() {
        return pagina;
    }

    /**
     *
     * @param pagina
     */
    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    @Override
    public String toString() {
        return "Cliente [nombre: " + nombre + ", direccion: " + direccion
                + ", identificador: " + identificador + ", telefono: " + telefono
                + ", pagina: " + pagina + "]";
    }

    /**
     *
     * @param obj
     * @return
     */
    public boolean Equals(Object obj) {
        return this.getIdentificador().equals(((Cliente) obj).getIdentificador());
    }
}
