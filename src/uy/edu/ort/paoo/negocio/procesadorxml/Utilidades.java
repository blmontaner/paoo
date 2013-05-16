package uy.edu.ort.paoo.negocio.procesadorxml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import uy.edu.ort.paoo.exceptions.PaooException;

/**
 *
 * @author Victor
 */
public class Utilidades {

    public static boolean validarXMLContraXSD(File xml, File xsd) throws PaooException {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsd);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            return true;
        } catch (SAXException | IOException ex) {
            throw new PaooException(ex.getMessage());
        }
    }

    public static void crearArchivo(StringBuffer sb) throws PaooException {
        File f = new File("src/resources/porfolio.html");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(sb.toString());
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            throw new PaooException(ex.getMessage());
        }
    }
}
