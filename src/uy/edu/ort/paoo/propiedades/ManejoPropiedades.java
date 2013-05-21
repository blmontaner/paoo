package uy.edu.ort.paoo.propiedades;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de manejar las propiedades del Sistema.
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ManejoPropiedades {

    private static ManejoPropiedades instancia;
    private static Properties propiedades = new Properties();

    private ManejoPropiedades() {
        try {
            cargarProperties();
        } catch (IOException ex) {
            Logger.getLogger(ManejoPropiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtengo una instancia de ManejoPropiedades
     *
     * @return
     */
    public static ManejoPropiedades obtenerInstancia() {
        if (instancia == null) {
            instancia = new ManejoPropiedades();
        }
        return instancia;
    }

    /**
     * Metodo para obtener una propiedad especifica del archivo de propiedades.
     *
     * @param clave
     * @return
     */
    public String obtenerPropiedad(String clave) {
        return propiedades.getProperty(clave);
    }

    private static void cargarProperties() throws IOException {
        try {
            //se crea una instancia a la clase Properties
            propiedades = new Properties();
            //se leen el archivo .properties
            String workingDir = System.getProperty("user.dir");
            String path = workingDir + "/Configuracion/Propiedades.properties";
            propiedades.load(new FileInputStream(path));

        } catch (IOException ex) {
            throw ex;
        }
    }
}
