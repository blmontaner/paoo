/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.negocio.procesadorpdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import uy.edu.ort.paoo.datos.dominio.Pagina;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ProcesadorPDF {

    public static void generarProgramasPDF(List<Programa> programas) throws PaooException {
        if (!programas.isEmpty()) {
            for (Programa programa : programas) {
                if (!programa.getNombre().isEmpty()) {
                    for (Pagina pagina : programa.getPaginas()) {
                        crearPDF(pagina.getNombre(), programa.getNombre());
                    }
                }
            }
        }
    }
    private static final String PATH_PROGRAMAS = "PathProgramas";

    /**
     *
     * @param pathInput
     * @param carpeta
     * @param nombrePDF
     */
    public static void crearPDF(String nombre, String carpeta) {

        String path = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(PATH_PROGRAMAS) + carpeta + "/" + nombre + ".pdf";

        PdfWriter pdfWriter = null;

        //create a new document
        Document document = new Document();

        try {

            //get Instance of the PDFWriter
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(path));

            //document header attributes
            document.addAuthor("paoo");
            document.addCreationDate();
            document.addProducer();
            document.setPageSize(PageSize.LETTER);

            //open document
            document.open();
            String pathInput = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(PATH_PROGRAMAS) + carpeta + "/" + nombre + ".html";

            InputStreamReader fis = new InputStreamReader(new FileInputStream(pathInput));

            //get the XMLWorkerHelper Instance
            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            //convert to PDF
            worker.parseXHtml(pdfWriter, document, fis);

            //close the document
            document.close();
            //close the writer
            pdfWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException | DocumentException e) {
            System.out.println(e);
        }

    }
}
