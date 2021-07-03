package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ImageDao;

import Model.Image;

@WebServlet("/fetchImage")
public class fetchImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 if(request.getSession().getAttribute("user") == null)
		 {
	            response.sendRedirect("login.jsp");
	     }
	     else 
	     {
	    	 	//fetching image  object with imageid
	            ImageDao imageDao = new ImageDao();
	            String imgId = request.getParameter("imageId");
	            Long imageId = Long.parseLong(imgId);
	            Image image = imageDao.getImage(imageId);
	            
	            //fetching image blob from image database
	            if (image != null)
	            {
	                response.setContentType("image/jpeg, image/jpg, image/png");
	                try {
	                	ServletOutputStream sos = response.getOutputStream();
	                    sos.flush();
	                    sos.write(image.getImage());
	                    sos.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	       }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
