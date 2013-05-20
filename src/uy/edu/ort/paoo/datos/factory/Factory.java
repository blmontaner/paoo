package uy.edu.ort.paoo.datos.factory;

import java.util.logging.Level;
import java.util.logging.Logger;
import uy.edu.ort.paoo.datos.dao.IClienteDAO;
import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
import uy.edu.ort.paoo.datos.dao.memoria.ProgramaDAO;
import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;

public class Factory {

    private static final String FABRICA_CLIENTES = "ClienteDAO";
    private static final String FABRICA_PROGRAMAS = "ProgramaDAO";
    
    //TODO: usar reflection
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
}
