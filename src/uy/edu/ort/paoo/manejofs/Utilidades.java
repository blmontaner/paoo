/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.manejofs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;

/**
 *
 * @author timba
 */
public class Utilidades {
    
    /**
     *
     * @param sb
     * @param path
     * @return
     * @throws PaooException
     */
    public static File crearArchivo(String sb, String path) throws PaooException {
        File f = new File(path);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(sb);
            fw.flush();
            fw.close();
            return f;
        } catch (IOException ex) {
            throw new PaooException(ex.getMessage());
        }
    }
    
    /**
     *
     * @param path
     * @throws PaooException
     */
    public static void crearDirectorio(String path) throws PaooException {
        //File dir = new File(ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathProgramas") + nombre);
        File dir = new File(path);
        dir.mkdir();
    }
}
