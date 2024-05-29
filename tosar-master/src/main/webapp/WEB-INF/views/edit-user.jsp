<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:base title="Hesap Güncelle ${user.name }">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<c:url var="action" value="/u/{username}/edit" />
				<f:form action="${action}" method="post" commandName="user">
					<f:hidden path="id" />
					<div class="form-group">
						<f:label path="name" cssClass="control-label">Ad soyad:</f:label>
						<f:input path="name" cssClass="form-control" />
					</div>
					<div class="form-group">
						<f:label path="username" cssClass="control-label">Kullanıcı Adı ${user.username } değiştirilemez!!!</f:label>
					</div>
					<div class="form-group">
						<f:label path="email" cssClass="control-label">Eposta:</f:label>
						<f:input path="email" cssClass="form-control" />
					</div>
					<div class="form-group">
						<f:label path="password" cssClass="control-label">Şifre:</f:label>
						<f:password path="password" cssClass="form-control" />
					</div>
					<div>
						<f:button class="btn btn-primary pull-right">Kaydet</f:button>
					</div>
				</f:form>
			</div>
		</div>
</t:base>