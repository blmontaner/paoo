package uy.edu.ort.paoo.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;
import uy.edu.ort.paoo.util.UtilPaooException;
import uy.edu.ort.paoo.util.Utilidades;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class PaooException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @param message mensaje de la Excepcion
     */
    public PaooException(String message) {
        super(message);
        try {
            Utilidades.getLogFile().log(Level.SEVERE, message, this);
        } catch (UtilPaooException ex) {
            Logger.getLogger(PaooException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
