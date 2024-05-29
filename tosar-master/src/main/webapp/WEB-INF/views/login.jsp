<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:base>
	<div class="row">
		<div class="col-md-4 col-sm-6 center-block" style="float: none;">
			<f:form action="login" method="post" commandName="user" role="form">
				<div class="form-group">
					<label for="username">Kullanıcı Adı:</label> <input type="text" name="username" class="form-control"
						placeholder="Kullanıcı Adı" />
				</div>
				<div class="form-group">
					<label for="password">Şifre:</label> <input type="password" name="password" class="form-control"
						placeholder="şifre" />
				</div>
				<div class="form-group">
					<f:button class="btn btn-primary pull-right">Giriş</f:button>
				</div>
			</f:form>
			<div class="text-center" style="margin-top: 80px">
				Henüz bir heabınız yok mu? Yeni hesap <a href="<c:url value="/new" />">oluşturun.</a>
			</div>
		</div>
	</div>
</t:base>
