package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.User;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
			HttpSession session=request.getSession();
			System.out.println("From logout servlet " + session.isNew());
			User user=(User)session.getAttribute("validated_user_dtls");
			System.out.println("JessiosnId " + session.getId());
			if(user!=null)
			pw.print("<h5>Hello "+user.getName()+"</h5>");
			else {
				pw.print("<h5>Session tracking failed   </h5>");
			}
			pw.print("<h5>Logout succesful</h5>");
			session.invalidate();
			pw.print("<h5><a href='login.html'>Visit again</a></h5>");
			
		}
	}

}
