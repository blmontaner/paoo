package uy.edu.ort.paoo.datos.dao.hibernate;

import uy.edu.ort.paoo.datos.DatosPaooException;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class HibernatePaooException extends DatosPaooException {
    /**
     *
     * @param message Mensaje de Exception
     */
    public HibernatePaooException(String message){
        super(message);
    }
}
