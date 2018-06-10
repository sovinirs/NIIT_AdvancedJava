package com.queuemanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebServlet("/AddBranches")
public class AddBranches extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		String branchcode = request.getParameter("branchcode");
		String branchlocation = request.getParameter("branchlocation");
		String SQL = "INSERT INTO branches(branchcode,branchlocation) " + "VALUES(?,?)";
		
		PrintWriter writer = response.getWriter(); 
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();
		
		HttpSession session=request.getSession(false);
        if(session!=null){
		try (PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)) {
	 
	            pstmt.setString(1, branchcode);
	            pstmt.setString(2, branchlocation);
	 
	            int affectedRows = pstmt.executeUpdate();
	            // check the affected rows 
	            if (affectedRows > 0) {
	                // get the ID back
	                try (ResultSet rs = pstmt.getGeneratedKeys()) {
	                    if (rs.next()) {
	                    	writer.print("You have sucessfully created a Branch");
	                    	response.setContentType("text/html");
	                        request.getRequestDispatcher("AddBranches.html").include(request, response);
	                    }
	                } catch (SQLException ex) {
	                    String msg = ex.getMessage();
                        String htmlRespone = "<html>";
                        htmlRespone += "<h2>" + msg + "<br/>";
                        htmlRespone += "</html>";
                        writer.println(htmlRespone);
	                }
	            }
	        } catch (SQLException ex) {
	        	String msg = ex.getMessage();
                String htmlRespone = "<html>";
                htmlRespone += "<h2>" + msg + "<br/>";
                htmlRespone += "</html>";
                writer.println(htmlRespone);
                }
		} else {
			response.setContentType("text/html"); 
		    writer.print("Please login first");
		    request.getRequestDispatcher("adminlogin.html").include(request, response);
	}
	}
}
 
