<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link type="text/css" rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

<style>
.ui-li-thumb {
	top: 0;
	bottom: 0;
}
</style>

<title>Jacky Big Company</title>
</head>
<body>


	<form action="register" method='post'>
		請輸入帳號<input type='text' id='user' name='user' />
		<p>
			請輸入密碼<input type="password" id='passwd' name='passwd' />
		<p>
			確認密碼<input type="password" id='passwd2' name='passwd2' />
		<p>
			請輸入email<input type='text' id='email' name='email' />
		<p>
			請輸入中文名<input type='text' id='chname' name='chname' />
		<p>
			<input type="hidden" name="_charset_" value="UTF-8" /> <input
				type='submit' name='add_action' value='送出' />
	</form>

</body>
</html>