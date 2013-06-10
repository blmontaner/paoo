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
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Victor Nessi
 * @author Bruno Montaner
 */
public class HibernateUtil {
    
    private static final SessionFactory sessionFactory; 
    
    static 
    { 
        try 
        { 
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory(); 
        } catch (HibernateException he) 
        { 
           System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + he); 
           throw new ExceptionInInitializerError(he); 
        } 
    }  

    public static SessionFactory getSessionFactory() 
    { 
        return sessionFactory; 
    } 
    
     

}
