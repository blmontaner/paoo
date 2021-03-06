package uy.edu.ort.paoo.negocio.facade;

import java.util.ArrayList;
import java.util.List;
import uy.edu.ort.paoo.datos.DatosPaooException;

import uy.edu.ort.paoo.datos.dao.IClienteDAO;
import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
import uy.edu.ort.paoo.datos.dominio.Cliente;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.datos.factory.Factory;
import uy.edu.ort.paoo.negocio.NegocioPaooException;
import uy.edu.ort.paoo.negocio.procesadorhtml.ProcesadorHTML;
import uy.edu.ort.paoo.negocio.procesadorpdf.ProcesadorPDF;
import uy.edu.ort.paoo.negocio.procesadorxml.Procesador;
import uy.edu.ort.paoo.negocio.procesadorxml.Resultado;

/**
 * Se encarga de los metodos a exponer del negocio para ser usados por la UI
 *
 * @author Bruno Montaner
 * @author Victor Nessi
 *
 */
public class NegocioFacade {

    /**
     * Cargar Clientes Con la url de un archivo xml conteniendo clientes los
     * valida parsea e inserta en la DB
     *
     * @param url direccion del archivo xml a procesar para cargar clientes
     * @return Resultado objeto con las propiedades resultado de haber cargado
     * Clientes
     * @throws NegocioPaooException 
     */
    public static Resultado cargarClientes(String url) throws NegocioPaooException {
        return Procesador.ingresarClientes(url);
    }

    /**
     * Cargar Programas Con la url de un archivo xml conteniendo programas los
     * valida parsea e inserta en la DB
     *
     * @param url direccion del archivo xml a procesar para cargar programas
     * @return Resultado propiedades resultado de haber cargado programas
     * @throws NegocioPaooException  
     */
    public static Resultado cargarProgramas(String url) throws NegocioPaooException {
        Resultado resultado = Procesador.cargarProgramas(url);
        return resultado;
    }

    /**
     * Programas solicitados. Se ingresa nombre de programa y se muestran sus
     * datos, si no se ingresa nada se muestran todos los programas generados.
     *
     * @param nombreProg Nombre del programa o vacio para ver todos
     * @return List<Programa> Lista de programas que cumplen con la condicion
     * nombreProg
     * @throws NegocioPaooException  
     */
    public static List<Programa> programasSolicitados(String nombreProg) throws NegocioPaooException {
        try {
            List<Programa> ret = new ArrayList<>();
            IProgramaDAO daoProg = Factory.getProgramaDAO();
            if (nombreProg.equals("")) {
                ret = daoProg.getAll();
            } else {
                ret = new ArrayList<>();
                ret.add(daoProg.getByPK(nombreProg));
            }

            return ret;
        } catch (DatosPaooException ex) {
            throw new NegocioPaooException(ex.getMessage());
        }
    }

    /**
     * Listado de clientes. Opcionalmente esta consulta permite dado un
     * identificador de cliente mostrar los datos de un cliente.
     *
     * @param idCliente identificador Cliente para ver uno o vacio para ver
     * todos
     * @return
     * @throws NegocioPaooException 
     */
    public static List<Cliente> listadoClientes(String idCliente) throws NegocioPaooException {
        try {
            List<Cliente> ret;
            IClienteDAO daoCli = Factory.getClienteDAO();
            if (idCliente.equals("")) {
                ret = daoCli.getAll();
            } else {
                ret = new ArrayList<>();
                ret.add(daoCli.getByPK(idCliente));
            }
            return ret;
        } catch (DatosPaooException ex) {
            throw new NegocioPaooException(ex.getMessage());
        }
    }

    /**
     * Programas que solicito un cliente. Se ingresa el identificador de cliente
     * y se listan los programas solicitados por este.
     *
     * @param idCliente identificador Cliente
     * @return List<Programa> Listado de Programas que solicito un determinado
     * cliente
     * @throws NegocioPaooException  
     */
    public static List<Programa> programasSolicitadosCliente(String idCliente) throws NegocioPaooException {
        try {
            Cliente c = Factory.getClienteDAO().getByPK(idCliente);
            if(c != null){
                return Factory.getProgramaDAO().getByProperty(Programa.PROPIEDAD_CLIENTE, c.getId().toString());
            }
            return new ArrayList<Programa>();
        } catch (DatosPaooException ex) {
            throw new NegocioPaooException(ex.getMessage());
        }
    }

    /**
     * Top 10 Programas con mayor cantidad de paginas. Se listan los 10
     * programas que tienen mayor cantidad de paginas.
     *
     * @return List<Programa>
     * @throws NegocioPaooException  
     */
    public static List<Programa> topProgramasNroPaginas() throws NegocioPaooException {
        try {
            return Factory.getProgramaDAO().getTop10MasPaginas();
        } catch (DatosPaooException ex) {
            throw new NegocioPaooException(ex.getMessage());
        }
    }

    /**
     * Top 10 Programas con mayor cantidad de paginas. Se listan los 10
     * programas que tienen mayor cantidad de paginas.
     *
     * @return List<Programa>
     * @throws NegocioPaooException 
     */
    public static List<Programa> topProgramasMasPesados() throws NegocioPaooException {
        try {
            return Factory.getProgramaDAO().getTop10MasPesados();
        } catch (DatosPaooException ex) {
            throw new NegocioPaooException(ex.getMessage());
        }
    }

    /**
     * Operacion que crear la esctructura de directorios y genera los HTMLs
     * correspondientes al nombreProg
     *
     * @param nombreProg nombre de programa que quiero generar sus archivos
     * HTMLs
     * @throws NegocioPaooException  
     */
    public static void generarHTML(String nombreProg) throws NegocioPaooException {
        try {
            Programa p = Factory.getProgramaDAO().getByPK(nombreProg);
            ProcesadorHTML.generarProgramasHTML(p);
        } catch (DatosPaooException ex) {
            throw new NegocioPaooException(ex.getMessage());
        }
    }

    /**
     * Operacion que crear la esctructura de directorios y genera los PDFs
     * correspondientes al nombreProg
     *
     * @param nombreProg nombre de programa que quiero generar sus archivos PDFs
     * @throws NegocioPaooException  
     */
    public static void generarPDF(String nombreProg) throws NegocioPaooException {
        try {
            Programa p = Factory.getProgramaDAO().getByPK(nombreProg);
            ProcesadorPDF.generarProgramasPDF(p);
        } catch (DatosPaooException ex) {
            throw new NegocioPaooException(ex.getMessage());
        }
    }
}
