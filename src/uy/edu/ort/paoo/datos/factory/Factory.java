package uy.edu.ort.paoo.datos.factory;

import uy.edu.ort.paoo.datos.DatosPaooException;
import uy.edu.ort.paoo.datos.dao.IClienteDAO;
import uy.edu.ort.paoo.datos.dao.IPaginaDAO;
import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;
import uy.edu.ort.paoo.propiedades.PropiedadesPaooException;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class Factory {

    private static final String FABRICA_CLIENTES = "ClienteDAO";
    private static final String FABRICA_PROGRAMAS = "ProgramaDAO";
    private static final String FABRICA_PAGINAS = "PaginaDAO";

    /**
     *
     * @return una instancia de DAO Clientes, especificada en el archivo
     * properties.
     * @throws DatosPaooException 
     */
    public static IClienteDAO getClienteDAO() throws DatosPaooException {
        Object clienteDAO = null;
        try {
            String fabricaClientes = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(FABRICA_CLIENTES);

            Class clienteImpl = Class.forName(fabricaClientes);
            clienteDAO = clienteImpl.newInstance();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | PropiedadesPaooException ex) {
            throw new DatosPaooException(ex.getMessage());
        }
        return (IClienteDAO) clienteDAO;
    }

    /**
     *
     * @return una instancia de DAO Programas, especificada en el archivo
     * properties.
     * @throws DatosPaooException 
     */
    public static IProgramaDAO getProgramaDAO() throws DatosPaooException {
        Object programaDAO = null;
        try {
            String fabricaProgramas = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(FABRICA_PROGRAMAS);

            Class programaImpl = Class.forName(fabricaProgramas);
            programaDAO = programaImpl.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | PropiedadesPaooException ex) {
            throw new DatosPaooException(ex.getMessage());
        }
        return (IProgramaDAO) programaDAO;
    }

    /**
     *
     * @return una instancia de DAO Pagina, especificada en el archivo
     * properties.
     * @throws DatosPaooException 
     */
    public static IPaginaDAO getPaginaDAO() throws DatosPaooException {
        Object paginaDAO = null;
        try {

            String fabricaPaginas = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(FABRICA_PAGINAS);

            Class pagImpl = Class.forName(fabricaPaginas);
            paginaDAO = pagImpl.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | PropiedadesPaooException ex) {
            throw new DatosPaooException(ex.getMessage());
        }
        return (IPaginaDAO) paginaDAO;
    }
}
