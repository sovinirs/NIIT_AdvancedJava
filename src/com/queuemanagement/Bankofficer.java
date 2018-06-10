package com.queuemanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Bankofficer")

public class Bankofficer extends HttpServlet {  
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
                      throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        //request.getRequestDispatcher("link.html").include(request, response);

        HttpSession session=request.getSession(false);
        if(session!=null){
        	
        	response.setContentType("text/html");
        	PrintWriter writer = response.getWriter();
            out.print("<html><br><b> Welcome to Bank Officers page </b><br>");
    		DatabaseConnection db = new DatabaseConnection();
    		Connection conn = db.getConnection();
    		String SQL = "SELECT name,username, password FROM bank_officer";
    		Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		try {
				ResultSet rs = stmt.executeQuery(SQL);
				writer.println("<table align=center width=50% border=1><tr>"); 
				writer.println("<th> Name </th>"); 
				writer.println("<th> Username </th>"); 
				writer.println("<th> Password </th></tr>"); 
				while(rs.next()) 
				{ 
				writer.println("<tr><td>"+rs.getString("name")+"</td>"); 
				writer.println("<td>"+rs.getString("username")+"</td>");
				writer.println("<td>"+rs.getString("password")+"</td></tr>"); 
				} 
				writer.println("</table></html>"); 
				request.getRequestDispatcher("link.html").include(request, response);
				
	    	 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
        else{
        	response.setContentType("text/html"); 
            out.print("Please login first");
            request.getRequestDispatcher("adminlogin.html").include(request, response);
        }
        out.close();
    }
}
