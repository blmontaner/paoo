package uy.edu.ort.paoo.datos.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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

    public Cliente() {
    }

    @XmlElement
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlElement
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    @XmlElement
    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    @XmlElement
    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    @Override
    public String toString() {
        return "Cliente [nombre: " + nombre + ", direccion: " + direccion
                + ", identificador: " + identificador + ", telefono: " + telefono
                + ", pagina: " + pagina + "]";
    }

    public boolean Equals(Object obj) {
        return this.getIdentificador().equals(((Cliente) obj).getIdentificador());
    }
}
