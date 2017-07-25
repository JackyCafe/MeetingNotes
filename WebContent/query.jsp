<%@page import="model.misc.HibernateUtil"%>
<%@page import="model.dao.NotesBeanHibernateDAO"%>
<%@page import="model.dao.ProcessBeanHibernateDAO"%>

<%@page import="model.NotesBean"%>
<%@page import="model.ProcessBean"%>

<%@page import="service.NotesService"%>
<%@page import="service.ProcessService"%>

<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel="stylesheet" href='css/index.css'>

<title>Jacky Big Company</title>
</head>
<body>
	<%
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		
		NotesService ns = new NotesService(new NotesBeanHibernateDAO(HibernateUtil.getSessionFactory()));
		
		List<NotesBean> select = null;
		if (name !=null&&value!=null) {
		 	  select = ns.selectByParam(name,value);
		}else{
			 select = ns.select();

		}
		request.setAttribute("select", select);
		ProcessService processService = new ProcessService(
				new ProcessBeanHibernateDAO(HibernateUtil.getSessionFactory()));
		List<ProcessBean> process = processService.select();
		request.setAttribute("process", process);
 	%>

	明細
	<table>
		<tr>
			<th style="width: 8%; border: 1px solid black;">列管事項來源</th>
			<th style="width: 7%; border: 1px solid black;">分級列管(註1)</th>
			<th style="width: 30%; border: 1px solid black;">討論事項</th>
			<th style="width: 30%; border: 1px solid black;">決議/裁示事項</th>
			<th style="width: 15%; border: 1px solid black;">辦理情形</th>
			<th style="width: 5%; border: 1px solid black;">本次會議裁示</th>
			<th style="width: 5%; border: 1px solid black;">功能</th>
		</tr>
		<c:if test="${not empty select}">
			<c:forEach var="row" items="${select}">
				<c:if test="${row.control == false}">
					<c:url value="/add_reply.jsp" var="replypath">
						<c:param name="processId" value="${row.processId}" />
						<c:param name="source" value="${row.source}" />
						<c:param name="grade" value="${row.grade}" />
						<c:param name="discuss_matter" value="${row.discussMatter}" />
						<c:param name="presentation" value="${row.presentation}" />
						<c:param name="reference" value="${row.reference}" />
						<c:param name="upload" value="${row.upload}" />

						<c:param name="add_action" value="add" />

					</c:url>
					<c:url value="/add_event.jsp" var="editpath">
						<c:param name="processId" value="${row.processId}" />
						<c:param name="source" value="${row.source}" />
						<c:param name="grade" value="${row.grade}" />
						<c:param name="discuss_matter" value="${row.discussMatter}" />
						<c:param name="presentation" value="${row.presentation}" />

						<c:param name="edit_action" value="edit" />
					</c:url>
					<tr>
						<td>${row.source}</td>
						<td>${row.grade}</td>
						<td>
						${row.discussMatter}
						(${row.sponsor},${row.recordDate})
						</td>
						<td>${row.presentation}</td>

						<!-- 辦理情形 -->
						<td>
							<table style="border: 0px">
								<c:forEach var="data" items="${process}" varStatus="status">
									<tr style="border: 0px">
										<td style="border: 0px"><c:if
												test="${row.processId==data.process_id}">
												<c:out value="${status.count}" />
												<p>
													<c:out value="${data.status}" escapeXml="false" />
												<p>(${data.sponsor},${data.replyDate})
											</c:if></td>
									</tr>
								</c:forEach>
							</table>
						</td>


						<!-- 本次會議裁示 -->
						<td>
							<table style="border: 0px">

								<c:forEach var="data" items="${process}">
									<tr style="border: 0px">
										<td style="border: 0px"><c:if
												test="${row.processId==data.process_id}">
												<c:out value="${data.id}" escapeXml="true" />

												<c:out value="${data.presestation}" escapeXml="true" />
											</c:if>
								</c:forEach>
								</td>
								</tr>
							</table>
						</td>
						<!-- 功能 -->
						<td><c:if test="${ not empty row.upload}">
								<a href='upload/<c:out value="${row.upload}"/>'
									download="${row.upload}" class='button' target='_blank'>下載</a>
								<p>
							</c:if> <c:if test="${user.chineseName !=null }">
								<a href="${replypath}" class='button'>回覆</a>
								<p>
							</c:if> <c:if test="${row.sponsor == user.chineseName }">
								<a href="${editpath}" class='button'>編輯</a>

							</c:if></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>