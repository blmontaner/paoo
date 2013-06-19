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
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class DisplayResultado {

    
    /**
     * Metodo para mostrar resultados en Swing
     *
     * @param parent Frame padre
     * @param titulo Titulo del Frame de Resultados
     * @param resultado Resultado a mostrar
     */
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
