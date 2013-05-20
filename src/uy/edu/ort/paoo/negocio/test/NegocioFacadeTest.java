package uy.edu.ort.paoo.negocio.test;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import uy.edu.ort.paoo.datos.dominio.Cliente;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.negocio.facade.NegocioFacade;
import uy.edu.ort.paoo.negocio.procesadorxml.Resultado;

public class NegocioFacadeTest {

	public static String dirRecursos = "src/uy/edu/ort/paoo/recursos/";
	
	@Test
	public void cargarClientesTest(){
		try {
			Resultado r= NegocioFacade.cargarClientes(dirRecursos+"4ClientesTestIdRep.xml");
			Assert.assertEquals(4, r.getProcesados());
			Assert.assertEquals(3, r.getExitosos());
			
			r= NegocioFacade.cargarClientes(dirRecursos+"4ClientesTestIdRep.xml");
			Assert.assertEquals(4, r.getProcesados());
			Assert.assertEquals(4, r.getDescartados());
			
			r= NegocioFacade.cargarClientes(dirRecursos+"5ClientesOK.xml");
			Assert.assertEquals(5, r.getProcesados());
			Assert.assertEquals(5, r.getExitosos());
			
			//No existe el archivo
			r= NegocioFacade.cargarClientes(dirRecursos+"NoFile.xml");
			fail();
		} catch (PaooException e) {
			
		}
		
	}
	@Test
	public void cargarProgramasTest(){
		//Resultado cargarProgramas(String url) throws PaooException {
		
	}
	@Test
	public void programasSolicitados(){
		//List<Programa> programasSolicitados(String nombreProg)
		
	}
	@Test
	public void listadoClientes(){
		
		try {
			NegocioFacade.cargarClientes(dirRecursos+"5ClientesOK.xml");
		} catch (PaooException e) {
			e.printStackTrace();
		}
		//Busco por id
		List<Cliente> lista = NegocioFacade.listadoClientes("CL13");
		Assert.assertEquals(1,lista.size());
		Assert.assertEquals("CL13",lista.get(0).getIdentificador());	
		
		//Devuelve todos
		lista = NegocioFacade.listadoClientes("");
		Assert.assertEquals(8,lista.size());
		
		//Busco un cliente q no existe
		lista= NegocioFacade.listadoClientes("NOTEXIST");;
		Assert.assertEquals(null,lista.get(0));
		
	}
	@Test
	public void programasSolicitadosCliente(){
		//List<Programa> programasSolicitadosCliente(String idCliente)
	}
	@Test
	public void topProgramasNroPaginas(){
		//List<Programa> topProgramasNroPaginas()
		
	}
	@Test
	public void topProgramasMasPesados(){
		//List<Programa> topProgramasMasPesados(){
		
	}
}
