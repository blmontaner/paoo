package uy.edu.ort.paoo.presentacion;

import uy.edu.ort.paoo.exceptions.PaooException;
import uy.edu.ort.paoo.negocio.procesadorxml.ProcesadorProgramas;

public class ConsolaUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			ProcesadorProgramas.ingresarClientes("Clientes.xml");
		} catch (PaooException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
