/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.presentacion.swing;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;
import uy.edu.ort.paoo.propiedades.PropiedadesPaooException;

/**
 *
 * @author Bruno
 */
public class FileChooser {

    private JFileChooser fc;
    private JFrame frame;

    public FileChooser(String titulo, JFrame frame) throws PropiedadesPaooException {
        try {
            this.frame = frame;
            String ruta = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathRecursos");
            fc = new JFileChooser(ruta);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
            fc.setAcceptAllFileFilterUsed(false);
            fc.addChoosableFileFilter(filter);
            fc.setDialogTitle(titulo);
        } catch (PropiedadesPaooException ex) {
            throw ex;
        }
    }

    public String showChooser() throws PropiedadesPaooException {
        int returnVal = fc.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                return file.getCanonicalPath();
            } catch (IOException ex) {
                throw new PropiedadesPaooException("No se pudo abrir el archivo");
            }
        }
        return "";
    }
}
