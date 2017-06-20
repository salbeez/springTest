<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.10/handlebars.js"></script>
<!-- <script type="text/javascript" src="/resources/js/handlebars-v4.0.10.js"></script> -->
<script>
	var formObj;

	$(function() {
		formObj = $("form[role='form']");
		console.log(formObj);
	});

	$(document).on("click", ".btn-warnin", function() {
		formObj.attr("action", "/sboard/modifyPage");
		formObj.attr("method", "get");
		formObj.submit();
	});

	$(document).on("click", ".btn-danger", function() {
		formObj.attr("method","get");
		formObj.attr("action", "/sboard/remove");
		formObj.submit();
	});

	$(document).on("click", ".btn-primary", function() {
		formObj.attr("method","get");
		formObj.attr("action", "/sboard/list");
		formObj.submit();
	});
	

</script>
<script id="template" type="text/x-handlebars-template">
{{#each .}}
<li class="replyLi" data-rno={{rno}}>
	<i class="fa fa-comments bg-blue"></i>
		<div class="timeline-item" >
			<span class="time">
				<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
			</span>
			<h3 class="timeline-header"><strong>{{rno}}</strong> -{{replyer}}</h3>
			<div class="timeline-body">{{replytext}} </div>
			<div class="timeline-footer">
				<a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyModal">Modify</a>
			</div>
		</div>			
</li>
{{/each}}
</script>

<script>
Handlebars.registerHelper("prettifyDate",function(timeValue){
	var dateObj = new Date(timeValue);
	var year = dateObj.getFullYear();
	var month= dateObj.getMonth();
	var date = dateObj.getDate();
	return year+"/"+month+"/"+date;
});

var printData = function(replyArr, target, templateObject) {
	var template = Handlebars.compile(templateObject.html());
	var html = template(replyArr);
	$(".replyLi").remove();
	target.after(html);
}


var printPaging = function(pageMaker,target){
	var str="";
	if(pageMaker.prev){
		str += "<li><a href='"+(pageMaker.startpage-1)+"'> << </a><li>";
	}
	for(var i=pageMaker.startpage,len=pageMaker.endpage; i<=len; i++){
		var strClass = pageMaker.cri.page ==i?'class=active':'';
		str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
	}
	if(pageMaker.next){
		str += "<li><a href='"+(pageMaker.endpage+1)+"'> >> </a><li>";
	}
	target.html(str);
}

var bno = ${guestVO.bno };
var replyPage =1;

function getPage(pageInfo){
	$.getJSON(pageInfo,function(data){
		printData(data.list,$("#repliesDiv"), $("#template"));
		printPaging(data.pageMaker, $(".pagination"));
	});
}
$(document).on("click", "#repliesDiv", function() {//list show
	if($(".timeline li").size() >1){
		return;
	}
	getPage("/replies/"+bno+"/1");
});

$(document).on("click",".pagination li a", function(e){ // paging btn click
	e.preventDefault();
	replyPage = $(this).attr("href");
	getPage("/replies/"+bno+"/"+replyPage);
});

$(document).on("click", "#replyAddBtn", function() {// add reply
	var replyerObj = $("#newReplyWriter");
	var replytextObj = $("#newReplyText");
	
	var replyer = replyerObj.val();
	var replytext = replytextObj.val();
	$.ajax({
		type : 'post',
		url : '/replies',
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		data : JSON.stringify({
			bno : bno,
			replyer : replyer,
			replytext : replytext
		}),
		success : function(result) {
			console.log(result);
			if (result == 'SUCCESS') {
				alert('등록되었습니다');
				replyPage =1;
				getPage("/replies/"+bno+"/"+replyPage);
				replyerObj.val("");
				replytextObj.val("");
				alert('끝');
			}
		}
	});
	 
});

</script>

<%@ include file="../include/header.jsp"%>

<form role="form" method="post">
	<input type="hidden" name="bno" value="${guestVO.bno }"> <input
		type="hidden" name="page" value="${cri.page }"> <input
		type="hidden" name="perPageNum" value="${cri.perPageNum }"> <input
		type="hidden" name="searchType" value="${cri.searchType }"> <input
		type="hidden" name="keyword" value="${cri.keyword }">
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

<div class="row">
	<div class="col-md-12">
		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">ADD NEW REPLY</h3>
			</div>

			<div class="box-body">
				<label for="newReplyWriter">Writer</label> <input
					class="form-control" type="text" placeholder="USER ID"
					id="newReplyWriter"> <label for="newReplyText">ReplyText</label>
				<input class="form-control" type="text" placeholder="Reply Text"
					id="newReplyText">
			</div>

			<!-- /. box-body -->
			<div class="box-footer">
				<button type="submit" class="btn btn-primary" id="replyAddBtn">ADD
					REPLY</button>
			</div>
		</div>
	</div>
</div>

<!-- The Time line -->
<ul class="timeline">
	<li class="time-label" id="repliesDiv"><span class="bg-green">Replies
			List</span></li>
</ul>

<div class="text-center">
	<ul id="pagination" class="pagination pagination-sm no-margin"></ul>
</div>
<%@ include file="../include/footer.jsp"%>