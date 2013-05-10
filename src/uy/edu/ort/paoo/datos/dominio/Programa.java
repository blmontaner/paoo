package uy.edu.ort.paoo.datos.dominio;

import java.util.List;

public class Programa {
	private Cliente cliente;
	private String nombre;
	private List<Pagina> paginas;
	
	public Programa(){}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Pagina> getPaginas() {
		return paginas;
	}
	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}
}
