 package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TopicDaoImpl;
import pojo.Topic;
import pojo.User;

/**
 * Servlet implementation class TopicServlet
 */
@WebServlet("/topics")
public class TopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
		HttpSession session=request.getSession();
		System.out.println("From topic servlet " + session.isNew());
		System.out.println("JessiosnId " + session.getId());
		User user = (User) session.getAttribute("validated_user_dtls");
		if (user != null) {
			pw.print("<h5>Valdiated user details" + user + "</h5>");
			TopicDaoImpl topicDao=(TopicDaoImpl)session.getAttribute("topic_dao");
			List<Topic> allTopics = topicDao.getAllTopics();
		//	allTopics.forEach(topic->pw.print(topic+"</br>"));
			//dynamic form generation
			pw.print("<form action='tutorials'>");
			pw.print("<h3 align='center'>All Topics</h3></br>");
			allTopics.forEach(topic->pw.print("<input type='radio' name='topics' value='"+topic+"'/>"+topic+"</br>"));
			pw.print("<input type='submit' value='choose topic'/>");
			pw.print("</form>");
		} else {
			pw.print("<h5>Session tracking failed   </h5>");
		}
	
			pw.print("<h5><a href='logout'>Logout</a></h5>");
		} catch (SQLException e) {
			throw new ServletException("err in do Get of "+getClass().getName(),e);
		}
	}

}

