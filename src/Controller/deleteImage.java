package Controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import DAO.ImageDao;
import DAO.UserDao;
import Model.User;

@WebServlet("/deleteImage")
public class deleteImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		delete(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		delete(request, response);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getSession().getAttribute("user")==null)
    	{
    		response.sendRedirect("login.jsp");
    	}
    	else
    	{
    		UserDao userDao = new UserDao();
    		User user = (User) request.getSession().getAttribute("user");
    	
    		//fetching imageId and deleting that image from the database
    		ImageDao imageDao =  new ImageDao();    		
    		String imgId = request.getParameter("imageId");    		
    		long imageId = Long.parseLong(imgId);
    		imageDao.deleteImage(imageId);
    		
    		//updating new user
    		User newUser = userDao.getUser(user.getUsername());
    		request.getSession().setAttribute("user", newUser); 		
    	}
    	
    	response.sendRedirect("login-success.jsp");
    }
	

}
