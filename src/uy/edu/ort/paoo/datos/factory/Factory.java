package uy.edu.ort.paoo.datos.factory;

import uy.edu.ort.paoo.datos.dao.IClienteDAO;
import uy.edu.ort.paoo.datos.dao.IPaginaDAO;
import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;

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
     * @throws PaooException
     */
    public static IClienteDAO getClienteDAO() throws PaooException {

        String fabricaClientes = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(FABRICA_CLIENTES);

        Object clienteDAO = null;
        try {
            Class clienteImpl = Class.forName(fabricaClientes);
            clienteDAO = clienteImpl.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new PaooException(ex.getMessage());
        }
        return (IClienteDAO) clienteDAO;
    }

    /**
     *
     * @return una instancia de DAO Programas, especificada en el archivo
     * properties.
     * @throws PaooException
     */
    public static IProgramaDAO getProgramaDAO() throws PaooException {

        String fabricaProgramas = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(FABRICA_PROGRAMAS);

        Object programaDAO = null;
        try {
            Class programaImpl = Class.forName(fabricaProgramas);
            programaDAO = programaImpl.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new PaooException(ex.getMessage());
        }
        return (IProgramaDAO) programaDAO;
    }
    
    /**
     *
     * @return una instancia de DAO Pagina, especificada en el archivo
     * properties.
     * @throws PaooException
     */
    public static IPaginaDAO getPaginaDAO() throws PaooException {

        String fabricaPaginas = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(FABRICA_PAGINAS);

        Object paginaDAO = null;
        try {
            Class pagImpl = Class.forName(fabricaPaginas);
            paginaDAO = pagImpl.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new PaooException(ex.getMessage());
        }
        return (IPaginaDAO) paginaDAO;
    }
}
