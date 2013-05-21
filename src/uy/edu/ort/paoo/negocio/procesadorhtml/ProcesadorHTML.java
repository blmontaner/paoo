/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.negocio.procesadorhtml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import uy.edu.ort.paoo.datos.dominio.Pagina;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;
import uy.edu.ort.paoo.util.Utilidades;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ProcesadorHTML {

    public static void generarProgramasHTML(Programa programa) throws PaooException {
        if (programa != null) {
            if (!programa.getNombre().isEmpty()) {
                if (!existeDirectorio(programa.getNombre())) {
                    crearDirectorio(programa.getNombre());
                }
                for (Pagina pagina : programa.getPaginas()) {
                    File f = crearArchivoHtml(programa.getNombre(), pagina.getBody(), pagina.getNombre());
                    //Ahora tengo que actualizar el tamanio de la pagina y la cantidad de lineas
                    pagina.setLineas(obtenerLineasArchivo(f));
                    pagina.setPeso(f.length());
                }
            }
        } else {
            throw new PaooException("No existe el programa que intenta convertir. Verifique y vuelta a intentar.");
        }

    }
    private static final String PATH_PROGRAMAS = "PathProgramas";

    /**
     *
     * @param nombre
     * @throws PaooException
     */
    private static void crearDirectorio(String nombre) throws PaooException {
        String path = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(PATH_PROGRAMAS) + nombre;
        Utilidades.crearDirectorio(path);
    }

    /**
     *
     * @param nombre
     * @throws PaooException
     */
    private static boolean existeDirectorio(String nombre) throws PaooException {
        String path = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(PATH_PROGRAMAS) + nombre;
        return Utilidades.existeDirectorio(path);
    }

    /**
     *
     * @param directorio
     * @param html
     * @param nombre
     * @return
     * @throws PaooException
     */
    private static File crearArchivoHtml(String directorio, String html, String nombre) throws PaooException {
        String path = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(PATH_PROGRAMAS) + directorio + "/" + nombre + ".html";
        return Utilidades.crearArchivo(html, path);
    }

    /**
     *
     * @param f
     * @return
     * @throws PaooException
     * @throws IOException
     */
    private static long obtenerLineasArchivo(File f) throws PaooException {
        try {
            LineNumberReader lnr = new LineNumberReader(new FileReader(f));
            lnr.skip(Long.MAX_VALUE);
            return lnr.getLineNumber() + 1;
        } catch (IOException ex) {
            throw new PaooException(ex.getMessage());
        }
    }
}
