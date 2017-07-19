<%@page import="service.ProcessService"%>
<%@page import="model.misc.HibernateUtil"%>
<%@page import="model.dao.NotesBeanHibernateDAO"%>
<%@page import="model.dao.ProcessBeanHibernateDAO"%>
<%@page import="service.ProcessService"%>
<%@page import="service.NotesService"%>
<%@page import="model.NotesBean"%>
<%@page import="model.ProcessBean"%>

<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>
	$(document).ready(function() {
	});
</script>
<link type='text/css' rel="stylesheet" href='css/index.css'>
<style>
#register {
	position: fixed;
	top: 500px;
	left: 30%;
	width: 450px;
	background-color: #fff;
	color: #7F7F7F;
	padding: 20px;
	border: 2px solid #ccc;
	-moz-border-radius: 20px;
	-webkit-border-radius: 20px;
	-khtml-border-radius: 20px;
	-moz-box-shadow: 0 1px 5px #333;
	-webkit-box-shadow: 0 1px 5px #333;
	z-index: 101;
	-webkit-transition: all 3s ease-out;
	-ms-transition: all 3s ease-out;
	transition: all 3s ease-out;
}
</style>
<head>

<title>會議記錄</title>
</head>
<body>
	<%
		NotesService service = new NotesService(new NotesBeanHibernateDAO(HibernateUtil.getSessionFactory()));
		List<NotesBean> select = service.select();
		request.setAttribute("select", select);

		ProcessService processService = new ProcessService(
				new ProcessBeanHibernateDAO(HibernateUtil.getSessionFactory()));
		List<ProcessBean> process = processService.select();
		request.setAttribute("process", process);
	%>
	<div id='login'>
		<c:choose>
			<c:when test="${user.chineseName!=null}">
				<a href='logout.jsp'>登出</a>

				<c:out value="歡迎" />
				<c:out value="${user.chineseName}" />

				<a href='add_event.jsp?add_action=add'>新增案由</a>
			</c:when>
			<c:otherwise>
				<a href='login.jsp'>登入</a>
				<a href='register.jsp'>註冊</a>
			</c:otherwise>
		</c:choose>
		<p>
		<p>
	</div>


	<div id='summary-table'>
		摘要
		<table>
			<tr>
				<th rowspan='2' style="width: 8%; border: 1px solid black;">級別</th>
				<th rowspan='2' style="width: 8%; border: 1px solid black;">處理原則</th>
				<th rowspan='2' style="width: 8%; border: 1px solid black;">數量</th>
				<th colspan='4' style="width: 8%; border: 1px solid black;">本次會議建議處理件數說明</th>
			</tr>
			<tr>
				<th class='table1' style="width: 8%; border: 1px solid black;">維持</th>
				<th class='table1' style="width: 8%; border: 1px solid black;">轉為B級</th>
				<th class='table1' style="width: 8%; border: 1px solid black;">轉為B級</th>
				<th class='table1' style="width: 8%; border: 1px solid black;">解除列管</th>
			</tr>
			<tr>
				<td>A級</td>
				<td>於會議中提出報告</td>
				<td>7</td>
				<td>1</td>
				<td>2</td>
				<td>1</td>
				<td>3</td>
			</tr>
			<tr>
				<td>B級</td>
				<td>承辦人檢視追蹤</td>
				<td>5</td>
				<td>1</td>
				<td>0</td>
				<td>1</td>
				<td>3</td>
			</tr>
			<tr>
				<td>C級</td>
				<td>列入議程，由團隊追蹤，並依情節回報承辦人</td>
				<td>1</td>
				<td>0</td>
				<td>1</td>
				<td>0</td>
				<td>0</td>
			</tr>
			<tr>
				<td>解列</td>
				<td>提出說明恭請裁示</td>
				<td>B03,B04,B05,A05,A06,A07</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
	<div id='summary-table'>
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
						<td>${row.discussMatter}</td>
						<td>${row.presentation}</td>

						<!-- 辦理情形 -->
						<td>
							<table style="border: 0px">
								<c:forEach var="data" items="${process}">
									<tr style="border: 0px">
										<td style="border: 0px"><c:if
												test="${row.processId==data.process_id}">
												 ${data.id} 
												 ${data.status} 
												  
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
												<c:out value="${data.id}" escapeXml="true"/>

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

				</c:forEach>
			</c:if>
		</table>
	</div>





</body>
</html>