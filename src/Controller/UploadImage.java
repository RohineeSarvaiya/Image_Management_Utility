package Controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import DAO.ImageDao;
import DAO.UserDao;

import Model.Image;
import Model.User;

@WebServlet("/UploadImage")
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
    	String imageName="";
    	byte bytes[]=null;
    	double imageSize=0;
    	
    	if(ServletFileUpload.isMultipartContent(request))
    	{
    		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
    		try {
				List<FileItem> items = sfu.parseRequest(request);
				
				for(FileItem item: items) 
				{
					if(!item.isFormField()) 
					{
						imageSize = item.getSize()/1024;
						bytes = item.get();
						imageName = item.getName();
					}
				}
				
				//fetching loged in user
				User user=(User) request.getSession().getAttribute("user");
				
				//Image object instance with userinput
				Image image=new Image();
				image.setImageName(imageName);
				image.setImage(bytes);
				image.setImageSize(imageSize);
				image.setUser(user);
				
				if(user!=null) 
				{
					if(image.getImageSize()<1024)
					{
						double totalSize = getTotalSize(user.getUsername());
						if(totalSize +image.getImageSize() < 10240)
						{
							// adding image in image table
							 ImageDao imgDao = new ImageDao();
							 imgDao.addImage(image);
                             try {
                                 UserDao userDao = new UserDao();

                                 //updating new user
                                 User newUser = userDao.getUser(((User) request.getSession().getAttribute("user")).getUsername());
                                 request.getSession().setAttribute("user", newUser);
                                 response.sendRedirect("login-success.jsp");
                             } 
                             catch (Exception e) {
                                 e.printStackTrace();
                             }
						}
						else 
						{
                            System.out.println("Total uploaded size can not be exceed 10 MB!");
                            response.sendRedirect("login-success.jsp");
						}
					}
					else 
					{
                        System.out.println("File size can not be exceed 1MB!");
                        response.sendRedirect("login-success.jsp");
                    }
				}
				
			} catch (FileUploadException e) {
				
				e.printStackTrace();
			}
    	}
    }
	
	public static double getTotalSize(String username) {
		
		//summing all the imagesize
		double totalSize = 0;
		UserDao userDao = new UserDao();
		User user = userDao.getUser(username);
		Collection<Image> images= user.getImages();
		for(Image image : images) 
		{
			totalSize += image.getImageSize();
		}		
		return totalSize;
	}
}
