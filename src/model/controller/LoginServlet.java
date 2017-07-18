package model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserBean;
import model.dao.UserBeanHibernateDAO;
import model.misc.HibernateUtil;
import service.UserBeanService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public PrintWriter out ;   
	public UserBeanService service;   
 
	@Override
	public void init() throws ServletException {
		service = new UserBeanService(
	    		 new UserBeanHibernateDAO(
	    		 HibernateUtil.getSessionFactory()));
		super.init();
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		doGet(request, response);
 	    response.setContentType("text/html; charset=utf-8");
	    response.setCharacterEncoding("utf-8");
	    request.setCharacterEncoding("utf-8");
  		
 		
 		String user = request.getParameter("user");
 		String passwd = request.getParameter("passwd");
 	    Map<String, String[]> maps = request.getParameterMap();
	    int size = maps.size();
	    String[] name = new String[size];
	    int i= 0 ;
	    for(String key:maps.keySet())
	    {
	    	name[i] = key;
 	    	i++;
	    }
	    
	    //新增
	    if (name[size-1].equals("login"))
	    {
	         List<UserBean> loginUser = service.select(user,passwd);
	    	if  (loginUser.size()>0)
	    	 {
 	             HttpSession session = request.getSession();
	             session.setAttribute("user", loginUser.get(0));
	             RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	             dispatcher.forward(request, response);
	    	 } 
	    	 
	     
	    }
 		doGet(request, response);
 		
 		
	}

}
