 package com.MembersOnly;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
        
    }


    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//res.setContentType("text/html");
		//String login_file_path = "/WebServ/loginPage.html";
		res.sendRedirect("loginPage.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
         processLogin(req, res);
	}
	
	protected void processLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    	/***
    	 * You can see the general idea here
    	 * Once we get API calls from other teams in terms of
    	 * calling, we can pass these into the others as arguments.
    	 */
		
		String username = req.getParameter("username");
    	String password = req.getParameter("password");
    	PrintWriter out = res.getWriter();
    	out.println(username);
    	out.println(password);
    	//if username and password not valid, redirect to login page
    	//else successful
	}
	

}
