package uy.edu.ort.paoo.datos.dao.memoria;

import java.util.List;

import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
import uy.edu.ort.paoo.datos.dominio.Programa;

public class ProgramaDAO implements IProgramaDAO{

	@Override
	public void save(Programa entity) {
		DB.getInstance().getProgramas().add(entity);
	}

	@Override
	public void delete(Programa entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Programa getByPK(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Programa> getAll() {
		return DB.getInstance().getProgramas();
	}

	@Override
	public List<Programa> getByProperty(String prop, Object val) {
		// TODO Auto-generated method stub
		return null;
	}

}
