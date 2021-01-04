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
						<textarea name="content" class="form-control"
							placeholder="Leave a comment here" id="floatingTextarea">
								${entity.content}
							</textarea>
						<label for="floatingTextarea">Comments</label>
					</div>
					<div class="form-group">
						<input type='text' class="form-control" id='datetimepicker4' />
					</div>
					<div class="form-group">
						<div class="custom-file">
							<input type="file" class="custom-file-input" id="customFile">
							<label class="custom-file-label" for="customFile">Choose
								file</label>
						</div>
					</div>
					<button type="submit" class="btn btn-success">Update</button>
				</form>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../layout/footer.jsp" />
<script type="text/javascript">
	$(function() {
		$('#datetimepicker4').datetimepicker();
	});
</script>