package uy.edu.ort.paoo.datos.dao.hibernate;

import java.util.Collections;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
import uy.edu.ort.paoo.datos.dominio.Pagina;
import uy.edu.ort.paoo.datos.dominio.Programa;
import uy.edu.ort.paoo.datos.dominio.ProgramaComparator;
import uy.edu.ort.paoo.datos.dominio.ProgramaComparator.EnumProgramaComparator;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class ProgramaDAO extends HibernateBase implements IProgramaDAO {

    @Override
    public void save(Programa entity) throws HibernatePaooException {
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
    public void delete(Programa entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public Programa getByPK(Object id) throws HibernatePaooException {
        try {
            List<Programa> progs = getByProperty("nombre", id);
            if(!progs.isEmpty())
                return progs.get(0);
            return null;
        } catch (HibernatePaooException ex) {
            throw new HibernatePaooException(ex.getMessage());
        }
    }

    @Override
    public List<Programa> getAll() throws HibernatePaooException {
        List<Programa> resultado;
        try 
        { 
            iniciarOperacion(); 
            
            String consulta = "from Programa";
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
    public List<Programa> getByProperty(String prop, Object val) throws HibernatePaooException {
        List<Programa> resultado;
        try 
        { 
            iniciarOperacion(); 
            
            String consulta = "from Programa where "+prop +"= :val";
            Query query = sesion.createQuery(consulta);
            query.setParameter("val", (String)val);
            resultado = query.list();
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
     * Obtiene los 10 programas con mayor cantidad de Paginas
     *
     * @return Lista con los 10 programas que contienen mas paginas
     */
    @Override
    public List<Programa> getTop10MasPaginas() throws HibernatePaooException {
        try {
            ProgramaComparator comp = new ProgramaComparator();
            comp.setComparator(EnumProgramaComparator.COMPARATOR_PAGINAS);
            List<Programa> progs = getAll();
            Collections.sort(progs, comp);
            if (progs.size() >= 9) {
                return progs.subList(0, 9);
            } else {
                return progs;
            }
        } catch (HibernatePaooException ex) {
            throw new HibernatePaooException(ex.getMessage());
        }
    }

    /**
     * Obtiene la lista de los 10 programas con paginas mas pesadas
     *
     * @return Lista de 10 programas con la suma de sus paginas mas pesados
     */
    @Override
    public List<Programa> getTop10MasPesados() throws HibernatePaooException {
        try {
            ProgramaComparator comp = new ProgramaComparator();
            comp.setComparator(EnumProgramaComparator.COMPARATOR_PESO);
            List<Programa> progs = getAll();
            Collections.sort(progs, comp);
            if (progs.size() >= 9) {
                return progs.subList(0, 9);
            } else {
                return progs;
            }
        } catch (HibernatePaooException ex) {
            throw new HibernatePaooException(ex.getMessage());
        }
    }

    /**
     * Obtengo la lista de Paginas que corresponden a un programa.
     *
     * @param idProg Identificador del programa q quiero obtener las paginas
     * @return Lista de Paginas que corresponden a un programa
     * @throws HibernatePaooException
     */
    @Override
    public List<Pagina> getPaginasPrograma(long idProg) throws HibernatePaooException {
        List<Pagina> resultado;
        try 
        { 
            iniciarOperacion(); 
            
            String consulta = "from Pagina where ID_PROGRAMA = :val";
            Query query = sesion.createQuery(consulta);
            query.setParameter("val", (Long)idProg);
            resultado = query.list();
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
