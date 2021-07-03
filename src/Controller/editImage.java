package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ImageDao;
import DAO.UserDao;
import Model.Image;
import Model.User;

@WebServlet("/editImage")
public class editImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		edit(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		edit(request,response);
	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//fetching image with id
		String imgId = request.getParameter("imageId");
		long imageId = Long.parseLong(imgId);
		String editedName = request.getParameter("editedName");
		
		//fetching image
		ImageDao imageDao = new ImageDao();
		Image image = imageDao.getImage(imageId);
		
		
		//upadting image with new image name
		if(editedName.equals(image.getImageName())) 
		{
			response.sendRedirect("login-success.jsp");	
		}
		else if(editedName=="")
		{			
			response.sendRedirect("editImage.jsp");
		}
		else
		{
			image.setImageName(editedName);
			imageDao.updateImage(image);
			response.sendRedirect("login-success.jsp");
			
		}
		
		//updating new user
		UserDao userDao = new UserDao();
	    User user = (User)request.getSession().getAttribute("user");
		User newUser = userDao.getUser(user.getUsername());
		request.getSession().setAttribute("user", newUser);
		
		
		
		
	}

}
