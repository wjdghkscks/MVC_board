<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		text-align: center;
		padding: 6px 10px;
		background-color: #AACCFF;
		color: white;
	}
	
</style>

<script type="text/javascript">
	function write_go() {
		location.href="/MyController?cmd=write"
	}
</script>

</head>

<body>
	<h2 style="text-align: center;">Board 리스트</h2>
	
	<table style="width: 650px; margin: 0 auto;">
		
		<thead>
			<tr>
				<th width="10%">번호</th>
				<th width="40%">제목</th>
				<th width="17%">글쓴이</th>
				<th width="20%">날짜</th>
				<th width="13%">조회수</th>
			</tr>
		</thead>
		
		<tbody>
			<c:choose>
				<c:when test="${empty list}">
					<tr>
						<td colspan="5">자료가 존재하지 않습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="k" items="${list}" varStatus="vs">
						<tr>
							<td>${paging.totalRecord - ((paging.nowPage-1)*paging.numPerPage+vs.index)} </td>
							<td style="width: 40%; text-align: left; padding-left: 20px;">
								<c:forEach begin="1" end="${k.step}">
									&nbsp;[re]
								</c:forEach>
								<a href="/MyController?cmd=onelist&idx=${k.idx}&cPage=${paging.nowPage}">${k.title}</a>
							</td>
							<td>${k.writer}</td>
							<td>${k.regdate.substring(0,10)}</td>
							<td>${k.hit}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
		
		<tfoot>
			<tr>
				<td colspan="5">
				<!-- btn_prev -->
					<c:choose>
						<c:when test="${paging.beginBlock <= paging.pagePerBlock}">
							
						</c:when>
						<c:otherwise>
							<a href="/MyController?cmd=list&cPage=${paging.beginBlock - paging.pagePerBlock}"><img src="../images/but_prev.gif"></a>
						</c:otherwise>
					</c:choose>
				<!-- page number -->
					<c:forEach begin="${paging.beginBlock}" end="${paging.endBlock}" step="1" var="k">
						<c:choose>
							<c:when test="${k == paging.nowPage}">
								<b>${k}</b>
							</c:when>
							<c:otherwise>
								<a href="/MyController?cmd=list&cPage=${k}">${k}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				<!-- btn_next -->
					<c:choose>
						<c:when test="${paging.endBlock >= paging.totalPage}">
							
						</c:when>
						<c:otherwise>
							<a href="/MyController?cmd=list&cPage=${paging.beginBlock + paging.pagePerBlock}"><img src="../images/but_next.gif"></a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tfoot>
	</table>
	
	<div style="text-align: right; margin: 10px 100px;">
		<button onclick="write_go()">글쓰기</button>
	</div>
	
	
</body>
</html>