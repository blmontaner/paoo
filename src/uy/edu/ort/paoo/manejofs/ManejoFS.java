/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.manejofs;

import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;

/**
 *
 * @author Victor
 */
public class ManejoFS {
    
    public static void crearDirectorio(String nombre) throws PaooException{
        String path = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathProgramas") + nombre;
        Utilidades.crearDirectorio(path);
    }
    
    public static void crearArchivoHtml(String directorio, String html, String nombre) throws PaooException{
        String path = ManejoPropiedades.obtenerInstancia().obtenerPropiedad("PathProgramas") + directorio + "/" + nombre + ".html";
        Utilidades.crearArchivo(html, path);
    }
            
}