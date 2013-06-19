/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.negocio.procesadorxml;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import uy.edu.ort.paoo.datos.dao.memoria.DB;
import uy.edu.ort.paoo.datos.dominio.Cliente;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.negocio.facade.NegocioFacade;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ProcesadorTest {
    
    /**
     * Test of ingresarClientes method, of class Procesador.
     */
    @Test
    public void testIngresarClientes() throws Exception {
        System.out.println("ingresarClientes");
        
        DB.getInstance().setClientes(new ArrayList<Cliente>());
        
        String pathRecursos = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathRecursos");
        
        String url = pathRecursos + "clientes_test.xml";
        
        Resultado result = NegocioFacade.cargarClientes(url);
        assertTrue(result.getProcesados() > 0);
    }

    /**
     * Test of cargarProgramas method, of class Procesador.
     */
    @Test
    public void testCargarProgramas() throws Exception {
        
        System.out.println("cargarProgramas");
        DB.getInstance().setClientes(new ArrayList<Cliente>());
        DB.getInstance().setProgramas(new ArrayList<Programa>());
        
        String pathRecursos = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathRecursos");
                
        NegocioFacade.cargarClientes(pathRecursos + "clientes_test.xml");
        NegocioFacade.cargarProgramas(pathRecursos + "programas_test.xml");
        
        List result = NegocioFacade.programasSolicitados("");
        assertEquals(1, result.size());
    }
}