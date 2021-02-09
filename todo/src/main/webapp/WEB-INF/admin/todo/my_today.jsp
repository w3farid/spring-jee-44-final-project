<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp" />
<div class="content">
	<div class="container-fluid">		
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