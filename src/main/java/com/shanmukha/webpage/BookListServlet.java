package com.shanmukha.webpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;
@WebServlet("/booklist")
public class BookListServlet extends HttpServlet {
	 final String query="select bookid,bookname,bookedition,bookprice from bookdata";
		public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {

			//		get PrintWriter
			PrintWriter out=res.getWriter();
			res.setContentType("text/html");
			
			
			//		Load jdbc
//			 final String query="insert into bookdata(bookname,bookedition,bookprice) vaalues(?,?,?)";
			String url="jdbc:mysql://localhost:3306/shanmukha";
			String uname="root";
			String pass="Shanmukh@18";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//		generaate connection
			try {
				Connection con=DriverManager.getConnection(url,uname,pass);
				PreparedStatement ps=con.prepareStatement(query);
				ResultSet rs=ps.executeQuery();
				 out.println("<table border=1 align='center'>");
		            out.println("<tr>");
		            out.println("<th>Book Id </th>");
		            out.println("<th>Book Name </th>");
		            out.println("<th>Book Edition </th>");
		            out.println("<th>Book Price </th>");
		            out.println("<th>Edit</th>");
		            out.println("<th>Delete</th>");
		            out.println("</tr>");

		            while (rs.next()) {
		                out.println("<tr>");
		                out.println("<td>" + rs.getInt(1) + "</td>");
		                out.println("<td>" + rs.getString(2) + "</td>");
		                out.println("<td>" + rs.getString(3) + "</td>");
		                out.println("<td>" + rs.getFloat(4) + "</td>");
		                out.println("<td> <a href='editScreen?id="+rs.getInt(1)+"'>Edit</a> </td>");
		                out.println("<td><a href='deleteServlet?id="+rs.getInt(1)+"'>Delete</a> </td>");
		                out.println("</tr>");
		            }
		            out.println("</table>");

				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.println("<h1>"+e.getMessage()+"</h2");
			}
			catch (Exception e) {
				// TODO: handle exception
				out.println("<h1>"+e.getMessage()+"</h2");
			}
			out.println("<a href='Home.html'>Home</a>");
		}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
			doGet(req, res);
		}

}
