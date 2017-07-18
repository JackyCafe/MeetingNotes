package model.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.sql.ordering.antlr.Factory;

import model.misc.HibernateUtil;
import model.UserBean;
import model.Interface.IDAO;

public class UserBeanHibernateDAO implements IDAO<UserBean> {
	public static Session session;
	public static SessionFactory factory;
	public static Transaction trx;

	public static void main(String[] args) {
		test();
		System.out.println("test");
	}

	private static void test() {
		factory = HibernateUtil.getSessionFactory();
		session = factory.getCurrentSession();
		UserBeanHibernateDAO dao = new UserBeanHibernateDAO(factory);
		// insert
		try {
			trx = dao.getSession().beginTransaction();
			UserBean bean = new UserBean();
			bean.setId(0);
			bean.setUser("Jacky");
			bean.setPasswd("123456");
			bean.setChineseName("林彥亨");
			bean.setEmail("Goerge@gmail.com");
			UserBean insert = dao.insert(bean);
			System.out.println("insert" + insert);
			trx.commit();
		} catch (Exception e) {
			for (StackTraceElement s : e.getStackTrace())
				System.out.println(s.toString());
			System.out.println(e.getMessage());
		}

		/* update */
		try {
			session = factory.getCurrentSession();
			trx = dao.getSession().beginTransaction();
			UserBean bean = new UserBean();
			bean.setId(3);
			bean.setUser("Jacky");
			bean.setPasswd("654321");
			
			bean.setEmail("lhm0531@gmail.com");
			UserBean update = dao.update(bean);
			System.out.println("update" + update);
			trx.commit();

		} catch (Exception e) {

		}
		// Select
		try {
			session = factory.getCurrentSession();
			trx = session.beginTransaction();
			List<UserBean> select = dao.select();
			System.out.println("select" + select);
			trx.commit();

		} catch (Exception e) {

		}

		/*
		 * delete try { session = factory.getCurrentSession(); trx =
		 * dao.getSession().beginTransaction(); Boolean delete = dao.delete(5);
		 * System.out.println("delete" + delete); trx.commit(); } catch (Exception e) {
		 * }
		 */

	}

	public UserBeanHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public UserBean select(UserBean bean) {
		if (bean != null) {
			bean = this.getSession().get(UserBean.class, bean.getId());
			
		}
		return bean;
	}

	@Override
	public UserBean select(int id) {
		return this.getSession().get(UserBean.class, id);
	}

	@Override
	public List<UserBean> select() {
		return this.getSession().createQuery("from UserBean", UserBean.class).getResultList();
	}

	@Override
	public UserBean insert(UserBean bean) {
		UserBean tmp = select(bean);
		if (tmp == null) {
			this.getSession().save(bean);
			System.out.println("tmp bean" + tmp);
		}

		return bean;
	}

	@Override
	public Boolean delete(UserBean bean) {
		UserBean tmp = select(bean.getId());
		if (tmp != null) {
			return delete(bean.getId());
		} else {
			return false;
		}
	}

	@Override
	public Boolean delete(int id) {
		UserBean temp = select(id);
		if (temp != null) {
			this.getSession().delete(temp);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public UserBean update(UserBean bean) {
		UserBean tmp = select(bean.getId());
		if (tmp != null) {
			tmp.setId(bean.getId());
			tmp.setUser(bean.getUser());
			tmp.setPasswd(bean.getPasswd());
			tmp.setEmail(bean.getEmail());
		}
		return tmp;
	}

	@Override
	public Session getSession() {
		return factory.getCurrentSession();
	}

	public List<UserBean> select(String user, String passwd) {
		
		 return this.getSession().createQuery("from UserBean where user =:user and passwd =:passwd", UserBean.class) 
		.setParameter("user", user)
		.setParameter("passwd", passwd)
		.getResultList();
 	}

}
