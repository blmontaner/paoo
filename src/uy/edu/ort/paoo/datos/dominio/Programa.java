package uy.edu.ort.paoo.datos.dominio;

import java.util.ArrayList;
import java.util.List;

public class Programa {
	private Cliente cliente;
	private String nombre;
	private List<Pagina> paginas;
	
	public Programa(){
		paginas = new ArrayList<>();
	}
	
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
	public long getPesoTotal(){
		long ret=0;
		for(Pagina p : getPaginas()){
			ret +=p.getPeso();
		}
		return ret;
	}
	public int getCantidadPaginas(){
		return getPaginas().size();
	}

	@Override
	public String toString() {
		return "Programa [cliente: " + cliente.getIdentificador() + ", nombre: " + nombre
				+" w pags: "+getPesoTotal()
				+ " nro pags: "+getCantidadPaginas()+", paginas: " + paginas + "]";
	}
	
}
