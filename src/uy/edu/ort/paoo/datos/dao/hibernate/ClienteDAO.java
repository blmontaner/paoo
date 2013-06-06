package uy.edu.ort.paoo.datos.dao.hibernate;

import uy.edu.ort.paoo.datos.dao.memoria.*;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import uy.edu.ort.paoo.datos.dao.IClienteDAO;
import uy.edu.ort.paoo.datos.dominio.Cliente;
import uy.edu.ort.paoo.negocio.procesadorxml.Resultado;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ClienteDAO implements IClienteDAO {

    private Session sesion; 
    private Transaction tx;  
    
    /**
     *
     * @param entity Cliente a salvar
     */
    @Override
    public void save(Cliente entity) {
        long id = 0;  

        try 
        { 
            iniciaOperacion(); 
            id = (Long) sesion.save(entity); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            //manejaExcepcion(he); 
            throw he; 
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
    public List<Cliente> getAll() {
        long id = 0;  
        List<Cliente> resultado;
        try 
        { 
            iniciaOperacion(); 
            
            String consulta = "from Cliente";
            resultado = sesion.createQuery(consulta).list();
            //tx.commit(); 
        } catch (HibernateException he) 
        { 
            //manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        }  
        
        return resultado;
    }

    @Override
    public List<Cliente> getByProperty(String prop, Object val) {
        List<Cliente> resultado;
        try 
        { 
            iniciaOperacion(); 
            
            String consulta = "from Cliente where :prop = :val";
            resultado = sesion.createQuery(consulta).setString("prop", prop).setString("val", (String)val).list();
            //tx.commit(); 
        } catch (HibernateException he) 
        { 
            //manejaExcepcion(he); 
            throw he; 
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
    public Cliente getByPK(Object id) {
        List<Cliente> clientes = getByProperty("IDENTIFICADOR", id);
        if(!clientes.isEmpty())
            return clientes.get(0);
        
        return null;
    }
    
    public void iniciaOperacion() throws HibernateException { 
        sesion = HibernateUtil.getSessionFactory().openSession(); 
        tx = sesion.beginTransaction(); 
    } 
}