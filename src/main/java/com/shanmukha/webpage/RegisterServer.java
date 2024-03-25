package com.shanmukha.webpage;

import java.io.IOException;

import java.io.PrintWriter;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.SQLException;

import javax.management.loading.PrivateClassLoader;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.valves.rewrite.Substitution.StaticElement;

import com.mysql.cj.jdbc.jmx.LoadBalanceConnectionGroupManager;
import com.mysql.cj.xdevapi.PreparableStatement;
@WebServlet("/register")
public class RegisterServer extends HttpServlet {
	 final String query="insert into bookdata(bookname,bookedition,bookprice) values(?,?,?)";
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {

		//		get PrintWriter
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");
		//		Get the book INfo
		String bookName=req.getParameter("bookname");
		String bookEdition=req.getParameter("bookedition");
		float bookPrice=Float.parseFloat(req.getParameter("bookprice"));
		
		//		Load jdbc
//		 final String query="insert into bookdata(bookname,bookedition,bookprice) vaalues(?,?,?)";
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
			ps.setString(1, bookName);
			ps.setString(2,bookEdition);
			ps.setFloat(3, bookPrice);
			
			int count=ps.executeUpdate();
			if(count==1) {
				out.println("<h2>Records is registered successfully</h>");
			}
			else {
				out.println("<h2>Records is Notregistered successfully</h2>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("<h1>"+e.getMessage()+"</h2");
		}
		catch (Exception e) {
			// TODO: handle exception
			out.println("<h1>"+e.getMessage()+"</h2");
		}
		
		out.println("<br>");
		out.println("<a href='home.html'>Home</a>");
		out.println("<br>");
		out.println("<a href='booklist'>Book List</a>");
	}
public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		doGet(req, res);
	}

}
