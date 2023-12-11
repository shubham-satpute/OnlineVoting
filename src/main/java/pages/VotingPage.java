package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import pojos.User;

/**
 * Servlet implementation class VotingPage
 */
@WebServlet("/votingPage")
public class VotingPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		HttpSession hs = req.getSession();
		CandidateDaoImpl cD = (CandidateDaoImpl) hs.getAttribute("candidateDao");
		User u = (User) hs.getAttribute("user_info");
		try (PrintWriter pw = resp.getWriter()) {
			pw.print("<h1>Hello " + u.getfName() + " " + u.getlName() + "</h1>");
			pw.print("<form action='logoutPage' method='post'>");
			cD.getAllCandidate().forEach(C -> pw.print("<h3><input type='radio' name='cd' value='" + C.getId() + "'>"
					+ C.getName() + "</input></h3>" + "</hr>"));
			pw.print("<input type='submit' value='Confirm'/>");
			pw.print("</form>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
