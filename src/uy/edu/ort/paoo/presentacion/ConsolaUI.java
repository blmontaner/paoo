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
			/*ProcesadorProgramas.ingresarClientes("src/uy/edu/ort/paoo/recursos/Clientes.xml");
                        System.out.println(DB.getInstance().getClientes());
                        ProcesadorProgramas.cargarProgramas("src/uy/edu/ort/paoo/recursos/programasSinPaginas.xml", "src/uy/edu/ort/paoo/recursos/validadorProgramasXSD.xsd");
                        * */
                     
                    for (int i = 0; i < 10; i++) {
                        System.out.println("i >> " + i);
                        for (int j = 0; j < 10; j++) {
                            System.out.println("j >> " + j);
                            if(j == 5){
                                break;
                                System.out.println("j22 >> " + j);
                            }
                        }
                    }
                    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
        

}
