<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#modDiv {
	width: 300px;
	height: 100px;
	background-color: gray;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	

	function getAllList() {
		$.getJSON("/replies/all/" + bno, function(data) {
			console.log(data.length);

			var str = '';
			$(data).each(function() {
				str += "<li data-rno='" + this.rno + "' class='replyLi'> "
					+ this.rno + " : " + this.replytext
					+ "<button>MOD</button></li>"
			});
			$("#replies").html(str);
		});
	}
	$(document).on("click", "#replies .replyLi button", function() { //에딧창 띄우기

		var reply = $(this).parent(); //<li> 태그를 지목

		var rno = reply.attr("data-rno");
		var replytext = reply.text();

		$(".modal-title").html(rno);
		$("#replytext").val(replytext);
		$("#modDiv").show("slow");
	});

	$(document).on("click", "#replyDelBtn", function() {
		var rno = $('.modal-title').html();
		var replytext = $("#replytext").val();

		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			success : function(result) {
				console.log("result : " + result);
				if (result == 'SUCCESS') {
					alert('삭제되었습니다');
					$('#modDiv').hide("slow");
					getAllList();
				}
			}
		});
	});

	$(document).on("click", "#replyModBtn", function() {
		var rno = $('.modal-title').html();
		var replytext = $("#replytext").val();

		$.ajax({
			type : 'put',
			url : '/replies/' + rno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PUT"
			},
			data : JSON.stringify({
				replytext : replytext
			}),
			success : function(result) {
				console.log("result : " + result);
				if (result == 'SUCCESS') {
					alert('수정 되었습니다');
					$('#modDiv').hide("slow");
					getAllList();
					getPageList(replyPage); //?
				}
			}
		});
	});

	$(document).on("click", "#replyAddBtn", function() {
		var replyer = $("#newReplyWriter").val();
		var replytext = $("#newReplyText").val();

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
				if (result == 'SUCCESS') {
					alert('등록되었습니다');
					getAllList();
				}
			}
		});
	});

	function getpageList(page) {
		$.getJSON("/replies/" + bno + "/" + page, function(data) {
			console.log(data.list.length);
			var str = '';

			$(data.list).each(function() {
				str += "<li data-rno='" + this.rno + "' class='replyLi'> "
				+ this.rno + " : " + this.replytext
				+ "<button>MOD</button></li>"
			});
			$("#replies").html(str);
			printPaging(data.pageMaker);
		});
	}

	function printPaging(pageMaker) {
		var str = '';
		console.log(pageMaker.startpage+" : "+ pageMaker.endpage+" , "+pageMaker.prev+" : "+pageMaker.next);
		if (pageMaker.prev) {
			str += "<li> <a href='" + (pageMaker.startpage - 1) + "'> << </a></li>";
		}

		for (var i = pageMaker.startpage, len = pageMaker.endpage; i <= len; i++) {
			var strClass = pageMaker.cri.page ==i?'class=active':'';
			str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
		}
		if (pageMaker.next) {
			str += "<li> <a href='" + (pageMaker.startpage + 1) + "'> >> </a></li>";
		}
		$('.pagination').html(str);
	}
	
	var replyPage = 1;
	$(document).on("click",".pagination li a", function(e){
		e.preventDefault();
		replyPage = $(this).attr("href");
		getpageList(replyPage);
	});
	
	var bno = 555033;
	getpageList(1);
</script>

<title>Insert title here</title>
</head>
<body>
	<h2>Ajax Test Page</h2>
	<div>
		<div>
			REPLYER <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			REPLY TEXT <input type="text" name="replytext" id="newReplyText">
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>

	<div id="modDiv" style="display: none;">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="replytext">
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>
			<button type="button" id="replyDelBtn">DELETE</button>
			<button type="button" id="closeBtn">Close</button>
		</div>
	</div>

	<ul id="replies"></ul>
	<ul class="pagination"></ul>
</body>
</html>