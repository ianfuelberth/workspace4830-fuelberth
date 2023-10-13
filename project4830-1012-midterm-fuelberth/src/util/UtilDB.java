/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import datamodel.MyEmployeeFuelberth;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<MyEmployeeFuelberth> listEmployees() {
      List<MyEmployeeFuelberth> resultList = new ArrayList<MyEmployeeFuelberth>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

      try {
         tx = session.beginTransaction();
         List<?> employees = session.createQuery("FROM MyEmployeeFuelberth").list();
         for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
            MyEmployeeFuelberth myEmployeeFuelberth = (MyEmployeeFuelberth) iterator.next();
            resultList.add(myEmployeeFuelberth);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<MyEmployeeFuelberth> listEmployees(String keyword) {
      List<MyEmployeeFuelberth> resultList = new ArrayList<MyEmployeeFuelberth>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         System.out.println((MyEmployeeFuelberth)session.get(MyEmployeeFuelberth.class, 1)); // use "get" to fetch data
        // Query q = session.createQuery("FROM Employee");
         List<?> employees = session.createQuery("FROM MyEmployeeFuelberth").list();
         for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
            MyEmployeeFuelberth myEmployeeFuelberth = (MyEmployeeFuelberth) iterator.next();
            if (myEmployeeFuelberth.getFirstName().startsWith(keyword)) {
               resultList.add(myEmployeeFuelberth);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static void createEmployees(String firstName, String lastName, String phone, String email) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new MyEmployeeFuelberth(firstName, lastName, phone, email));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
}
