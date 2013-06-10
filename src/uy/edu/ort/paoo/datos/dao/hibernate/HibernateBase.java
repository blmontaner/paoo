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
public class HibernateBase {
    
    public Session sesion; 
    public Transaction tx;  
    
    private static SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            try 
            { 
                sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory(); 
            } catch (HibernateException he) 
            { 
               System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + he); 
               throw new ExceptionInInitializerError(he); 
            } 
        }
        return sessionFactory;
    }

    public void iniciarOperacion() throws HibernateException { 
        sesion = getSessionFactory().openSession(); 
        tx = sesion.beginTransaction(); 
    } 
}
