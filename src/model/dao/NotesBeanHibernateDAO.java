package model.dao;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.sql.ordering.antlr.Factory;

import model.misc.HibernateUtil;
import model.NotesBean;
import model.UserBean;
import model.Interface.IDAO;

public class NotesBeanHibernateDAO implements IDAO<NotesBean> {
	public static Session session;
	public static SessionFactory factory;
	public static Transaction trx;

	public static void main(String[] args) {
 		System.out.println("test");
	}

	 

	public NotesBeanHibernateDAO(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public NotesBean select(NotesBean bean) {
		if (bean != null) {
			bean = this.getSession().get(NotesBean.class, bean.getProcessId());
			
		}
		return bean;
	}

	@Override
	public NotesBean select(int id) {
		return this.getSession().get(NotesBean.class, id);
	}

	@Override
	public List<NotesBean> select() {
		return this.getSession().createQuery("from NotesBean", NotesBean.class).getResultList();
	}

	@Override
	public NotesBean insert(NotesBean bean) {
		NotesBean tmp = select(bean);
		if (tmp == null) {
			this.getSession().save(bean);
			System.out.println("tmp bean" + tmp);
		}

		return bean;
	}

	@Override
	public Boolean delete(NotesBean bean) {
		NotesBean tmp = select(bean.getProcessId());
		if (tmp != null) {
			return delete(bean.getProcessId());
		} else {
			return false;
		}
	}

	@Override
	public Boolean delete(int id) {
		NotesBean temp = select(id);
		if (temp != null) {
			this.getSession().delete(temp);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public NotesBean update(NotesBean bean) {
		NotesBean tmp = select(bean.getProcessId());
		if (tmp != null) {
			tmp.setProcessId(bean.getProcessId());
			tmp.setRecordDate(new Date());
			tmp.setSponsor(bean.getSponsor());
			tmp.setGrade(bean.getGrade());
			tmp.setDiscussMatter(bean.getDiscussMatter());
			tmp.setSource(bean.getSource());
			tmp.setPresentation(bean.getPresentation());
			tmp.setReference(bean.getReference());
			tmp.setUpload(bean.getUpload());
			tmp.setControl(bean.getControl());
			tmp.setControlStatus(bean.getControlStatus());

		 
		}
		return tmp;
	}
	
	public NotesBean update(int id,Boolean control,int controlStatus)
	{
		NotesBean tmp = select(id);
		System.out.println("update tmp"+tmp+""+id);
		if (tmp != null) {
			tmp.setProcessId(tmp.getProcessId());
			tmp.setRecordDate(new Date());
			tmp.setSponsor(tmp.getSponsor());
			tmp.setGrade(tmp.getGrade());
			tmp.setDiscussMatter(tmp.getDiscussMatter());
			tmp.setSource(tmp.getSource());
			tmp.setPresentation(tmp.getPresentation());
			tmp.setReference(tmp.getReference());
			tmp.setUpload(tmp.getUpload());
			tmp.setControl(control);
			tmp.setControlStatus(controlStatus);
		 
		}
		return tmp;
	}

	@Override
	public Session getSession() {
		return factory.getCurrentSession();
	}


}
