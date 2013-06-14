package uy.edu.ort.paoo.datos.dao.hibernate;

import uy.edu.ort.paoo.datos.dao.memoria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import uy.edu.ort.paoo.datos.dao.IProgramaDAO;
import uy.edu.ort.paoo.datos.dominio.Cliente;
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
    public void save(Programa entity) {
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
    public void delete(Programa entity) {
        // TODO Auto-generated method stub
    }

    @Override
    public Programa getByPK(Object id) {
        List<Programa> progs = getByProperty("nombre", id);
        if(!progs.isEmpty())
            return progs.get(0);
        
        return null;
    }

    @Override
    public List<Programa> getAll() {
        List<Programa> resultado;
        try 
        { 
            iniciarOperacion(); 
            
            String consulta = "from Programa";
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
    public List<Programa> getByProperty(String prop, Object val) {
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
            //manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        }  
        return resultado;
    }

    @Override
    public List<Programa> getTop10MasPaginas() {
        ProgramaComparator comp = new ProgramaComparator();
        comp.setComparator(EnumProgramaComparator.COMPARATOR_PAGINAS);
        List<Programa> progs = DB.getInstance().getProgramas();
        Collections.sort(progs, comp);
        if (progs.size() >= 9) {
            return progs.subList(0, 9);
        } else {
            return progs;
        }
    }

    @Override
    public List<Programa> getTop10MasPesados() {
        ProgramaComparator comp = new ProgramaComparator();
        comp.setComparator(EnumProgramaComparator.COMPARATOR_PESO);
        List<Programa> progs = DB.getInstance().getProgramas();
        Collections.sort(progs, comp);
        if (progs.size() >= 9) {
            return progs.subList(0, 9);
        } else {
            return progs;
        }
    }
}
