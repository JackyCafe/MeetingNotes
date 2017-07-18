package model.controller;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sun.corba.se.impl.ior.GenericTaggedComponent;

import model.NotesBean;
import model.dao.NotesBeanHibernateDAO;
import model.dao.UserBeanHibernateDAO;
import model.misc.HibernateUtil;
import service.NotesService;
import service.UserBeanService;

/**
 * Servlet implementation class Notes
 */

@WebServlet(urlPatterns = { "/notes" }, initParams = {
		@WebInitParam(name = "upload-path", value = "/MeetingNotes/WebContent/upload") })
@MultipartConfig
public class NotesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public NotesService service;

	public NotesServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		service = new NotesService(new NotesBeanHibernateDAO(HibernateUtil.getSessionFactory()));
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		Map<String, String[]> maps = request.getParameterMap();
		String[] name = new String[maps.size()];
		String[] value = new String[maps.size()];
		int i = 0;
		for (String key : maps.keySet()) {
			name[i] = key;
			value[i] = maps.get(key)[0];
			i++;
		}

		String process_id;
		int id;
		if (request.getParameter("processId") != null)
			process_id = request.getParameter("processId");
		else
			process_id = "0";

		if (process_id.equals(""))
			id = 0;
		else
			id = Integer.valueOf(process_id);
		Date record_date = new Date();
		String sponsor = request.getParameter("sponsor");
		String source = request.getParameter("source");
		String discuss_matter = request.getParameter("discuss_matter");
		String grade = request.getParameter("grade");
		
		String presentation = request.getParameter("presentation");
		String reference = request.getParameter("reference");
		String uploadPath = " ";
		// process upload
		Part part = request.getPart("upload");
		String header = part.getHeader("Content-Disposition");
		String filename = part.getSubmittedFileName();
		if (!filename.equals("")) {
			long size = part.getSize();
			byte[] buf = new byte[(int) size];
			InputStream in = part.getInputStream();
			uploadPath = getServletConfig().getInitParameter("upload-path");
			uploadPath = uploadPath + "/" + filename;
			FileOutputStream fout = new FileOutputStream(uploadPath);
			BufferedInputStream bin = new BufferedInputStream(in);
			bin.read(buf);
			fout.write(buf);
			fout.close();
		}

		// construct bean
		NotesBean notes = new NotesBean();
		notes.setProcessId(id);
		notes.setSponsor(sponsor);
		notes.setRecordDate(record_date);
		notes.setSource(source);
		notes.setDiscussMatter(discuss_matter);
		notes.setGrade(grade);
		notes.setPresentation(presentation);
		notes.setReference(reference);
		notes.setUpload(filename);
 
		if (name[maps.size() - 1].equals("add_notes")) {
			// System.out.println(notes);
			service.insert(notes);
			List<NotesBean> select = service.select();
			request.setAttribute("select", select);
			response.sendRedirect("index.jsp");
		}
		if (name[maps.size() - 1].equals("edit_notes")) {
			service.update(notes);
			response.sendRedirect("index.jsp");
		}

		//
		doGet(request, response);

	}

}
