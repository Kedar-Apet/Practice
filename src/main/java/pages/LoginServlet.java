package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TopicDaoImpl;
import dao.UserDaoImpl;
import pojo.User;
import utils.DBUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/validate", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;
	private TopicDaoImpl topicDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		System.out.println("Login servlet created");
		try {
			userDao = new UserDaoImpl();
			topicDao = new TopicDaoImpl();
			//System.out.println("no eerror");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// to let W.C know that the DBConnection is failed and
			// so servlet creation failed
			System.out.println("error");
			throw new ServletException("err in init " + getClass().getName(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("Login servlet destroyed");
		try {
			userDao.cleanUp();
			topicDao.cleanUp();
			DBUtils.closeConnection();
		} catch (SQLException e) {
			System.out.println("err in destory method of " + getClass().getName() + " " + e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			User u = userDao.validateUser(email, password);

			if (u != null) {
				// 1.request W.C get the session
				HttpSession session = request.getSession();
				System.out.println("session "+session.getClass().getName());
				System.out.println("From login page " + session.isNew());// true for first time
				System.out.println("JsessionId " + session.getId());
				session.setAttribute("validated_user_dtls", u);
				session.setAttribute("topic_dao", topicDao);
				response.sendRedirect("topics");// prog
				// W.C: send temp redirect response to the client pw content is discarded
				// sts: SC302 | header -->location=topics, content-Length=0 | body:Empty, set
				// cookie:JSESSION:string val generated by W.C unique per clnt
				// clnt browser:generates a new request
				// URL:http://localhost:8080/day4.1/topics, method=GET

			} else {
				pw.print("<h5><a href='login.html'>No user found REtry</a> </h5>");
			}
		} catch (SQLException e) {
			throw new ServletException("Error in dopost of " + getClass().getName(), e);

		}

	}

}