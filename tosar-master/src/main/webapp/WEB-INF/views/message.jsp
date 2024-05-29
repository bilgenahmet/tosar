<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:base title="${message.title}">

	<style type="text/css">
body {
	background-color: #ededed;
}

.message {
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 4px;
	padding-bottom: 6px;
}

.message-body {
	margin-top: 8px;
}

.message-footer {
	margin: 10px 0;
}

.message-footer>* {
	display: inline-block;
}

.message h3 {
	margin-top: 10px;
}

li {
	background-color: #ededed;
	padding: 6px;
	margin: 4px 0;
}

.date {
	font-size: 12px;
}
.delete-comment{
	font-size: 13px;
}
</style>
	<div class="row">
		<div class="message col-md-8 col-md-offset-2">
			<h3>${message.title}</h3>
			<div class="message-body">${message.message}</div>
			<hr>
			<div class="message-footer">
				<div>
				<a href="<c:url value='/u/${message.owner.username}' />">${message.owner.name}</a>
				</div>
				<div class="date">
					<fmt:formatDate value="${message.date}" pattern="dd.MM.yyyy HH:mm" />
				</div>
				<a class="btn btn-default" href="<c:url value='/messages/${message.id}/vote' />" title="Oyla">${message.vote} <span
					class="glyphicon glyphicon-thumbs-up"></span>
				</a>
				<div>
					<a class="btn btn-warning" href="<c:url value='/messages/${message.id}/edit' />">Değiştir</a>
				</div>
				<div>
					<a class="btn btn-danger" href="<c:url value='/messages/${message.id}/delete' />">Sil</a>
				</div>
				<div>
					<a class="btn btn-primary" href="#new-comment">Yorum Yap</a>
				</div>
			</div>

			<h4 id="comments">Yorumlar</h4>
			<ul class="list-unstyled">
				<c:forEach items="${message.comments}" var="comment">
					<li class="comment">
						<div>
							<a href="<c:url value='/u/${comment.user.username}' />">${comment.user.name}</a>
						</div>
						<p>${comment.comment}</p>
						<div class="date">
							<fmt:formatDate value="${comment.date}" pattern="dd.MM.yyyy HH:mm" />
						</div>
						<div class="delete-comment">
							<a href="<c:url value='/comments/${comment.id}/delete' />">Yorumu Sil</a>
						</div>
					</li>
				</c:forEach>
			</ul>
			<div id="new-comment">
				<h4>Yorum yaz:</h4>
				<c:url var="action" value="/comments/new/${message.id}" />
				<f:form method="post" action="${action }" commandName="newComment" role="form">
					<f:hidden path="id" />
					<f:textarea path="comment" class="form-control" />
					<f:button class="btn btn-primary pull-right" style="margin-top:6px">Gönder</f:button>
				</f:form>
			</div>
		</div>
	</div>
</t:base>