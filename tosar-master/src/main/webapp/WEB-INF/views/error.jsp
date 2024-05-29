<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:base title="${exceptionclass}">
	<div class="row">
		<div class="col-md-4 col-sm-6 center-block" style="float: none;">
			<div class="text-center" style="margin-top: 80px">
				${exceptionmessage} <a href="<c:url value="/messages" />">oluşturun.</a>
			</div>
		</div>
	</div>
</t:base>
