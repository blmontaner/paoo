package uy.edu.ort.paoo.presentacion;

import java.io.InputStream;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import uy.edu.ort.paoo.datos.dao.memoria.DB;
import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.negocio.procesadorxml.ProcesadorProgramas;

public class ConsolaUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			ProcesadorProgramas.ingresarClientes("src/uy/edu/ort/paoo/recursos/Clientes.xml");
                        System.out.println(DB.getInstance().getClientes());
                        ProcesadorProgramas.cargarProgramas("src/uy/edu/ort/paoo/recursos/programas.xml", "src/uy/edu/ort/paoo/recursos/validadorProgramasXSD.xsd");
		} catch (PaooException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
        

}
