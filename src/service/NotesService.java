package service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.NotesBean;
import model.ProcessBean;
import model.Interface.IService;
import model.dao.NotesBeanHibernateDAO;
import model.dao.ProcessBeanHibernateDAO;
import model.misc.HibernateUtil;
 
public class NotesService implements IService<NotesBean> {
	public static NotesBeanHibernateDAO dao;
	 
	public static void main(String[] args) {
		Session session = HibernateUtil.createSessionFactoty().getCurrentSession();
		dao = new NotesBeanHibernateDAO(HibernateUtil.createSessionFactoty());
		NotesService service = new NotesService(dao);
		Transaction trx = null;
 
 		try {
			// insert
			 
	 
		} catch (Exception e) {
			for (StackTraceElement s: e.getStackTrace())
			{
				System.out.println(s.toString());
			}
			System.out.println(e.toString());

			trx.rollback();
		}

	}
	 
	public NotesService(NotesBeanHibernateDAO dao) {
		this.dao = dao;
	}

	@Override
	public NotesBean insert(NotesBean bean) {
		return dao.insert(bean);
	}

	@Override
	public NotesBean select(int id) {
		return dao.select(id);
	}

	@Override
	public List<NotesBean> select() {
		return dao.select();
	}

	public List<NotesBean> selectByParam(String name,String value)
	{
		return dao.selectByParam(name,value);
	}	
	
	@Override
	public Boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public NotesBean update(NotesBean bean) {
		return dao.update(bean);
	}
	
	public NotesBean update(int id, Boolean control,Integer controlStatus)
	{
		return dao.update(id,control,controlStatus);
	}

	
	
	
}
