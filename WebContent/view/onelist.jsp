<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

tr {
	text-align: center;
	padding: 4px 10px;
	background-color: #F6F6F6;
}

th {
	width: 120px;
	text-align: center;
	padding: 4px 10px;
	background-color: #B2CCFF;
}

h2 {
	text-align: center;
}

table {
	margin: 0 auto;
}

</style>

<script type="text/javascript">

	function list_go(f) {
		f.action = "/MyController?cmd=list";
		f.submit();
	}

	function reply_go(f) {
		f.action = "/MyController?cmd=re_write";
		f.submit();
	}

	function update_go(f) {
		f.action = "/MyController?cmd=update";
		f.submit();
	}

	function delete_go(f) {
		f.action = "/MyController?cmd=delete";
		f.submit();
	}
	
</script>

</head>

<body>

	<h2>Board 상세보기</h2>

	<form method="post">
		<table style="width: 800px;">
			<tbody>
				<tr>
					<th bgcolor="#B2EBF4">작성자</th>
					<td>${vo.writer}</td>
				</tr>

				<tr>
					<th bgcolor="#B2EBF4">제목</th>
					<td>${vo.title}</td>
				</tr>
				<tr>
					<th bgcolor="#B2EBF4">작성일</th>
					<td>${vo.regdate.substring(0,10)}</td>
				</tr>
				<tr>
					<th bgcolor="#B2EBF4">첨부파일</th>
					<c:choose>
						<c:when test="${empty vo.file_name}">
							<td><b> 첨부파일 없음 </b></td>
						</c:when>
						<c:otherwise>
							<td>
								<img src="upload/${vo.file_name}" style="width: 100px;">
								<a href="view/download.jsp?path=upload&file_name=${vo.file_name}">${vo.file_name}</a>
							</td>
						</c:otherwise>
					</c:choose>
				</tr>

				<tr>
					<th bgcolor="#B2EBF4">내용</th>
					<td><script
							src="https://cdn.ckeditor.com/4.14.1/standard/ckeditor.js"></script>
						<textarea name="content" readonly><pre>${vo.content}</pre></textarea>
						<script>
							CKEDITOR.replace('content');
						</script></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<input type="button" value="목록" onclick="list_go(this.form)">
						<input type="button" value="답글" onclick="reply_go(this.form)">
						<input type="button" value="수정" onclick="update_go(this.form)">
						<input type="button" value="삭제" onclick="delete_go(this.form)">
						<input type="hidden" value="${cPage}" name="cPage"></td>
				</tr>
			</tfoot>
		</table>
	</form>
</body>
</html>