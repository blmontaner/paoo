package uy.edu.ort.paoo.datos.dao.memoria;

import java.util.List;

import uy.edu.ort.paoo.datos.dao.IClienteDAO;
import uy.edu.ort.paoo.datos.dominio.Cliente;

public class ClienteDAO implements IClienteDAO{

	@Override
	public void save(Cliente entity) {
		DB.getInstance().getClientes().add(entity);
	}

	@Override
	public void delete(Cliente entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Cliente> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> getByProperty(String prop, Object val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente getByPK(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

}
