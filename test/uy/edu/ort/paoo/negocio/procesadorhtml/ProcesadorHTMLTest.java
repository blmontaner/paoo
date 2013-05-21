/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.negocio.procesadorhtml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uy.edu.ort.paoo.datos.dominio.Pagina;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;

/**
 *
 * @author timba
 */
public class ProcesadorHTMLTest {

    public String htmlBody = "<html>\n"
            + "<body>\n"
            + "<h1>Catalog</h1>\n"
            + "<ul>\n"
            + "<li>Jay-Pea-Eyes Aka Junior Private Investigators\n"
            + "by John Priest</li>\n"
            + "<li>Rainy Day Poems\n"
            + "by James McDonald (Goodreads Author)</li>\n"
            + "<li>Jack Templar, Monster Hunter (The Templar Chronicles, #1)\n"
            + "by Jeff Gunhus (Goodreads Author)</li>\n"
            + "</ul>\n"
            + "</body>\n"
            + "</html>";

    /**
     * Test of generarProgramasHTML method, of class ProcesadorHTML.
     */
    @Test
    public void testGenerarProgramasHTML() throws Exception {
        System.out.println("generarProgramasHTML");
        
        String pathProgramas = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathProgramas");
        
        List<Programa> programas = new ArrayList<Programa>();

        Pagina pagina1 = new Pagina();
        pagina1.setBody(htmlBody);
        pagina1.setNombre("Test");
        
        Programa programa = new Programa();
        programa.setNombre("Test Programa");
        programa.getPaginas().add(pagina1);
        
        programas.add(programa);
        
        ProcesadorHTML.generarProgramasHTML(programa);
        
        //Chequeo que se haya creado el directorio para el programa
        File directorioPrograma = new File(pathProgramas + programa.getNombre());
        Assert.assertTrue(directorioPrograma.exists());
        
        //Chequeo que se haya creado el archivo HTML correctamente
        File archivoHtml = new File(pathProgramas + programa.getNombre() + "/" + pagina1.getNombre() + ".html");
        Assert.assertTrue(archivoHtml.exists());
        
        //recupero la pagina y chequeo de que se completaron correctamente los atributos Lineas y Length
        Pagina paginaAux = programas.get(0).getPaginas().get(0);
        Assert.assertNotSame(0L, paginaAux.getLineas());
        Assert.assertNotSame(0L, paginaAux.getPeso());
        
    }
    
    /**
     * Test of generarProgramasHTML method, of class ProcesadorHTML.
     */
    @Test
    public void testGenerarProgramasHTMLVacio() throws Exception {
        System.out.println("generarProgramasHTML");
        
        String pathProgramas = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathProgramas");
        
        List<Programa> programas = new ArrayList<Programa>();

        Pagina pagina1 = new Pagina();
        pagina1.setBody("");
        pagina1.setNombre("Test");
        
        Programa programa = new Programa();
        programa.setNombre("Test Programa");
        programa.getPaginas().add(pagina1);
        
        programas.add(programa);
        
        ProcesadorHTML.generarProgramasHTML(programa);
        
        //Chequeo que se haya creado el directorio para el programa
        File directorioPrograma = new File(pathProgramas + programa.getNombre());
        Assert.assertTrue(directorioPrograma.exists());
        
        //Chequeo que se haya creado el archivo HTML correctamente, aunque el html este vacio
        File archivoHtml = new File(pathProgramas + programa.getNombre() + "/" + pagina1.getNombre() + ".html");
        Assert.assertTrue(archivoHtml.exists());
        
        //recupero la pagina y chequeo de que se completaron correctamente los atributos Lineas y Length
        Pagina paginaAux = programas.get(0).getPaginas().get(0);
        Assert.assertSame(1L, paginaAux.getLineas());
        Assert.assertSame(0L, paginaAux.getPeso());
        
    }
}