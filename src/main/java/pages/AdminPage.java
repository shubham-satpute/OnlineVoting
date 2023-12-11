package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import dao.UserDaoImpl;
import pojos.Candidate;
import pojos.User;

@WebServlet(urlPatterns = "/adminPage")
public class AdminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
		HttpSession hs = req.getSession();
	CandidateDaoImpl cD = (CandidateDaoImpl) hs.getAttribute("candidateDao");
	UserDaoImpl uD =  (UserDaoImpl) hs.getAttribute("userDao");
	User admin = (User) hs.getAttribute("user_info");
	
	try(PrintWriter pw = resp.getWriter()) {
		
		pw.print("<h1>Top 2 Candidates: </h1>");
		cD.getTop2MaxVotes().forEach(C->pw.print("<h5>"+C+"</h5>"));
		
		pw.print("<h1>Party and their respective votes : </h1>");
		cD.partywiseVotes().forEach((k,v)->pw.print("<h5>"+k+"  "+v+"</h5>"));
		pw.print("<a href='LoginForm.html'> Login </a> ");
		hs.invalidate();
	} catch (SQLException e) {
	
		e.printStackTrace();
	}
	
	}

}
