package DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Configure.HibernateUtil;
import Model.User;


public class UserDao {

	 public boolean validate(String userName, String password) {

	        Transaction transaction = null;
	        User user = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            
	            transaction = session.beginTransaction();
	            user = (User) session.createQuery("FROM User u WHERE u.username = :userName").setParameter("userName", userName)
	                .uniqueResult();

	            if (user != null && user.getPassword().equals(password)) {
	                return true;
	            }
	            
	            transaction.commit();
	            
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	        return false;
	 }
	 
	 
	 public User getUser(String username) {
			User user=null;
			Session session= HibernateUtil.getSessionFactory().openSession();
			try {
				session.getTransaction().begin();
				user=session.get(User.class, username);
			}catch(Exception e) {
				e.printStackTrace();

			}
			return user;
		}
	 
	 public void saveUser(User user) {
			
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            
	            transaction = session.beginTransaction();            
	            session.save(user);           
	            transaction.commit();
	            
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	 }

}
