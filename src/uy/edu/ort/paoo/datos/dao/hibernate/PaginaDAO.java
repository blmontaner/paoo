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

    /**
     * Salvo una entidad Pagina en la BD
     *
     * @param entity
     * @throws HibernatePaooException
     */
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

    /**
     * No implementado
     *
     * @param entity
     */
    @Override
    public void delete(Pagina entity) {
        // TODO Auto-generated method stub
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Pagina getByPK(Object id) {
        return null;
    }

    /**
     * Ontenemos todas las Paginas de la BD
     *
     * @return
     * @throws HibernatePaooException
     */
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

    /**
     * Obtengo una lista de Paginas que cumplan con el valor
     * :val de la propiedad :prop
     *
     * @param prop Atributo de comparacion para obtener las Paginas
     * @param val Valor del atributo de comparacion
     * @return Lista de Paginas que cumplen la condicion
     * @throws HibernatePaooException
     */
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
