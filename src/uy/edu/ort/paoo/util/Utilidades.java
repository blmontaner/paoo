/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import uy.edu.ort.paoo.exceptions.PaooException;

/**
 *
 * @author 
 */
public class Utilidades {
    
    /**
     *
     * @param sb
     * @param path
     * @return
     * @throws PaooException
     */
    public static File crearArchivo(String sb, String path) throws PaooException {
        File f = new File(path);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(sb);
            fw.flush();
            fw.close();
            return f;
        } catch (IOException ex) {
            throw new PaooException(ex.getMessage());
        }
    }
    
    /**
     *
     * @param path
     * @throws PaooException
     */
    public static void crearDirectorio(String path) throws PaooException {
        File dir = new File(path);
        dir.mkdir();
    }
    
    
    /**
     *
     * @param path
     * @throws PaooException
     */
    public static boolean existeDirectorio(String path) throws PaooException {
        File dir = new File(path);
        return dir.exists();
    }
    
    /**
     *
     */
    public static Pattern pattern;

    static {
        pattern = Pattern.compile(
                "# Match a valid Windows filename (unspecified file system).          \n"
                + "^                                # Anchor to start of string.        \n"
                + "(?!                              # Assert filename is not: CON, PRN, \n"
                + "  (?:                            # AUX, NUL, COM1, COM2, COM3, COM4, \n"
                + "    CON|PRN|AUX|NUL|             # COM5, COM6, COM7, COM8, COM9,     \n"
                + "    COM[1-9]|LPT[1-9]            # LPT1, LPT2, LPT3, LPT4, LPT5,     \n"
                + "  )                              # LPT6, LPT7, LPT8, and LPT9...     \n"
                + "  (?:\\.[^.]*)?                  # followed by optional extension    \n"
                + "  $                              # and end of string                 \n"
                + ")                                # End negative lookahead assertion. \n"
                + "[^<>:\"/\\\\|?*\\x00-\\x1F]*     # Zero or more valid filename chars.\n"
                + "[^<>:\"/\\\\|?*\\x00-\\x1F\\ .]  # Last char is not a space or dot.  \n"
                + "$                                # Anchor to end of string.            ",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.COMMENTS);
    }

    /**
     * Validar XML contra XSD Se ingresa el File del xml y xsd. Retorna si es
     * valido o no.
     *
     * @param xml File del xml que va a ser validado
     * @param xsd File del xsd que valida
     * @return Un boolean que indica si es valido el xml
     */
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

    /**
     *
     * @param text
     * @return
     */
    public static boolean isValidName(String text) {
        Matcher matcher = pattern.matcher(text);
        boolean isMatch = matcher.matches();
        return isMatch;
    }
}
