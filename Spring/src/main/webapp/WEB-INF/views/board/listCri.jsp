<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<%@ include file="../include/header.jsp"%>

<script>

	$(function() {
		var result = '${msg}';
		if (result == 'SUCCESS') {
			alert("처리가 완료 되었습니다");
		}
	});
</script>
<table class="table table-bordered">
	<tr>
		<th style="width: 10px">BNO</th>
		<th>TITLE</th>
		<th>WRITER</th>
		<th>REGDATE</th>
		<th style="width: 40px">VIEWCNT</th>
	</tr>

	<c:forEach var="guestVO" items="${list}">
		<tr>
			<td>${guestVO.bno}</td>
			<td><a href="/board/read?bno=${guestVO.bno}">${guestVO.title}</a></td>
			<td>${guestVO.writer}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
					value="${guestVO.regdate}" /></td>
			<td><span class="badge bg_red">${guestVO.viewcnt}</span>
		</tr>
	</c:forEach>
</table>

<%@ include file="../include/footer.jsp"%>