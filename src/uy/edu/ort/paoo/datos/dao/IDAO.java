package uy.edu.ort.paoo.datos.dao;

import java.util.List;

/*
* Interfaz
*/
public interface IDAO <T> {
	
	void save(T entity);
	void delete(T entity);
	T getByPK(Object id);
	List<T> getAll();
	List<T> getByProperty(String prop, Object val);
}