/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.negocio;

import uy.edu.ort.paoo.exceptions.PaooException;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class NegocioPaooException extends PaooException {
    
    public NegocioPaooException(String message){
        super(message);
    }
}
