package service;

import java.util.List;
import model.NotesBean;
import model.Interface.IService;
import model.dao.NotesBeanHibernateDAO;
 
public class NotesService implements IService<NotesBean> {
	public static NotesBeanHibernateDAO dao;
	 
	 
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

	@Override
	public Boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public NotesBean update(NotesBean bean) {
		return dao.update(bean);
	}

}
