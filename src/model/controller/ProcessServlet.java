package model.controller;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

import model.ProcessBean;
import model.UserBean;
import model.dao.NotesBeanHibernateDAO;
import model.dao.ProcessBeanHibernateDAO;
import model.misc.HibernateUtil;
import service.NotesService;
import service.ProcessService;

/**
 * Servlet implementation class ProcessServlet
 */
@WebServlet(urlPatterns = { "/process" }, initParams = {
		@WebInitParam(name = "upload-path", value = "/MeetingNotes/WebContent/reply") })
@MultipartConfig
public class ProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProcessService service = null;
	private NotesService noteService;
	private PrintWriter out = null;

	@Override
	public void init() throws ServletException {
		service = new ProcessService(new ProcessBeanHibernateDAO(HibernateUtil.getSessionFactory()));
		noteService = new NotesService(new NotesBeanHibernateDAO(HibernateUtil.getSessionFactory()));

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
		out = response.getWriter();

		Map<String, String[]> maps = request.getParameterMap();
		String[] name = new String[maps.size()];
		String[] value = new String[maps.size()];
		int processId = 0;
		int id = 0;

		int i = 0;
		for (String key : maps.keySet()) {
			name[i] = key;
			value[i] = maps.get(key)[0];
			System.out.println(name[i] + ":" + value[i]);
			i++;
		}

		if (request.getParameter("id") != null)
			id = Integer.valueOf(request.getParameter("processId"));
		if (request.getParameter("processId") != null)
			processId = Integer.valueOf(request.getParameter("processId"));

		String presestation = request.getParameter("presestation");
		UserBean usr = (UserBean) request.getSession().getAttribute("user");
		String status = request.getParameter("status");
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

		ProcessBean bean = new ProcessBean();

		bean.setId(id);
		bean.setProcess_id(processId);
		bean.setReplyDate(new Date());
		bean.setSponsor(usr.getChineseName());
		bean.setStatus(status);
		bean.setPresestation(presestation);
		bean.setReference(reference);
		bean.setUpload(filename);
		// 解除列管 status =>0
		if (presestation.equals("解除列管")) {
			System.out.println(presestation);
			noteService.update(processId, true, 0);
		}else

		// 轉為A級
		if (presestation.equals("轉為A級")) {
			System.out.println(presestation);
			noteService.update(processId, false, 1);
		}else

		// 轉為B級
		if (presestation.equals("轉為B級")) {
			System.out.println(presestation);
			noteService.update(processId, false, 2);
		}else

		// 轉為C級
		if (presestation.equals("轉為C級")) {
			System.out.println(presestation);
			noteService.update(processId, false, 3);
		}else

		// 持續列管
		if (presestation.equals("持續列管")) {
			System.out.println(presestation);
			noteService.update(processId, false,999);
		}

		if (name[maps.size() - 1].equals("add_notes")) {
			ProcessBean insert = service.insert(bean);
			response.sendRedirect("index.jsp");

		}

		doGet(request, response);

	}

}
