/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.presentacion.swing;

import java.awt.Frame;
import javax.swing.JOptionPane;
import uy.edu.ort.paoo.negocio.procesadorxml.Resultado;

/**
 *
 * @author bruno
 */


public class DisplayResultado {

    
    public static void showResultado(Frame parent,String titulo,Resultado resultado){
        if(resultado.getIsOK()){
            JOptionPane.showMessageDialog(parent, resultado.toString(), titulo, JOptionPane.INFORMATION_MESSAGE);
        }
        if(resultado.getIsError()){
            JOptionPane.showMessageDialog(parent, resultado.toString(), titulo, JOptionPane.WARNING_MESSAGE);
        }
        if(resultado.getIsExceptio()){
            JOptionPane.showMessageDialog(parent, resultado.toString(), titulo, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}
