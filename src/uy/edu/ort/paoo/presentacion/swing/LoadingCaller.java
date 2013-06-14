/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.presentacion.swing;

import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author Bruno
 */
public class LoadingCaller extends SwingWorker<Void, Void> {

    static Loading loading;
    public LoadingCaller(Frame parent){
         loading = new Loading(parent, true);
         loading.setTitle("Cargando...");         
    }
    
    @Override
     protected void done() {
        loading.setVisible(false);
   }

    @Override
    protected Void doInBackground() {
        loading.setVisible(true);        
        return null;
    }

}
