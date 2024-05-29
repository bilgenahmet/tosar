<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:base title="Tosar">
<style type="text/css">
.message {
	position: relative;
}

.message-footer {
	margin-top: 5px;
}

.message-footer>* {
	display: inline;
}

.message .date {
	margin-left: 12px;
	margin-right: 12px;
}

.vote {
	position: absolute;
	right: 10px;
	top: 10px;
}

.vote-count {
	margin-bottom: 10px;
}

hr {
	margin-top: 10px;
	margin-bottom: 10px;
}
</style>
		<div>${user.name}</div>
		<div>${user.email}</div>
		<div>${user.username}</div>
		<div>${user.regDate}</div>
		<div>
			<a href="<c:url value='/u/${user.username}/edit' />">Değiştir</a>
		</div>
		<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<ul class="list-group">
				<c:forEach items="${messages}" var="message">
					<li class="message list-group-item">
						<h3>
							<a href="<c:url value='/messages/${message.id}' />">${message.title}</a>
						</h3>
						<div class="message-body">${message.message}</div>
						<div class="message-footer text-muted">
							<div>
								<a href="<c:url value='/u/${message.owner.username}' />"> ${message.owner.name}</a>
							</div>
							<div class="date">
								<small><fmt:formatDate value="${message.date}" pattern="dd.MM.yyyy HH:mm" /></small>
							</div>
							<div>
								<small> ${fn:length(message.comments)} yorum</small>
							</div>
						</div>
						<div class="vote text-center">
							<div class="vote-count">
								<span class="glyphicon glyphicon-heart-empty"></span> ${message.vote}
							</div>
							<a class="btn btn-success" href="<c:url value='/messages/${message.id}/vote' />"> <span
								class="glyphicon glyphicon-thumbs-up"></span>
							</a>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</t:base>