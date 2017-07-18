<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel="stylesheet" href='css/index.css'>
<script src="https://cdn.ckeditor.com/4.7.1/standard/ckeditor.js"></script>


<title>Jacky Big Company</title>
</head>
<body>
	 <div >
		<c:choose >
			<c:when test="${user.chineseName!=null}">
			<a href='logout.jsp'  >登出</a>
  					 <c:out value="歡迎"/> <c:out value="${user.chineseName}" />
 						
			</c:when>
			<c:otherwise>
			 <a href='login.jsp'  >登入</a>
			  <a href='register.jsp'  >註冊</a>
			</c:otherwise>			
		</c:choose>
		<p>
		<p>
		</div>
		<div id = 'editor'>
		<form  action='notes' method='post' enctype='multipart/form-data'>
			<c:if test="${param.processId==''}">
				<c:set var="processId" scope="session" value="0"/>
			</c:if>
		    <input type="hidden" id='processId' name='processId' value="${param.processId}"/>
			
			列管事項來源:<textarea rows='10' cols='80' id='souece' name='source' readonly>${param.source}</textarea><p>
			等級: <input type='text' id='grade' name='grade' value="${param.grade}" readonly/><p>
			討論事項:<textarea rows='10' cols='100' id='discuss_matter' name='discuss_matter'readonly>${param.discuss_matter}</textarea><p>
			決議:<textarea rows='20' cols='100' id='presentation' name='presentation'readonly>${param.presentation}</textarea><p>
			<a href='upload/<c:out value="${param.upload}"/>' download="${row.upload}" class='button' target='_blank'  >下載</a> 
			<P>
			辦理情形<textarea rows='10' cols='100' id='status' name='status' >${param.status}</textarea><p>
			 
			參考:<input type='text' id='reference' name='reference' value="${param.reference}" readonly/>
			上傳:<input type='file' id='upload' name='upload' value="${param.upload}" readonly/><p>
			
			
			<input hidden name='sponsor' value="${user.chineseName}">
 			<c:if test= "${param.add_action=='add' }"> 
 			 	<input type='submit' name='add_notes' value='新增'/>
 			
 			</c:if>
 			<c:if test= "${param.edit_action=='edit' }"> 
				<input type='submit' name='edit_notes' value='編輯'/> 
			</c:if>
		</form>
	</div>

	<script>
      CKEDITOR.replace( 'souece' );
      CKEDITOR.replace( 'discuss_matter' );
      CKEDITOR.replace( 'presentation' );
      CKEDITOR.replace( 'status' );
    </script>
</body>
</html>