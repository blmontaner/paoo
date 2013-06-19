package uy.edu.ort.paoo.negocio.procesadorhtml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import uy.edu.ort.paoo.datos.DatosPaooException;
import uy.edu.ort.paoo.datos.dao.IPaginaDAO;
import uy.edu.ort.paoo.datos.dominio.Pagina;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.datos.factory.Factory;
import uy.edu.ort.paoo.negocio.NegocioPaooException;
import uy.edu.ort.paoo.propiedades.ManejoPropiedades;
import uy.edu.ort.paoo.propiedades.PropiedadesPaooException;
import uy.edu.ort.paoo.util.UtilPaooException;
import uy.edu.ort.paoo.util.Utilidades;

/**
 *
 * Se encarga de manejar todo lo relacionado con los HTML (crearlos, obtener
 * tamanio, cantidad de lineas)
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ProcesadorHTML {

    /**
     * Programa que quiero convertir a PDF, si le paso un objeto nulo, retorno
     * una excepcion.
     *
     * @param programa programa que deseo convertir a PDF
     * @throws NegocioPaooException 
     */
    public static void generarProgramasHTML(Programa programa) throws NegocioPaooException {
        if (programa != null) {
            if (!programa.getNombre().isEmpty()) {
                try {
                    IPaginaDAO paginaDAO = Factory.getPaginaDAO();
                    if (!existeDirectorio(programa.getNombre())) {
                        crearDirectorio(programa.getNombre());
                    }
                    for (Pagina pagina : programa.getPaginas()) {
                        File f = crearArchivoHtml(programa.getNombre(), pagina.getBody(), pagina.getNombre());
                        //Ahora tengo que actualizar el tamanio de la pagina y la cantidad de lineas
                        pagina.setLineas(obtenerLineasArchivo(f));
                        pagina.setPeso(f.length());
                        paginaDAO.save(pagina);
                    }
                } catch (DatosPaooException ex) {
                    throw new NegocioPaooException(ex.getMessage());
                }
            }
        } else {
            throw new NegocioPaooException("No existe el programa que intenta convertir. Verifique y vuelta a intentar.");
        }

    }
    private static final String PATH_PROGRAMAS = "PathProgramas";

    /**
     * Funcion auxiliar para crear los directorios donde se van a alojar los
     * HTMLs
     *
     * @param nombre nombre del directorio que deseo crear
     * @throws NegocioPaooException
     */
    private static void crearDirectorio(String nombre) throws NegocioPaooException {
        try {
            String path = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(PATH_PROGRAMAS) + nombre;
            Utilidades.crearDirectorio(path);
        } catch (PropiedadesPaooException ex) {
            throw new NegocioPaooException(ex.getMessage());
        }
    }

    /**
     * Metodo auxiliar para consultar si ya existe un directorio
     *
     * @param nombre nombre del directorio que deseo saber si existe
     * @throws NegocioPaooException
     */
    private static boolean existeDirectorio(String nombre) throws NegocioPaooException {
        try {
            String path = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(PATH_PROGRAMAS) + nombre;
            return Utilidades.existeDirectorio(path);
        } catch (PropiedadesPaooException ex) {
            throw new NegocioPaooException(ex.getMessage());
        }
    }

    /**
     * Metodo auxiliar para crear un archivo HTML, retorna un File para obtener
     * sus propiedades y actualizar (lineas y peso) del objeto Pagina.
     *
     * @param directorio Directorio donde se va a cerar el archivo HTML
     * @param html Contenido en formato HTML del archivo
     * @param nombre nombre del archivo HTML.
     * @return
     * @throws NegocioPaooException
     */
    private static File crearArchivoHtml(String directorio, String html, String nombre) throws NegocioPaooException {
        try {
            String path = ManejoPropiedades.obtenerInstancia().obtenerPropiedad(PATH_PROGRAMAS) + directorio + "/" + nombre + ".html";
            return Utilidades.crearArchivo(html, path);
        } catch (UtilPaooException | PropiedadesPaooException ex) {
            throw new NegocioPaooException(ex.getMessage());
        }
    }

    /**
     * Metodo auxiliar para obtener la cantidad de lineas de un archivo HTML.
     *
     * @param f Archivo para contar sus lineas
     * @return
     * @throws NegocioPaooException
     */
    private static long obtenerLineasArchivo(File f) throws NegocioPaooException {
        try {
            LineNumberReader lnr = new LineNumberReader(new FileReader(f));
            lnr.skip(Long.MAX_VALUE);
            return lnr.getLineNumber() + 1;
        } catch (IOException ex) {
            throw new NegocioPaooException(ex.getMessage());
        }
    }
}
