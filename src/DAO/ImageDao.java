package DAO;

import org.hibernate.Session;

import Model.Image;
import Configure.HibernateUtil;

public class ImageDao {
	
	public void addImage(Image image) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();
			session.save(image);
			session.getTransaction().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Image getImage(long imageId) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Image image=null;
		try {
			image=session.get(Image.class, imageId);
			return image;
		}catch(Exception e) {
			e.printStackTrace();
	    }
	    return image;
	}

	public void deleteImage(long imageId) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Image image=session.get(Image.class, imageId);
			if(image!=null) {
				session.delete(image);
				
			}
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}

	public void updateImage(Image image) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(image);
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
