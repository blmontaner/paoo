package uy.edu.ort.paoo.negocio.facade;

import java.util.ArrayList;
import java.util.List;

import uy.edu.ort.paoo.datos.dao.IClienteDAO;
import uy.edu.ort.paoo.datos.dao.IDAO;
import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
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
	
	/** Cargar Clientes
	 *  Con la url de un archivo xml conteniendo clientes
	 *  los valida parsea e inserta en la DB
	 * 
	 * @param url direccion del archivo xml a procesar para cargar clientes
	 * @return Resultado
	 * @throws PaooException
	 */
	public static Resultado cargarClientes(String url) throws PaooException {
		return ProcesadorProgramas.ingresarClientes(url);
	}
	
	/** Cargar Programas 
	 *  Con la url de un archivo xml conteniendo programas
	 *  los valida parsea e inserta en la DB
	 * 
	 * @param url direccion del archivo xml a procesar para cargar programas
	 * @return Resultado
	 * @throws PaooException
	 */
	public static Resultado cargarProgramas(String url) throws PaooException {
		return ProcesadorProgramas.procesarProgramas(url);
	}
	
	/** Programas solicitados. 
	 *  Se ingresa nombre de programa y se muestran sus datos,
	 *  si no se ingresa nada se muestran todos los programas generados.
	 * 
	 * @param nombreProg Nombre del programa o vacio para ver todos
	 * @return List<Programa>
	 */
	public static List<Programa> programasSolicitados(String nombreProg){
		List<Programa> ret;
		IProgramaDAO daoProg = Factory.getProgramaDAO();
		if(nombreProg.equals("")){
			ret = daoProg.getAll();
		}else{
			ret = new ArrayList<>();
			ret.add(daoProg.getByPK(nombreProg));
		}
		return ret;
		
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
		IClienteDAO daoCli = Factory.getClienteDAO();
		if(idCliente.equals("")){
			ret = daoCli.getAll();
		}else{
			ret = new ArrayList<>();
			ret.add(daoCli.getByPK(idCliente));
		}
		return ret;
	}
	
	/** Programas que solicito un cliente. 
	 * Se ingresa el identificador de cliente y se listan los programas solicitados por este.
	 * 
	 * @param idCliente 	identificador Cliente
	 * @return List<Programa>
	 */
	public static List<Programa> programasSolicitadosCliente(String idCliente){
		return null;
	}
	
	/** Top 10 Programas con mayor cantidad de páginas.
	 * Se listan los 10 programas que tienen mayor cantidad de páginas.
	 * 
	 * @return List<Programa>
	 */
	public static List<Programa> topProgramasNroPaginas(){
		return Factory.getProgramaDAO().getTop10MasPaginas();
	}
	
	/** Top 10 Programas con mayor cantidad de páginas.
	 * Se listan los 10 programas que tienen mayor cantidad de páginas.
	 * 
	 * @return List<Programa>
	 */
	public static List<Programa> topProgramasMasPesados(){
		return Factory.getProgramaDAO().getTop10MasPesados();
	}

}
