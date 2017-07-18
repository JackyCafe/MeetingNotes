package model.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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
 * Servlet implementation class register
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out ;   
	UserBeanService service;
	@Override
	public void init() throws ServletException {
	     service = new UserBeanService(
	    		 new UserBeanHibernateDAO(
	    		 HibernateUtil.getSessionFactory()));

 		super.init();
	}
	
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		 
  	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
 	    Map<String, String[]> maps = request.getParameterMap();
	    int size = maps.size();
	    String[] name = new String[size];
	    String[] value = new String[size];
	    int i = 0;
	    for(String key:maps.keySet())
	    {
	    	name[i] = key;
	    	value[i] = maps.get(key)[0];
	    	i++;
	    }
	    //呼叫Model
	    
	    UserBean users = new UserBean();
	    users.setId(0);
	    users.setUser(maps.get("user")[0]);
	    users.setPasswd(maps.get("passwd")[0]);
	    users.setEmail(maps.get("email")[0] );
	    users.setChineseName(maps.get("chname")[0]);
	    //新增
	    if (name[size-1].equals("add_action"))
	    {
  	      service.insert(users);
  	      HttpSession session = request.getSession();
  	      session.setAttribute("user",users);
  	      RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
  	      dispatcher.forward(request, response);
 	     }
	    
	    
		doGet(request, response); 
 		
	}

}
