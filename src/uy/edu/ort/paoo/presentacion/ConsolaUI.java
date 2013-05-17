package uy.edu.ort.paoo.presentacion;

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
import java.util.Properties;
import uy.edu.ort.paoo.negocio.procesadorxml.ProcesadorProgramas;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;

public class ConsolaUI {

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            ProcesadorProgramas.ingresarClientes("src/uy/edu/ort/paoo/recursos/Clientes.xml");
             //System.out.println(DB.getInstance().getClientes());
             ProcesadorProgramas.cargarProgramas("src/uy/edu/ort/paoo/recursos/programas.xml", "src/uy/edu/ort/paoo/recursos/validadorProgramasXSD.xsd");
             

            //String pdfFilename = "/Users/timba/NetBeansProjects/paoo/src/uy/edu/ort/paoo/recursos/newhtml.html";
            //createPDF(pdfFilename);
            
            /*ManejoPropiedades m = new ManejoPropiedades();
            Properties p = m.getProperties();
            System.out.println(p.getProperty("Test"));*/
            

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void createPDF(String pdfFilename) {

        //path for the PDF file to be generated
        String path = "src/uy/edu/ort/paoo/recursos/newhtml.pdf"; //"docs/" + pdfFilename;
        PdfWriter pdfWriter = null;

        //create a new document
        Document document = new Document();

        try {

            //get Instance of the PDFWriter
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(path));

            //document header attributes
            document.addAuthor("betterThanZero");
            document.addCreationDate();
            document.addProducer();
            document.addCreator("MySampleCode.com");
            document.addTitle("Demo for iText XMLWorker");
            document.setPageSize(PageSize.LETTER);

            //open document
            document.open();

            //To convert a HTML file from the filesystem
            //String File_To_Convert = "docs/SamplePDF.html";
            //FileInputStream fis = new FileInputStream(File_To_Convert);

            //URL for HTML page
            //URL myWebPage = new URL("http://demo.mysamplecode.com/");
            String pathInput = "src/uy/edu/ort/paoo/recursos/newhtml.html";
            InputStreamReader fis = new InputStreamReader(new FileInputStream(pathInput));
               
            
            //HTMLWorker htmlWorker = new HTMLWorker(document);
            //htmlWorker.parse(fis);
            
            //get the XMLWorkerHelper Instance
            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            //convert to PDF
            worker.parseXHtml(pdfWriter, document, fis);

            
            //close the document
            document.close();
            //close the writer
            pdfWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}
