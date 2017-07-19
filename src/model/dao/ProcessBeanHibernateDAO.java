package model.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.misc.HibernateUtil;
import model.Interface.IDAO;
import model.ProcessBean;
import model.UserBean;

public class ProcessBeanHibernateDAO implements IDAO<ProcessBean> {
	public static Session session;
	public static SessionFactory factory;
	public static Transaction trx;
	
	public static void main(String[] args) {
		test();
    }
	
	public static void test()
	{
		
		factory = HibernateUtil.getSessionFactory();
		session = factory.getCurrentSession();
		ProcessBeanHibernateDAO dao = new ProcessBeanHibernateDAO(factory);
		insert(dao);
		select(dao);
		//update(dao);
		//delete(dao);
		
	}

	private static void delete(ProcessBeanHibernateDAO dao) {
		try {
			dao.getSession().close();
			trx = dao.getSession().beginTransaction();
			Boolean delete = dao.delete(1);
			System.out.println("delete" + delete);
			trx.commit();
			
			
		} catch (Exception e) {
			for (StackTraceElement s : e.getStackTrace())
				System.out.println(s.toString());
			System.out.println(e.getMessage());
		}
	}

	private static void update(ProcessBeanHibernateDAO dao) {
		try {
			trx = dao.getSession().beginTransaction();
			ProcessBean bean = new ProcessBean();
			 
			ProcessBean update = dao.update(bean);
			System.out.println("update" + bean);

			trx.commit();
		} catch (Exception e) {
			for (StackTraceElement s : e.getStackTrace())
				System.out.println(s.toString());
			System.out.println(e.getMessage());
		}
	}

	
	
	
	
	
	
	
	
	private static void select(ProcessBeanHibernateDAO dao) {
		try {
			session = factory.getCurrentSession();
			trx = session.beginTransaction();
			List<ProcessBean> select = dao.select();
			System.out.println(select);
			trx.commit();
		} catch (Exception e) {
			for (StackTraceElement s : e.getStackTrace())
				System.out.println(s.toString());
			System.out.println(e.getMessage());
		}
	}

	private static void insert(ProcessBeanHibernateDAO dao) {
		// insert
		try {
			trx = dao.getSession().beginTransaction();
			ProcessBean bean = new ProcessBean();
			bean.setId(0);
			bean.setProcess_id(2);
			bean.setReplyDate(new Date());
			bean.setSponsor("林彥亨");
			bean.setStatus("111");
			bean.setPresestation("A1");
			bean.setReference("ooo");
			bean.setUpload("ggg"); 
			ProcessBean insert = dao.insert(bean);
			System.out.println("insert" + bean);

			trx.commit();
		} catch (Exception e) {
			for (StackTraceElement s : e.getStackTrace())
				System.out.println(s.toString());
			System.out.println(e.getMessage());
		}
	}
	
	
	public ProcessBeanHibernateDAO(SessionFactory factory) {
		 this.factory = factory;
 	}
	
	
	@Override
	public ProcessBean select(ProcessBean bean) {
 		return select(bean.getId());
	}

	@Override
	public ProcessBean select(int id) {
 		return this.getSession().get(ProcessBean.class,id);
	}

	@Override
	public List<ProcessBean> select() {
 		return this.getSession().createQuery("from ProcessBean", ProcessBean.class).getResultList();
	}

	@Override
	public ProcessBean insert(ProcessBean bean) {
		ProcessBean tmp = select(0);
		if( tmp ==null)
		{
		   this.getSession().save(bean);
		   System.out.println("Select " +tmp);
		}
		 return bean;
	}

	@Override
	public Boolean delete(ProcessBean bean) {
		ProcessBean tmp = select(bean);
		if(tmp !=null)
		{
			this.getSession().delete(tmp);
			return true;
		}else {
			return false;
		}
 		
	}

	@Override
	public Boolean delete(int id) {
		ProcessBean tmp = select(id);
		if(tmp !=null)
		{
			this.getSession().delete(tmp);
			return true;
		}else {
			return false;
		}
			
	}

	@Override
	public ProcessBean update(ProcessBean bean) {
		ProcessBean tmp = select(bean.getId());
		if(tmp!=null)
		{
		 
		}	
		
		
 		return tmp;
	}

	@Override
	public Session getSession() {
 		return factory.getCurrentSession();
	}

	
	
	 
}
