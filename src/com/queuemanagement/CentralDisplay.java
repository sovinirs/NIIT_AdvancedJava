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
@WebServlet("/CentralDisplay")

public class CentralDisplay extends HttpServlet {  
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        	response.setContentType("text/html");
        	PrintWriter writer = response.getWriter();
            out.print("<html><br><b> Welcome to Central Display</b><br>");
    		DatabaseConnection db = new DatabaseConnection();
    		Connection conn = db.getConnection();
    		String SQL = "SELECT id,ticket FROM ticket";
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
				writer.println("<th> Ticket Numbers </th></tr>"); 
				for(int i=0; i<5 && rs.next(); i++) 
				{ 
				writer.println("<tr><td>"+rs.getString("ticket")+rs.getString("id")+"</td></tr>");
				} 
				writer.println("</table></html>"); 
				request.getRequestDispatcher("generate.html").include(request, response);
				
	    	 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            out.close();
        }

    }
