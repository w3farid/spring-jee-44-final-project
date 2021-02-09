<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp" />
<div class="content">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<c:if test='${status.equals("success")}'>
					<div class="alert alert-primary" role="alert">${message}</div>
				</c:if>

				<c:if test='${status.equals("error")}'>
					<div class="alert alert-danger" role="alert">${message}</div>
				</c:if>

				<form class="form-floating" action="/todo/add" method="post">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="form-floating">
						<textarea name="content" class="form-control"
							placeholder="Leave a comment here" id="floatingTextarea"></textarea>
						<label for="floatingTextarea">Comments</label>
					</div>
					<button type="submit" class="btn btn-success">Add</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Title</th>
							<th scope="col">Status</th>
							<th scope="col">Created date</th>
							
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="todo" items="${entityList}">
							<tr>
								<th scope="row">${todo.id}</th>
								<td>${todo.title}</td>
								<td>${todo.status}</td>
								<td>${todo.createdDate}</td>
								<td>

									<a href="${contextPath}/todo/edit/${todo.id}" >
										<i class="material-icons">edit</i>
									</a>
									<a href="${contextPath}/todo/delete/${todo.id}" >
										<i class="material-icons">delete</i>
									</a>
									<a href="${contextPath}/todo/done/${todo.id}">
										<i class="material-icons">done</i>
									</a>
								</td>
							</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../layout/footer.jsp" />