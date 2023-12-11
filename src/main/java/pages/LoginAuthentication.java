package pages;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import pojos.User;

import static utils.DBUtils.*;

//@WebServlet(urlPatterns = "/validate", loadOnStartup = 1)
public class LoginAuthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDaoImpl userDao;
	CandidateDaoImpl candidateDao;

	@Override
	public void init() throws ServletException {
		ServletConfig config = getServletConfig();

		try {
			openConnection(config.getInitParameter("url"), config.getInitParameter("name"),
					config.getInitParameter("password"));
			userDao = new UserDaoImpl();
			candidateDao = new CandidateDaoImpl();
		} catch (Exception e) {
			throw new ServletException("error in init of" + getClass() + e);

		}

	}

	@Override
	public void destroy() {
		try {
			closeConnection();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			User user = userDao.authenticateUser(req.getParameter("em"), req.getParameter("pass"));
			HttpSession session = req.getSession();
			session.setAttribute("user_info", user);
			session.setAttribute("userDao", userDao);
			session.setAttribute("candidateDao", candidateDao);

			System.out.println(user);
			if (user == null) {
				
				
				resp.sendRedirect("RegisterForm.html");
			} else if (user.getRole().equals("voter") && !user.getStatus()) {
				resp.sendRedirect("votingPage");

			} else if (user.getRole().equals("voter") && user.getStatus()) {
				resp.sendRedirect("logoutPage");
				
			} else if (user.getRole().equals("admin")) {
				System.out.println(user.getRole());
				resp.sendRedirect("adminPage");
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
}
			
				
