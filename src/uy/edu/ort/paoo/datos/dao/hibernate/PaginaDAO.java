package uy.edu.ort.paoo.datos.dao.hibernate;

import java.util.List;
import org.hibernate.HibernateException;

import uy.edu.ort.paoo.datos.dao.IPaginaDAO;
import uy.edu.ort.paoo.datos.dominio.Cliente;
import uy.edu.ort.paoo.datos.dominio.Pagina;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class PaginaDAO extends HibernateBase implements IPaginaDAO {

    @Override
    public void save(Pagina entity) {
        try 
        { 
            iniciarOperacion(); 
            sesion.save(entity); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        }
    }

    @Override
    public void delete(Pagina entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public Pagina getByPK(Object id) {
        return null;
    }

    @Override
    public List<Pagina> getAll() {
        List<Pagina> resultado;
        try 
        { 
            iniciarOperacion(); 
            
            String consulta = "from Pagina";
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
    public List<Pagina> getByProperty(String prop, Object val) {
        List<Pagina> resultado;
        try 
        { 
            iniciarOperacion(); 
            
            String consulta = "from Cliente where :prop = :val";
            resultado = sesion.createQuery(consulta).setString("prop", prop).setString("val", (String)val).list();
            //tx.commit(); 
        } catch (HibernateException he) 
        { 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        }  
        return resultado;
    }
}
