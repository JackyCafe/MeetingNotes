package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.misc.HibernateUtil;
import model.NotesBean;
import model.ProcessBean;
import model.UserBean;
import model.Interface.IDAO;

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
 		ProcessBeanHibernateDAO dao = new ProcessBeanHibernateDAO(factory);
		insert(dao);

		
	}
	
	private static void insert(ProcessBeanHibernateDAO dao) {
		// insert
		try {
			/* NoteBean 
			NotesBean notes = new NotesBean();
			notes.setProcessId(0);
			notes.setRecordDate(new Date());
			notes.setSponsor("林彥亨");
			notes.setSource("106年第3次工作會議 106/5/9(二)");
			notes.setGrade("B01");
			notes.setDiscussMatter("建請教育部協助協調縣市政府，將初階認證線上審查作業規劃，訂於6/1後進行，提請討論。");
			notes.setPresentation("一、 精緻網須於各縣市完成初階認證審查人員培訓前，完成初階認證審查功能介面。"); 
			notes.setReference("xx");
			notes.setUpload("gg");
			*/
 			ProcessBean process = new ProcessBean();
 			process.setId(0);
 			process.setProcess_id(2);
  		    process.setReplyDate(new Date());
 		    process.setSponsor("林彥亨");
		    process.setStatus("111");
 		    process.setPresestation("A1");
		    process.setReference("ooo");
		    process.setUpload("ggg");
		 
 			trx = dao.getSession().beginTransaction();
 				dao.getSession().save(process);
			trx.commit();
		} catch (Exception e) {
			for (StackTraceElement s : e.getStackTrace())
			{
				System.out.println(s.toString());
			}
			System.out.println(e.toString());
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
 		return this.getSession().get(ProcessBean.class, id);
	}

	@Override
	public List<ProcessBean> select() {
 		return this.getSession()
 					.createQuery("from ProcessBean", ProcessBean.class)
 					.getResultList();
	}

	@Override
	public ProcessBean insert(ProcessBean bean) {
		ProcessBean tmp = select (bean.getId());
		if(tmp == null )
		{
			this.getSession().save(tmp);
		}
		
 		return tmp;
	}

	@Override
	public Boolean delete(ProcessBean bean) {
		ProcessBean tmp = select(bean.getId());
		if (tmp !=null)
		{
			this.getSession().delete(tmp);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Boolean delete(int id) {
		ProcessBean temp = select(id);
		if (temp != null) {
			this.getSession().delete(temp);
			return true;
		} else {
			return false;
		}	}

	@Override
	public ProcessBean update(ProcessBean bean) {
		ProcessBean tmp = select(bean.getId());
		if (tmp != null) {
			tmp.setId(bean.getId());
			//tmp.set
			 
		}
		return tmp;	}

	@Override
	public Session getSession() {
 		return factory.getCurrentSession();
	}

}
