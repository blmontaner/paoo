package uy.edu.ort.paoo.datos.dao;

import java.util.List;
import uy.edu.ort.paoo.datos.DatosPaooException;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public interface IDAO <T> {
	
	void save(T entity) throws DatosPaooException;
	void delete(T entity) throws DatosPaooException;
	T getByPK(Object id) throws DatosPaooException;
	List<T> getAll() throws DatosPaooException;
	List<T> getByProperty(String prop, Object val) throws DatosPaooException;
}