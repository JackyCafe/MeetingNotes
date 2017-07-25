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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


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
<script>
	function query(s) {
		var value = document.getElementById('search').value;
		if (s == 0) {
			$.ajax({
				url : 'query.jsp',
				type : 'post',
				success : doSuccess,
				error : doError
			});
		} else if (s == 1) {
			$.ajax({
				url : 'query.jsp?name=discuss_matter&value=' + value,
				type : 'post',
				success : doSuccess,
				error : doError
			});
		} else if (s == 2) {
			$.ajax({
				url : 'query.jsp?name=presentation&value=' + value,
				type : 'post',
				success : doSuccess,
				error : doError
			});
		} else if (s == 3) {
			$.ajax({
				url : 'query.jsp?name=grade&value=' + value,
				type : 'post',
				success : doSuccess,
				error : doError
			});

		}else if (s == 4) {
			$.ajax({
				url : 'query.jsp?name=record_date&value=' + value,
				type : 'post',
				success : doSuccess,
				error : doError
			});

		}else if (s == 5) {
			$.ajax({
				url : 'query.jsp?name=sponsor&value=' + value,
				type : 'post',
				success : doSuccess,
				error : doError
			});

		}

		function doSuccess(json) {
			$('#summary-table').html(json);
		}
		function doError(json) {
			$('#summary-table').html(json);
		}
	}
	function query_select(s) {
		query(s);
	}
	$(document).ready(function() {
		query();
		query_select(0);
	})
</script>
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

	<!-- jstl setup  jndi -->
	<sql:query var="summary1" dataSource="jdbc/xxx">
        SELECT substr(grade,1,1) as grade,count(substr(grade,1,1)) as val 
        FROM Notes
        GROUP BY substr(grade,1,1)  
    </sql:query>
	<sql:query var="summary2" dataSource="jdbc/xxx">
        Select ControlStatus  ,Count(ControlStatus) as val
		from Notes
		group By ControlStatus
    </sql:query>


	<input type='text' name='search' id='search'>
	<select id='choice' name='choice'
		onchange='query_select(this.options[this.options.selectedIndex].value)'>
		<option value="0">重設</option>
		<option value="1">討論事項 </option>
		<option value="2">決議</option>
		<option value="3">分級列管</option>
		<option value="4">發文日期</option>
		<option value="5">發文者</option>


	</select>


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
		<a href='history.jsp'>歷史資料</a>
		<p>
		<p>
	</div>


	<div class='summary-table'>
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
				<th class='table1' style="width: 8%; border: 1px solid black;">轉為C級</th>
				<th class='table1' style="width: 8%; border: 1px solid black;">解除列管</th>
			</tr>
			<tr>
				<td>A級</td>
				<td>於會議中提出報告</td>
				<td><c:forEach var="row" items="${summary1.rows}">
						<c:if test='${ row.grade=="A"}'>
							<c:out value="${row.val}" />
						</c:if>
					</c:forEach></td>
				<td>
					<!-- 維持 --> <c:forEach var="row" items="${summary2.rows}">
						<c:if test='${row.ControlStatus=="999"}'>
					 ${row.val}
					</c:if>
					</c:forEach>

				</td>
				<td>
					<!-- 轉為B級 --> <c:forEach var="row" items="${summary2.rows}">
						<c:if test='${ row.ControlStatus=="2" }'>
					 ${row.val}
					</c:if>
					</c:forEach>
				</td>
				<td>
					<!-- 轉為C級 --> <c:forEach var="row" items="${summary2.rows}">
						<c:if test='${ row.ControlStatus=="3" }'>
					 ${row.val}
					</c:if>
					</c:forEach>
				</td>
				<td>
					<!-- 解除列管 --> <c:forEach var="row" items="${summary2.rows}">
						<c:if test='${ row.ControlStatus=="0" }'>
					 ${row.val}
					</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>B級</td>
				<td>承辦人檢視追蹤</td>
				<td><c:forEach var="row" items="${summary1.rows}">
						<c:if test='${ row.grade=="B"}'>
							<c:out value="${row.val}" />
						</c:if>
					</c:forEach></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>C級</td>
				<td>列入議程，由團隊追蹤，並依情節回報承辦人</td>
				<td><c:forEach var="row" items="${summary1.rows}">
						<c:if test='${ row.grade=="C"}'>
							<c:out value="${row.val}" />
						</c:if>
					</c:forEach></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
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
	<div id='summary-table' class='summary-table'></div>





</body>
</html>