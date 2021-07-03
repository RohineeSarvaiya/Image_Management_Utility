package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDao;
import Model.User;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

    public void init() {
    	userDao = new UserDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	          authenticate(request, response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (userDao.validate(username, password)) 
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login-success.jsp");
            User user=userDao.getUser(username);
    		request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("UserName", username);
            dispatcher.forward(request, response);
        } 
        else 
        {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("login-fail.jsp");
            dispatcher.forward(request, response);
        }
    }
}
