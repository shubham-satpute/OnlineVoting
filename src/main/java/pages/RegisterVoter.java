package pages;

import static utils.DBUtils.openConnection;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import dao.UserDaoImpl;
import pojos.User;

//@WebServlet("/Register")
public class RegisterVoter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDaoImpl userDao;
	CandidateDaoImpl candidateDao;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		ServletConfig config = getServletConfig();
		
		User user = new User(req.getParameter("fn"), req.getParameter("ln"), req.getParameter("em"),
				req.getParameter("pass"), Date.valueOf(req.getParameter("db")));
		try {
			openConnection(config.getInitParameter("url"), config.getInitParameter("name"),
					config.getInitParameter("password"));
			
			userDao = new UserDaoImpl();
			candidateDao = new CandidateDaoImpl();
			
			
			String ConfirmMsg = userDao.registerNewUser(user);
			System.out.println(ConfirmMsg);
			resp.sendRedirect("RegisteredPage.html");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
