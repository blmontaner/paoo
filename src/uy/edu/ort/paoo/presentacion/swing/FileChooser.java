/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.presentacion.swing;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import uy.edu.ort.paoo.presentacion.PresentacionPaooException;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;

/**
 *
 * @author Bruno
 */
public class FileChooser {
    
    private JFileChooser fc;
    private JFrame frame;
    
    public FileChooser(String titulo, JFrame frame){
        this.frame=frame;
        String ruta = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathRecursos");
        fc = new JFileChooser(ruta);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(filter);
        fc.setDialogTitle(titulo);
    }
    
    public String showChooser() throws PresentacionPaooException{
        int returnVal = fc.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                return file.getCanonicalPath();
            } catch (IOException ex) {
                throw new PresentacionPaooException("No se pudo abrir el archivo");
            }
        }
        return "";
    }
    
    
}
