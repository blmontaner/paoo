package uy.edu.ort.paoo.presentacion.swing;

import java.awt.Frame;
import javax.swing.SwingWorker;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
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
