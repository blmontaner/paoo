/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.propiedades;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ManejoPropiedadesTest {
    
    /**
     * Test of obtenerInstancia method, of class ManejoPropiedades.
     */
    @Test
    public void testObtenerInstancia() {
        try {
            System.out.println("obtenerInstancia");
            
            ManejoPropiedades result = ManejoPropiedades.obtenerInstancia();
            assertNotNull(result);
        } catch (PropiedadesPaooException ex) {
            fail();
        }
    }

    /**
     * Test of obtenerPropiedad method, of class ManejoPropiedades.
     */
    @Test
    public void testObtenerPropiedad() {
        try {
            System.out.println("obtenerPropiedad");
            
            String clave = "PathProgramas";
            ManejoPropiedades instance = ManejoPropiedades.obtenerInstancia();
            String expResult = "";
            String result = instance.obtenerPropiedad(clave);
            assertNotSame(expResult, result);
        } catch (PropiedadesPaooException ex) {
            fail();
        }
    }
}