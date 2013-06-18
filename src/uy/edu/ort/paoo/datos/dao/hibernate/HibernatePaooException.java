package uy.edu.ort.paoo.datos.dao.hibernate;

import uy.edu.ort.paoo.datos.DatosPaooException;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class HibernatePaooException extends DatosPaooException {
    public HibernatePaooException(String message){
        super(message);
    }
}
