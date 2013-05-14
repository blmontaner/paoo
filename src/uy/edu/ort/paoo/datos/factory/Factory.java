package uy.edu.ort.paoo.datos.factory;

import uy.edu.ort.paoo.datos.dao.IClienteDAO;
import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
import uy.edu.ort.paoo.datos.dao.memoria.ClienteDAO;
import uy.edu.ort.paoo.datos.dao.memoria.ProgramaDAO;

public class Factory {

	public static IClienteDAO getClienteDAO(){
		return new ClienteDAO();
	}
	
	public static IProgramaDAO getProgramaDAO(){
		return new ProgramaDAO();
	}
}
