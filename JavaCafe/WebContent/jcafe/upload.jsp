<!-- 210114 / 파일 첨부 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jcafe/upload.jsp</title>
</head>
<body>

	<!-- action = 서블릿 호출 담당(FileUpload) -->
	<form method="post" enctype="multipart/form-data" action="../FileUpload">
		<input type="file" name="attach1" />
		<input type="submit" value="파일업로드" />
	</form>

</body>
</html>