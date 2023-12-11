package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import dao.UserDaoImpl;
import pojos.User;

@WebServlet("/logoutPage")
public class LogoutPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession hs = req.getSession();
		User u = (User) hs.getAttribute("user_info");
		System.out.println(hs.getId());
		try (PrintWriter pw = resp.getWriter()) {
			pw.print("<h1>Hello " + u.getfName() + " " + u.getlName() + "</h1>");
			pw.print("<h1>You have already voted, Thank you!</h1>");
				hs.invalidate();
				System.out.println(hs.getId());
			pw.print(" <h5><a href='LoginForm.html'>Log Out</a></h5>");
			pw.print("<a href='RegisterForm.html'>Register new Candidate</a>");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession hs = req.getSession();
		UserDaoImpl uD = (UserDaoImpl) hs.getAttribute("userDao");
		CandidateDaoImpl cD = (CandidateDaoImpl) hs.getAttribute("candidateDao");
		User u = (User) hs.getAttribute("user_info");
		System.out.println(u);

		try (PrintWriter pw = resp.getWriter()) {

			pw.print("<h1>Hello " + u.getfName() + " " + u.getlName() + "</h1>");
			pw.print("<h1>Thanks For voting....!</h1>");
			System.out.println(hs.getId());
			hs.invalidate();
			System.out.println(hs.getId());
			pw.print(" <h5><a href='LoginForm.html'>Log Out</a></h5>");
			cD.IncrementVotes(Integer.parseInt(req.getParameter("cd")));
			uD.updateVotingStatus(u);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
