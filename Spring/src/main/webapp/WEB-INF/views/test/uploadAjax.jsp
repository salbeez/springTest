<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>Insert title here</title>
</head>
<style>
.fileDrop {
	width: 100%;
	height: 200px;
	border: 1px dotted blue;
}

small {
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
</style>

<script type="text/javascript">
$(document).on("dragenter dragover",".fileDrop",function(event){
	event.preventDefault();
});

$(document).on("drop",".fileDrop",function(event){
	event.preventDefault();
	var files = event.originalEvent.dataTransfer.files;
	var file = files[0];
	
	var formData = new FormData();
	formData.append("file", file);
	
	$.ajax({
		type : "post",
		url : "/uploadAjax",
		data : formData,
		processData : false,
		contentType : false,
		success : function(data){
			var str='';
			
			console.log(data);
			console.log(checkImageType(data));
			
			if(checkImageType(data)){
				str = "<div> <a href='displayFile?fileName="+getImageLink(data)+"'>"
						+"<img src='displayFile?fileName="+data+" '/></a>"
						+"<small data-src="+data+">X</small></div>";
			}else{
				str = "<div>"
						+"<a href='displayFile?fileName="+data+"'>"+getOriginalName(data)+"</a>"
						+"<small data-src="+data+">X</small></div>";
			}
			console.log(str+" data : "+data);
			$(".uploadedList").append(str);
		}
	});
	
	console.log(file);
});
function checkImageType(fileName){
	var pattern = /jpg|gif|png|jpeg/i;
	return fileName.match(pattern);
}

function getOriginalName(fileName){
	if(checkImageType(fileName)){
		return;
	}
	var  idx = fileName.indexOf("_")+1;
	return fileName.substr(idx);
}
function getImageLink(fileName){
	if(!checkImageType(fileName)){
		return;
	}
	var front  = fileName.substr(0,12);
	var end = fileName.substr(14);
	return front + end;
}

$(document).on("click",".uploadedList small",function(event){
	var that = $(this);
	
	$.ajax({
		url : "deleteFile",
		type : 'POST',
		data : {fileName:$(this).attr("data-src")},
		success : function(result){
			if(result == 'delete'){
				alert('삭제');
				that.parent("div").remove();
			}
		}
	});
});
</script>

<body>

	<h3>Ajax File Upload</h3>
	<div class="fileDrop"></div>

	<div class="uploadedList"></div>


</body>
</html>