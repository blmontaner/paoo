package uy.edu.ort.paoo.propiedades;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase encargada de manejar las propiedades del Sistema.
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ManejoPropiedades {

    private static ManejoPropiedades instancia;
    private static Properties propiedades = new Properties();

    private ManejoPropiedades() throws PropiedadesPaooException {
        cargarProperties();
    }

    /**
     * Obtengo una instancia de ManejoPropiedades
     *
     * @return
     */
    public static ManejoPropiedades obtenerInstancia() throws PropiedadesPaooException {
        if (instancia == null) {
            try {
                instancia = new ManejoPropiedades();
            } catch (PropiedadesPaooException ex) {
                throw new PropiedadesPaooException(ex.getMessage());
            }
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

    private static void cargarProperties() throws PropiedadesPaooException {
        try {
            //se crea una instancia a la clase Properties
            propiedades = new Properties();
            //se leen el archivo .properties
            String workingDir = System.getProperty("user.dir");
            String path = workingDir + "/Configuracion/Propiedades.properties";
            propiedades.load(new FileInputStream(path));

        } catch (IOException ex) {
            throw new PropiedadesPaooException(ex.getMessage());
        }
    }
}
