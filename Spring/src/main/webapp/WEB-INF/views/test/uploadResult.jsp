<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">
	var result = ''${savadName};
	parent.addFilePath(result);
	alert("Result.jsp");
	$(function(){
		function addFilePath(msg){
			alert(msg);
			$("#form1")[0].reset();
		}
		
		/*
		  //방법1 - 하나만 리셋
			$('#myForm')[0].reset();
		  //방법2 - 문서에 있는 모든 form을 리셋
			$('form').each(function(){
				this.reset();
			});
  		*/
	});
</script>

<style>
iframe {
	width: 0px;
	height: 0px;
	border: 0px;
}
</style>

<body>

	<form action="uploadForm" id="form1" method="post"
		enctype="multipart/form-data" target="zeroFrame">
		<input type="file" name="file"> <input type="submit">
	</form>

	<iframe name="zeroFrame"></iframe>


</body>
</html>