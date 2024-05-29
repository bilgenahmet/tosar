<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Yeni Mesaj</title>
</head>
<body>
	<c:url var="action" value="/messages/new" />
	<f:form action="${action}" method="post" commandName="message">
		<f:hidden path="id" />
		<div>
			<f:label path="title">Başlık:</f:label>
			<f:input path="title" />
		</div>
		<div>
			<f:label path="message">Mesajınız:</f:label>
			<f:input path="message" />
		</div>
		<div>
			<f:button>Kaydet</f:button>
		</div>
	</f:form>
</body>
</html>