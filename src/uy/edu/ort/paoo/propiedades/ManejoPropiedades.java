/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.propiedades;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor
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
    
    public static ManejoPropiedades obtenerInstancia(){
        if(instancia == null){
            instancia = new ManejoPropiedades();
        }
        return instancia;
    }
    
    public String obtenerPropiedad(String clave){
        return propiedades.getProperty(clave);
    }
            
            
    private static void cargarProperties() throws IOException{
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
