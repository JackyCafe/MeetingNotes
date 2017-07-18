package service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.misc.HibernateUtil;
import model.UserBean;
import model.Interface.IService;
import model.dao.UserBeanHibernateDAO;

public class UserBeanService implements IService<UserBean> {
	public static UserBeanHibernateDAO dao;
	 
	public UserBeanService(UserBeanHibernateDAO dao) {
		this.dao = dao;
	}

	@Override
	public UserBean insert(UserBean bean) {
		return dao.insert(bean);
	}

	@Override
	public UserBean select(int id) {
		return dao.select(id);
	}
	
	public List<UserBean> select(String user,String passwd)
	{
		 return dao.select(user,passwd);
 		 	
	}

	@Override
	public List<UserBean> select() {
		return dao.select();
	}

	@Override
	public Boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public UserBean update(UserBean bean) {
		return dao.update(bean);
	}

}
