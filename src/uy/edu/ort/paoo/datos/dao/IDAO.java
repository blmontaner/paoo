package uy.edu.ort.paoo.datos.dao;

import java.util.List;
import uy.edu.ort.paoo.datos.DatosPaooException;

/**
 *
 * @param <T> 
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public interface IDAO <T> {
	
	/**
     *
     * @param entity
     * @throws DatosPaooException
     */
    void save(T entity) throws DatosPaooException;
	/**
     *
     * @param entity
     * @throws DatosPaooException
     */
    void delete(T entity) throws DatosPaooException;
	/**
     *
     * @param id
     * @return
     * @throws DatosPaooException
     */
    T getByPK(Object id) throws DatosPaooException;
	/**
     *
     * @return
     * @throws DatosPaooException
     */
    List<T> getAll() throws DatosPaooException;
	/**
     *
     * @param prop
     * @param val
     * @return
     * @throws DatosPaooException
     */
    List<T> getByProperty(String prop, Object val) throws DatosPaooException;
}