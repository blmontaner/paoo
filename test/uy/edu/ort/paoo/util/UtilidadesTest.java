/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.util;

import java.io.File;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;

/**
 *
 * @author timba
 */
public class UtilidadesTest {
    
    /**
     * Test of crearArchivo method, of class Utilidades.
     */
    @Test
    public void testCrearArchivo() throws Exception {
        System.out.println("crearArchivo");
        
        String pathRecursos = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathRecursos");
        
        String sb = "TEST jUnit";
        String path = pathRecursos + "test.txt";
        
        File result = Utilidades.crearArchivo(sb, path);
        
        assertNotSame("", result.getName());
    }

    /**
     * Test of crearDirectorio method, of class Utilidades.
     */
    @Test
    public void testCrearDirectorio() throws Exception {
        System.out.println("crearDirectorio");
        
        String pathRecursos = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathRecursos");
        
        Utilidades.crearDirectorio(pathRecursos + "/testDirectorio");
        
        assertTrue(Utilidades.existeDirectorio(pathRecursos + "/testDirectorio"));
    }

    /**
     * Test of existeDirectorio method, of class Utilidades.
     */
    @Test
    public void testExisteDirectorio() throws Exception {
        System.out.println("existeDirectorio");
        
        String pathRecursos = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathRecursos");
        
        boolean result = Utilidades.existeDirectorio(pathRecursos);
        
        assertTrue(result);        
    }

    /**
     * Test of validarXMLContraXSD method, of class Utilidades.
     */
    @Test
    public void testValidarXMLContraXSD() throws Exception {
        System.out.println("validarXMLContraXSD");
        
        String pathRecursos = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathRecursos");
        
        File xml = new File(pathRecursos + "/programas.xml");
        File xsd = new File(pathRecursos + "/validadorProgramasXSD.xsd");
        
        boolean result = Utilidades.validarXMLContraXSD(xml, xsd);
        
        assertTrue(result);
    }

    /**
     * Test of isValidName method, of class Utilidades.
     */
    @Test
    public void testIsValidName() {
        System.out.println("isValidName");
        
        String text = "Test Carpeta";
        boolean result = Utilidades.isValidName(text);
        assertEquals(true, result);
        
        text = "Test/Carpeta";
        result = Utilidades.isValidName(text);
        assertEquals(false, result);
        
        text = "COM1";
        result = Utilidades.isValidName(text);
        assertEquals(false, result);
        
    }
}