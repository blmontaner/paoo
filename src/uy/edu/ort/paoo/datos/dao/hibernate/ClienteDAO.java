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
     *
     * @param entity Cliente a salvar
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

    @Override
    public void delete(Cliente entity) {
        // TODO Auto-generated method stub
    }

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
