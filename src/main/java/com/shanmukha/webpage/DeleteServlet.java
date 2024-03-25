package com.shanmukha.webpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
	String query="delete from bookdata where bookid=?";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  //get PrintWriter
        PrintWriter pw = response.getWriter();
        //set content type
        response.setContentType("text/html");
        //get the id of record
        int id = Integer.parseInt(request.getParameter("id"));
      
        //LOAD jdbc driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }
        //generate the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shanmukha", "root", "Shanmukh@18");
        		PreparedStatement ps = con.prepareStatement(query);) {
           ps.setInt(1, id);
            int count = ps.executeUpdate();
            if (count == 1) {
                pw.println("<h2>Record is Deleted Successfully</h2>");
            } else {
                pw.println("<h2>Record is not Deleted Successfully</h2>");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h2>");
        }
        pw.println("<a href='Home.html'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='booklist'>Book List</a>");

	}

	

}
