package uy.edu.ort.paoo.negocio.facade;

import java.util.ArrayList;
import java.util.List;

import uy.edu.ort.paoo.datos.dominio.Cliente;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.datos.factory.Factory;
import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.negocio.procesadorxml.ProcesadorProgramas;
import uy.edu.ort.paoo.negocio.procesadorxml.Resultado;

/** Se encarga de los metodos a exponer del negocio para ser usados por la UI
 * 
 * @author Bruno Montaner
 * @author Victor Nessi
 * */
public class NegocioFacade {
	
	public static Resultado cargarClientes(String ulr) throws PaooException {
		return ProcesadorProgramas.ingresarClientes(ulr);
	}
	
	public static Resultado cargarProgramas(String url) throws PaooException {
		return ProcesadorProgramas.procesarProgramas(url);
	}
	
	/** Programas solicitados. 
	 * Se ingresa nombre de programa y se muestran sus datos,
	 *  si no se ingresa nada se muestran todos los programas generados.
	 * 
	 * @param nombreProg 	Nombre del programa o vacio para ver todos
	 * @return 
	 */
	public static void programasSolicitados(String nombreProg){
		
	}

	/** Listado de clientes. 
	 *  Opcionalmente esta consulta permite dado un identificador
	 *  de cliente mostrar los datos de un cliente.
	 * 
	 * @param idCliente 	identificador Cliente para ver uno o vacio para ver todos
	 * @return 
	 */
	public static List<Cliente> listadoClientes(String idCliente){
		List<Cliente> ret;
		if(idCliente.equals("")){
			ret = Factory.getClienteDAO().getAll();
		}else{
			ret = new ArrayList<>();
			ret.add(Factory.getClienteDAO().getByPK(idCliente));
		}
		return ret;
	}
	
	/** Programas que solicito un cliente. 
	 * Se ingresa el identificador de cliente y se listan los programas solicitados por este.
	 * 
	 * @param idCliente 	identificador Cliente
	 * @return 
	 */
	public static void programasSolicitadosCliente(String idCliente){
		
	}
	
	/** Top 10 Programas con mayor cantidad de páginas.
	 * Se listan los 10 programas que tienen mayor cantidad de páginas.
	 * 
	 * @return 
	 */
	public static List<Programa> topProgramasNroPaginas(){
		return null;
	}
	
	/** Top 10 Programas con mayor cantidad de páginas.
	 * Se listan los 10 programas que tienen mayor cantidad de páginas.
	 * 
	 * @return 
	 */
	public static List<Programa> topProgramasMasPesados(){
		return null;
	}

}
