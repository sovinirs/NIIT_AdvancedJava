package com.queuemanagement; 


import java.io.IOException;
import java.io.PrintWriter;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession; 

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        request.getRequestDispatcher("adminprofile.html").include(request, response);  
          
        String username=request.getParameter("username");  
        String password=request.getParameter("password");
          
        if(password.equals("admin") && username.equals("admin")){ 
        	String htmlRespone = "<html>";
            htmlRespone += "<h2> You have sucessfully logged in! </h2>";
            htmlRespone += "</html>";
            out.println(htmlRespone);  
        HttpSession session=request.getSession();  
        session.setAttribute("name",username);  
        }  
        else{  
            out.print("Sorry, username or password error!");  
            request.getRequestDispatcher("adminlogin.html").include(request, response);  
        }  
        out.close();  
    }  
}  