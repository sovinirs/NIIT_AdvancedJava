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
 
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String SQL = "INSERT INTO bank_officer(username,password,name) " + "VALUES(?,?,?)";
		
		PrintWriter writer = response.getWriter(); 
		DatabaseConnection db = new DatabaseConnection();
		Connection conn = db.getConnection();
		
		try (PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)) {
	 
	            pstmt.setString(1, username);
	            pstmt.setString(2, password);
	            pstmt.setString(3, name);
	 
	            int affectedRows = pstmt.executeUpdate();
	            // check the affected rows 
	            if (affectedRows > 0) {
	                // get the ID back
	                try (ResultSet rs = pstmt.getGeneratedKeys()) {
	                    if (rs.next()) {
	                    	writer.print("You have sucessfully created a Bank Officer Account");
	                    	response.setContentType("text/html");
	                        request.getRequestDispatcher("bankofficer.html").include(request, response);
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
		}
	}
 
