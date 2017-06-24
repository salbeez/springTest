<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.10/handlebars.js"></script>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script id="template" type="text/x-handlebars-template">
<li>
	<span class="mailbox-attachment-icon has-img">
		<img src="{{imgsrc}}" alt="Attachment"></img>
	</span>
	<div class="mailbox-attachment-info">
		<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
		<a href="{{fullName}}" class="btn btn-default btn-sx pull-right delbtn" name=removeA>
			<i class="fa fa-fw fa-remove"></i>
		</a>
	</div>
</li>
</script>
<style>
.fileDrop {
	width: 80%;
	height: 100px;
	border: 1px dotted gray;
	background-color: lightslategrey;
	margin: auto;
}
</style>
<script>
	var template = Handlebars.compile($("#template").html());

	$(document).on("dragenter dragover", ".fileDrop", function(e) {
		e.preventDefault();
	});
	$(document).on("drop", ".fileDrop", function(e) {
		e.preventDefault();

		var files = e.originalEvent.dataTransfer.files;
		var file = files[0];
		var formData = new FormData();
		formData.append("file", file);

		$.ajax({
			url : '/uploadAjax',
			data : formData,
			processData : false,
			contentType : false,
			type : 'post',
			success : function(data) {
				var fileInfo = getFileInfo(data);
				var html = template(fileInfo);
				$(".uploadedList").append(html);
			}
		});
	});

	$(document).on("click", "#registerForm", function(event) {
		event.preventDefault();
		var that = $(this);
		var str = "";
		$(".uploadedList .delbtn").each(function(index) {
			str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "'>";
		});
		that.append(str);
		//that.get(0).submit();
		$('form[role=form]').submit();
	});
	
 	$(document).on("click", "a[name=removeA]", function(event) {
		event.preventDefault();
		var that = $(this);
		$.ajax({
			url : "/deleteFile",
			type : 'POST',
			data : {fileName:$(this).attr("href")},
			success : function(result){
				if(result == 'delete'){
					that.parentsUntil("ul").remove();
				}
			}
		});
	}); 
</script>
<%@ include file="../include/header.jsp"%>
<form role="form" method="post">
	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">title</label> <input type="text"
				name="title" class="form-control" placeholder="Enter Title">
		</div>

		<div class="form-group">
			<label for="exampleInputPassword1">Content</label><br>
			<textarea name="content" rows="3" style="width: 100%;" placeholder=""></textarea>
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label> <input type="text"
				name="writer" class="form-control" placeholder="Enter Writer">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">File DROP Here</label>
			<div class="fileDrop"></div>
		</div>
	</div>
	<!-- /.box-body -->

	<div class="box-footer">
		<div>
			<hr>
		</div>
		<ul class="mailbox-attachments clearfix uploadedList">
		</ul>

		<button type="button" class="btn btn-primary" id="registerForm">Submit</button>
	</div>
</form>
<%@ include file="../include/footer.jsp"%>