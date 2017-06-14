<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>
	var formObj;

	$(function() {
		formObj = $("form[role='form']");
		console.log(formObj);
	});

	$(document).on("click", ".btn-warnin", function() {
		formObj.attr("action", "/board/modify");
		formObj.attr("method", "get");
		formObj.submit();
	});

	$(document).on("click", ".btn-danger", function() {
		formObj.attr("method","get");
		formObj.attr("action", "/board/remove");
		formObj.submit();
	});

	$(document).on("click", ".btn-primary", function() {
		formObj.attr("method","get");
		formObj.attr("action", "/board/listPage");
		formObj.submit();
	});
</script>
<%@ include file="../include/header.jsp"%>

<form role="form" method="post">
	<input type="hidden" name="bno" value="${guestVO.bno }">
	<input type="hidden" name="page" value="${cri.page }">
	<input type="hidden" name="perPageNum" value="${cri.perPageNum }">
</form>

<div class="box-body">
	<div class="form-group">
		<label for="exampleInputEmail1">title</label> <input type="text"
			name="title" class="form-control" placeholder="Enter Title"
			value="${guestVO.title}" readonly="readonly">
	</div>

	<div class="form-group">
		<label for="exampleInputPassword1">Content</label><br>
		<textarea name="content" rows="3" style="width: 100%;" placeholder=""
			readonly="readonly">${guestVO.content}</textarea>
	</div>

	<div class="form-group">
		<label for="exampleInputEmail1">Writer</label> <input type="text"
			name="writer" class="form-control" placeholder="Enter Writer"
			readonly="readonly" value="${guestVO.writer }">
	</div>
</div>
<!-- /.box-body -->

<div class="box-footer">
	<button type="submit" class="btn btn-warnin">Modify</button>
	<button type="submit" class="btn btn-danger">Remove</button>
	<button type="submit" class="btn btn-primary">LIST ALL</button>
</div>
<%@ include file="../include/footer.jsp"%>