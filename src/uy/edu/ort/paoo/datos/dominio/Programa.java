package uy.edu.ort.paoo.datos.dominio;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
@Entity
@Table(name = "PROGRAMAS")
public class Programa extends EntidadPersistente {

    public static String PROPIEDAD_CLIENTE = "CLIENTE_FK";
    
    @OneToOne (cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="CLIENTE_FK")
    private Cliente cliente;
    
    @Column(name="NOMBRE")
    private String nombre;
    
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="ID_PROGRAMA")
    private List<Pagina> paginas;

    /**
     *
     */
    public Programa() {
        paginas = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     *
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
    public List<Pagina> getPaginas() {
        return paginas;
    }

    /**
     *
     * @param paginas
     */
    public void setPaginas(List<Pagina> paginas) {
        this.paginas = paginas;
    }
    /**
     *
     * @return
     */
    public long getPesoTotal() {
        long ret = 0;
        for (Pagina p : getPaginas()) {
            ret += p.getPeso();
        }
        return ret;
    }
    /**
     *
     * @return
     */
    public int getCantidadPaginas() {
        return getPaginas().size();
    }

    @Override
    public String toString() {
        return "Programa [cliente: " + cliente.getIdentificador() + ", nombre: " + nombre
                + " w pags: " + getPesoTotal()
                + " nro pags: " + getCantidadPaginas() + ", paginas: " + paginas + "]";
    }
}
