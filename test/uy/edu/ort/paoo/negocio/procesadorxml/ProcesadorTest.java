/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.negocio.procesadorxml;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ProcesadorTest {
    
    /**
     * Test of procesarProgramas method, of class Procesador.
     */
    @Test
    public void testProcesarProgramas() throws Exception {
        System.out.println("procesarProgramas");
        String ruta = "";
        Resultado expResult = null;
        Resultado result = Procesador.procesarProgramas(ruta);
        assertEquals(expResult, result);
    }

    /**
     * Test of ingresarClientes method, of class Procesador.
     */
    @Test
    public void testIngresarClientes() throws Exception {
        System.out.println("ingresarClientes");
        String nombreArchivo = "";
        Resultado expResult = null;
        Resultado result = Procesador.ingresarClientes(nombreArchivo);
        assertEquals(expResult, result);
    }

    /**
     * Test of cargarProgramas method, of class Procesador.
     */
    @Test
    public void testCargarProgramas() throws Exception {
        System.out.println("cargarProgramas");
        String nombreXML = "";
        Resultado expResult = null;
        Resultado result = Procesador.cargarProgramas(nombreXML);
        assertEquals(expResult, result);
    }
}