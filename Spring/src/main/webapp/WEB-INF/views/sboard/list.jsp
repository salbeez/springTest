<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<%@ include file="../include/header.jsp"%>

<script>

	$(function() {
		var result = '${msg}';
		if (result == 'SUCCESS') {
			alert("처리가 완료 되었습니다");
		}
	});
	
	$(document).on("click","#searchBtn",function(){
		self.location = "list"+"${pageMaker.makeQuery(1)}"
									+"&searchType="+$("select option:selected").val()
									+"&keyword="+encodeURIComponent($('#keywordInput').val());
	});
	$(document).on("click","#newBtn",function(){
		self.location = "register";
	});
</script>
<div class="box-body">
	<select name="searchType">
		<option value="n"
			<c:out value="${cri.searchType ==null? 'selected' : '' }"/>>---</option>
		<!-- 검색 조건 없음 -->
		<option value="t"
			<c:out value="${cri.searchType eq 't'? 'selected' : '' }"/>>Title</option>
		<option value="c"
			<c:out value="${cri.searchType eq 'c'? 'selected' : '' }"/>>Content</option>
		<option value="w"
			<c:out value="${cri.searchType eq 'w'? 'selected' : '' }"/>>Writer</option>
		<option value="tc"
			<c:out value="${cri.searchType eq 'tc'? 'selected' : '' }"/>>Title
			or Writer</option>
		<option value="cw"
			<c:out value="${cri.searchType eq 'cw'? 'selected' : '' }"/>>Content
			or Writer</option>
		<option value="tcw"
			<c:out value="${cri.searchType eq 'tcw'? 'selected' : '' }"/>>Title
			or Content or Writer</option> >
	</select> <input type="text" name="keyword" id="keywordInput"
		value="${cri.keyword }">
		<!-- ${cri.keyword } ${fn:replace(String, '바꾸고 싶은것', '바꿀것')}-->
	<button id="searchBtn">Search</button>
	<button id="newBtn">New Board</button>
</div>

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
			<td><a
				href="/sboard/readPage${pageMaker.makeSearch(pageMaker.cri.page) }&bno=${guestVO.bno}">${guestVO.title} <strong>[ ${guestVO.replycnt} ]</strong> </a></td>
			<td>${guestVO.writer}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
					value="${guestVO.regdate}" /></td>
			<td><span class="badge bg_red">${guestVO.viewcnt}</span>
		</tr>
	</c:forEach>
</table>


<div class="text-center">
	<ul class="pagination">
		<c:if test="${pageMaker.prev}">
			 <li><a href="list${pageMaker.makeSearch(pageMaker.startpage -1)}">&laquo;</a></li> 
			<%-- <li><a href="list?page=${pageMaker.startpage -1}">&laquo;</a></li> --%>
		</c:if>

		<c:forEach var="idx" begin="${pageMaker.startpage}"	end="${pageMaker.endpage}">
			<li
				<c:out value="${pageMaker.cri.page == idx ? 'class =active' : ''}"/>>
				<a href="list${pageMaker.makeSearch(idx) }">${idx}</a>
			</li>
		</c:forEach>

		<c:if test="${pageMaker.next && pageMaker.endpage >0}">
			<li><a href="list${pageMaker.makeSearch(pageMaker.endpage +1)}">&raquo;</a></li> 
			<%-- <li><a href="list?page=${pageMaker.endpage +1}">&raquo;</a></li> --%>
		</c:if>
	</ul>

</div>
<%@ include file="../include/footer.jsp"%>