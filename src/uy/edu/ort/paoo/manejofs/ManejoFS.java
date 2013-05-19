/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.manejofs;

import java.io.File;
import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;

/**
 *
 * @author Victor
 */
public class ManejoFS {
    
    /**
     *
     * @param nombre
     * @throws PaooException
     */
    public static void crearDirectorio(String nombre) throws PaooException{
        String path = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathProgramas") + nombre;
        Utilidades.crearDirectorio(path);
    }
    
    /**
     *
     * @param directorio
     * @param html
     * @param nombre
     * @return
     * @throws PaooException
     */
    public static File crearArchivoHtml(String directorio, String html, String nombre) throws PaooException{
        String path = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathProgramas") + directorio + "/" + nombre + ".html";
        return Utilidades.crearArchivo(html, path);
    }
            
}