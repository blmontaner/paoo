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
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import uy.edu.ort.paoo.datos.dominio.Pagina;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;
import uy.edu.ort.paoo.util.Utilidades;

/**
 *
 * Se encarga de manejar todo lo relacionado con los PDFs
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ProcesadorPDF {

    /**
     * Programa que deseo convertir a PDF
     *
     * @param programa programa para convertir a PDF.
     * @throws PaooException
     */
    public static void generarProgramasPDF(Programa programa) throws PaooException {
        if (programa != null) {
            if (!programa.getNombre().isEmpty()) {
                for (Pagina pagina : programa.getPaginas()) {
                    crearPDF(programa.getNombre(), pagina.getNombre(), pagina.getBody());
                }
            }
        } else {
            throw new PaooException("No existe el programa que intenta convertir. Verifique y vuelta a intentar.");
        }
    }
    private static final String PATH_PROGRAMAS = "PathProgramas";

    /**
     * Funcion auxiliar para crear un archivo PDF
     *
     * @param carpeta destino donde se va a crear el archivo PDF
     * @param nombre nombre del archivo PDF
     * @param html contenido del PDF en formato HTML.
     */
    private static void crearPDF(String carpeta, String nombre, String html) throws PaooException {

        String pathDirectorio = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(PATH_PROGRAMAS) + carpeta;
        if (!Utilidades.existeDirectorio(pathDirectorio)) {
            Utilidades.crearDirectorio(pathDirectorio);
        }

        String path = pathDirectorio + "/" + nombre + ".pdf";

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

            //InputStreamReader fis = new InputStreamReader(new FileInputStream(pathInput));
            InputStreamReader fis = new InputStreamReader(new ByteArrayInputStream(html.getBytes()));
            //get the XMLWorkerHelper Instance
            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            //convert to PDF
            worker.parseXHtml(pdfWriter, document, fis);

            //close the document
            document.close();
            //close the writer
            pdfWriter.close();

        } catch (IOException | DocumentException e) {
            throw new PaooException(e.getMessage());
        }

    }
}
