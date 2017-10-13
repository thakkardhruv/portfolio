package form;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import connect.ConnectManager;

@WebServlet("/manage_form")
public class manage_form extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String phone = request.getParameter("phone");
		String message = request.getParameter("message");
		
		ConnectManager c = new ConnectManager();
		try{
			Connection con = c.connect("portfolio");
			/*Class.forName("com.mysql.jdbc.Driver");  
	        String URLDB = "jdbc:mysql://localhost:3306/"+"portfolio";
	        Connection con = DriverManager.getConnection(URLDB,"root","root");*/
			if(con != null)
			{
				String sql = "insert into contact_me(name,email,subject,phone,message) values (?,?,?,?,?)";
				PreparedStatement prst = con.prepareStatement(sql);
				prst.setString(1, name);
				prst.setString(2, email);
				prst.setString(3, subject);
				prst.setString(4, phone);
				prst.setString(5, message);
				
				int result = prst.executeUpdate();
				if(result != 0 )
				{
					EmailMe mymail = new EmailMe();
					int statusEmail = mymail.send(subject, message, phone, email, name);
					if(statusEmail != 0){
						RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
						out.print("<p style='color:green;'><b>Email is sent, I will contact you soon..</b></p>");
						rd.include(request, response);
					}
					else{
						RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
						out.print("<p style='color:red;'><b>Email can not be send, please try again later..</b></p>");
						rd.include(request, response);
					}
				}
				else
				{
					out.print("Can't be inserted!");
				}
			}
			else
			{
				out.print("Can not Connect!!");
			}
		}
		catch(Exception e)
		{
			out.print("Connection Problem!!"+e);
		}
	
	}

}
