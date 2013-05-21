package uy.edu.ort.paoo.exceptions;

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
    }
}
