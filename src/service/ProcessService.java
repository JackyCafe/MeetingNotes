package service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.misc.HibernateUtil;
import model.NotesBean;
import model.ProcessBean;
import model.Interface.IService;
import model.dao.ProcessBeanHibernateDAO;
 
public class ProcessService implements IService<ProcessBean> {
	
	public static ProcessBeanHibernateDAO dao;
	
	
	// test
		public static void main(String[] args) {
			Session session = HibernateUtil.createSessionFactoty().getCurrentSession();
			dao = new ProcessBeanHibernateDAO(HibernateUtil.createSessionFactoty());
			ProcessService service = new ProcessService(dao);
			Transaction trx = null;
	 
	 		try {
				// insert
				dao = new ProcessBeanHibernateDAO(HibernateUtil.createSessionFactoty());
				trx = dao.getSession().beginTransaction();
				ProcessBean bean = new ProcessBean();
			    
				bean.setId(0);
				bean.setProcess_id(2);
				bean.setReplyDate(new Date());
				bean.setSponsor("林彥亨");
				bean.setStatus("222");
				bean.setPresestation("A1");
				bean.setReference("ooo");
				bean.setUpload("ggg");
			 
				ProcessBean insert = service.insert(bean);
				System.out.println("insert ==>" + insert);
				trx.commit();
				
				
				//select 
				//dao = new UserBeanHibernateDAO(HibernateUtil.createSessionFactoty());
	 			//trx = dao.getSession().getTransaction();
	 			//List<UserBean> select = service.select();
				//System.out.println("select ==>" + select);
				//trx.commit();

				
			} catch (Exception e) {
				for (StackTraceElement s: e.getStackTrace())
				{
					System.out.println(s.toString());
				}
				System.out.println(e.toString());

				trx.rollback();
			}

		}
	
	
	
	 
	public ProcessService(ProcessBeanHibernateDAO dao) {
		this.dao = dao;
	}

	@Override
	public ProcessBean insert(ProcessBean bean) {
		return dao.insert(bean);
	}

	@Override
	public ProcessBean select(int id) {
		return dao.select(id);
	}

	@Override
	public List<ProcessBean> select() {
		return dao.select();
	}

	@Override
	public Boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public ProcessBean update(ProcessBean bean) {
		return dao.update(bean);
	}

}
