/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.datos.factory;

import org.junit.Test;
import static org.junit.Assert.*;
import uy.edu.ort.paoo.datos.dao.IClienteDAO;
import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class FactoryTest {
    
    /**
     * Test of getClienteDAO method, of class Factory.
     */
    @Test
    public void testGetClienteDAO() throws Exception {
        String FABRICA_CLIENTES = "ClienteDAO";
        String fabricaClientes = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(FABRICA_CLIENTES);
        System.out.println("getClienteDAO");
        IClienteDAO result = Factory.getClienteDAO();
        System.out.println(result.getClass().getName());
        assertEquals(fabricaClientes, result.getClass().getName());
    }

    /**
     * Test of getProgramaDAO method, of class Factory.
     */
    @Test
    public void testGetProgramaDAO() throws Exception {
        System.out.println("getProgramaDAO");
        String FABRICA_PROGRAMAS = "ProgramaDAO";
        String fabricaClientes = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(FABRICA_PROGRAMAS);
        IProgramaDAO result = Factory.getProgramaDAO();
        assertEquals(fabricaClientes, result.getClass().getName());
    }
}