package uy.edu.ort.paoo.datos.dao.hibernate;

import java.util.List;
import org.hibernate.HibernateException;

import uy.edu.ort.paoo.datos.dao.IPaginaDAO;
import uy.edu.ort.paoo.datos.dominio.Pagina;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class PaginaDAO extends HibernateBase implements IPaginaDAO {

    @Override
    public void save(Pagina entity) throws HibernatePaooException {
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
    public void delete(Pagina entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public Pagina getByPK(Object id) {
        return null;
    }

    @Override
    public List<Pagina> getAll() throws HibernatePaooException {
        List<Pagina> resultado;
        try 
        { 
            iniciarOperacion(); 
            
            String consulta = "from Pagina";
            resultado = sesion.createQuery(consulta).list();
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
    public List<Pagina> getByProperty(String prop, Object val) throws HibernatePaooException {
        List<Pagina> resultado;
        try 
        { 
            iniciarOperacion(); 
            
            String consulta = "from Cliente where :prop = :val";
            resultado = sesion.createQuery(consulta).setString("prop", prop).setString("val", (String)val).list();
        } catch (HibernateException he) 
        { 
            throw new HibernatePaooException(he.getMessage());
        } finally 
        { 
            sesion.close(); 
        }  
        return resultado;
    }
}
