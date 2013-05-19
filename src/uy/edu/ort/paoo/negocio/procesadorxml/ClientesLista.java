package uy.edu.ort.paoo.negocio.procesadorxml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uy.edu.ort.paoo.datos.dominio.Cliente;

/**
 *
 * @author Victor
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "clientes")
public class ClientesLista {

	@XmlElement(name = "cliente", type = Cliente.class)
	private List<Cliente> clientes = new ArrayList<Cliente>();

	/**
     *
     */
    public ClientesLista() {
	}

	/**
     *
     * @param clientes
     */
    public ClientesLista(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	/**
     *
     * @return
     */
    public List<Cliente> getClientes() {
		return clientes;
	}

	/**
     *
     * @param clientes
     */
    public void setBooks(List<Cliente> clientes) {
		this.clientes = clientes;
	}
        
        /**
     *
     * @param cliente
     * @return
     */
    public boolean existeCliente(Cliente cliente)
        {
            for (Cliente c : clientes) {
                if(c.Equals(cliente))
                {
                    return true;
                }
            }
            return false;
        }
}
