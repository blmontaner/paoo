package uy.edu.ort.paoo.negocio.procesadorxml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uy.edu.ort.paoo.datos.dominio.Cliente;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "clientes")
public class ClientesLista {

	@XmlElement(name = "cliente", type = Cliente.class)
	private List<Cliente> clientes = new ArrayList<Cliente>();

	public ClientesLista() {
	}

	public ClientesLista(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setBooks(List<Cliente> clientes) {
		this.clientes = clientes;
	}
}
