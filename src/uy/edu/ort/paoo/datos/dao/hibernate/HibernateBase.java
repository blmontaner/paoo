/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.paoo.datos.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public abstract class HibernateBase {
    
    public Session sesion; 
    public Transaction tx;  
    
    private static SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory() throws HibernatePaooException {
        if(sessionFactory == null){
            try 
            { 
                sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory(); 
            } catch (HibernateException he) 
            { 
               throw new HibernatePaooException(he.getMessage());
            } 
        }
        return sessionFactory;
    }

    /**
     * Metodo generalizado para iniciar las operaciones de acceso
     * a la base de datos.
     * 
     * @throws HibernatePaooException
     */
    public void iniciarOperacion() throws HibernatePaooException { 
        try {
            sesion = getSessionFactory().openSession(); 
            tx = sesion.beginTransaction();
        } catch (HibernatePaooException ex) {
            throw ex;
        }
    } 
}
