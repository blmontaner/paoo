/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.negocio.procesadorpdf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import uy.edu.ort.paoo.datos.dominio.Pagina;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ProcesadorPDFTest {
    
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
     * Test of generarProgramasPDF method, of class ProcesadorPDF.
     */
    @Test
    public void testGenerarProgramasPDF() throws Exception {
        System.out.println("generarProgramasPDF");
        
        String pathProgramas = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathProgramas");
        
        List<Programa> programas = new ArrayList<Programa>();

        Pagina pagina1 = new Pagina();
        pagina1.setBody(htmlBody);
        pagina1.setNombre("Test");
        
        Programa programa = new Programa();
        programa.setNombre("Test Programa");
        programa.getPaginas().add(pagina1);
        
        programas.add(programa);
        
        ProcesadorPDF.generarProgramasPDF(programa);
        
        //Chequeo que se haya creado el directorio para el programa
        File directorioPrograma = new File(pathProgramas + programa.getNombre());
        Assert.assertTrue(directorioPrograma.exists());
        
        //Chequeo que se haya creado el archivo PDF correctamente
        File archivoPDF = new File(pathProgramas + programa.getNombre() + "/" + pagina1.getNombre() + ".pdf");
        Assert.assertTrue(archivoPDF.exists());
        
    }

}