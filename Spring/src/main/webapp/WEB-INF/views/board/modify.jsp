<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>
	$(function() {
		var formObj = $("form[role='form']");
		console.log(formObj);

		$(".btn-primary").on("click", function() {
			formObj.submit();
		});
		$(".btn-warnin").on("click", function() {
			self.location = "/board/listPage";
		});
	});
</script>
<%@ include file="../include/header.jsp"%>

<form role="form" method="post">
	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">BNO</label> <input type="text"
				name="bno" class="form-control" value="${guestVO.bno}"
				readonly="readonly">
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Title</label> <input type="text"
				name="title" class="form-control" value="${guestVO.title}">
		</div>

		<div class="form-group">
			<label for="exampleInputPassword1">Content</label><br>
			<textarea name="content" rows="3" style="width: 100%;" placeholder="">${guestVO.content}</textarea>
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label> <input type="text"
				name="writer" class="form-control" placeholder="Enter Writer"
				value="${guestVO.writer }">
		</div>
	</div>
</form>

<div class="box-footer">
	<button type="submit" class="btn btn-primary">SAVE</button>
	<button type="submit" class="btn btn-warnin">CANCEL</button>
</div>
<%@ include file="../include/footer.jsp"%>
