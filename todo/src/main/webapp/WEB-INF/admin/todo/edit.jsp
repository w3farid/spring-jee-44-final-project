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

				<form class="form-floating" action="${contextPath}/todo/update"
					method="post">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="hidden" name="id"
						value="${entity.id}" />
					<div class="form-floating">
						<textarea name="content" class="form-control" placeholder="ToDo title" id="floatingTextarea">
								${entity.content.trim()}
							</textarea>
						<label for="floatingTextarea">Title</label>
					</div>
					 <div class="form-group">
			            <div class="row">
			            	<div class="col-md-6">
			            	<div class='input-group date'>
				               <input type='date' name="due_date" id="due_date" class="form-control" 
				               value="${entity.dueDate}"/>
				               <span class="input-group-addon">
				               <span class="glyphicon glyphicon-calendar">Due date</span>
				               </span>
				            </div>
			            </div>
			             <div class="col-md-2">
			            	<div class='input-group date'>
				               <input type='time' name="due_time" id="due_date" class="form-control" />
				               <span class="input-group-addon">
				               <span class="glyphicon glyphicon-calendar"></span>
				               </span>
				            </div>
			            </div>
			            </div>
			         </div>
					<button type="submit" class="btn btn-success">Update</button>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../layout/footer.jsp" />
<script>
	
</script>