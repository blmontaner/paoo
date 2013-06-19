package uy.edu.ort.paoo.datos.dao.hibernate;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import uy.edu.ort.paoo.datos.DatosPaooException;

import uy.edu.ort.paoo.datos.dao.IClienteDAO;
import uy.edu.ort.paoo.datos.dominio.Cliente;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ClienteDAO extends HibernateBase implements IClienteDAO {
    
    /**
     * Guardar un cliente en la Base de Datos
     * 
     * @param entity Cliente a salvar
     * @throws DatosPaooException  
     */
    @Override
    public void save(Cliente entity) throws DatosPaooException {
        try 
        { 
            iniciarOperacion(); 
            sesion.saveOrUpdate(entity); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            throw new HibernatePaooException(he.getMessage());
        } finally 
        { 
            sesion.close(); 
        }  
    }

    /**
     *
     * @param entity
     */
    @Override
    public void delete(Cliente entity) {
        // TODO Auto-generated method stub
    }
    
    /**
     * Metodo para borrar todos los clientes. Se utiliza solo para los tests
     *
     * @throws HibernatePaooException
     */
    public void deleteAll() throws HibernatePaooException{
        try {
            iniciarOperacion(); 
            String hql = String.format("delete from %s","clientes");
            Query query = sesion.createQuery(hql);
            query.executeUpdate();
        } catch (HibernateException ex) {
            throw new HibernatePaooException(ex.getMessage());
        } finally 
        { 
            sesion.close(); 
        }  
    }

    /**
     * Obtengo todos los clientes
     *
     * @return
     * @throws HibernatePaooException
     */
    @Override
    public List<Cliente> getAll() throws HibernatePaooException {
        List<Cliente> resultado;
        try 
        { 
            iniciarOperacion(); 
            
            String consulta = "from Cliente";
            resultado = sesion.createQuery(consulta).list();
            //tx.commit(); 
        } catch (HibernateException he) 
        { 
            throw new HibernatePaooException(he.getMessage());
        } finally 
        { 
            sesion.close(); 
        }  
        
        return resultado;
    }

    /**
     * Obtengo una lista de Clientes que cumplan con el valor
     * :val de la propiedad :prop
     *
     * @param prop Atributo de comparacion para obtener los Clientes
     * @param val Valor del atributo de comparacion
     * @return Lista de Clientes que cumplen la condicion
     * @throws HibernatePaooException
     */
    @Override
    public List<Cliente> getByProperty(String prop, Object val) throws HibernatePaooException {
        List<Cliente> resultado;
        try 
        { 
            iniciarOperacion(); 
            
            String consulta = "from Cliente where " + prop + " = :val";
            Query query = sesion.createQuery(consulta);
            query.setParameter("val", (String)val);
            resultado = query.list();
            //tx.commit(); 
        } catch (HibernateException he) 
        { 
            throw new HibernatePaooException(he.getMessage());
        } finally 
        { 
            sesion.close(); 
        }  
        return resultado;
    }
    
    /*
     * Asumimos que PK en caso del Cliente es el Identifador
     */
    /**
     * Obtenemos un cliente por su atributo identificador
     *
     * @param id identificador del Cliente
     * @return Cliente que cumple con el identificador, null en otro caso
     * @throws HibernatePaooException
     */
    @Override
    public Cliente getByPK(Object id) throws HibernatePaooException {
        try {
            List<Cliente> clientes = getByProperty("identificador", id);
            if(!clientes.isEmpty())
                return clientes.get(0);
            return null;
        } catch (HibernatePaooException ex) {
            throw ex;
        }
    }
    
    
}
