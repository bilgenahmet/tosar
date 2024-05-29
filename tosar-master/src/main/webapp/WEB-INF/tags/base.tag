<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ attribute name="header" fragment="true" required="false"%>
<%@ attribute name="title" required="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
body {
	padding-top: 50px;
	padding-bottom: 60px;
}

#body {
	margin-top: 10px;
}
</style>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<jsp:invoke fragment="header" />

<title>${title}</title>

<c:url var="url" value="/" />
<link href="${url}css/bootstrap.min.css" rel="stylesheet">

<!--[if lt IE 9]>
	<script src="//oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="//oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<c:url var="home" value="/" />
				<a class="navbar-brand" href="${home }">Tosar</a>
			</div>
			<c:if test="${!hideLoginBtn }">
				<div class="navbar-collapse collapse">
					<sec:authorize access="isAnonymous()">
						<c:url var="login" value="/login" />
						<a class="btn btn-success navbar-right" href="${login }" style="margin: 8px -15px;">Giriş</a>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<c:url var="logout" value="/logout" />
						<form class="navbar-form navbar-right" role="form" action="${logout }" method="post">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<button type="submit" class="btn btn-warning">Çıkış</button>
						</form>
						<c:url var="userhome" value="/u/${pageContext.request.remoteUser}" />
						<a class="navbar-brand navbar-right" href="${userhome}">${pageContext.request.remoteUser}</a>
					</sec:authorize>
				</div>
			</c:if>
		</div>
	</div>
	<div id="body" class="container">
		<jsp:doBody />
	</div>

	<script src="${url}js/bootstrap.min.js"></script>
</body>
</html>
