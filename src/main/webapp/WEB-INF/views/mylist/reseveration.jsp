<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cri" value="${pageMaker.cri }" />

<head></head>

<title>도서목록</title>

						<div class="card-body">
							<table class="table table-bordered text-center" >					
								<tr style="font-size:0.95em;">
									<th style="width:6%;">번 호</th>
									<th style="width:30%;">제 목</th>
									<th style="width:10%;">저 자</th>
									<th>예약일자</th>
									<th style="width:15%;">대여하기</th>
								</tr>				
								<c:if test="${empty resList }" >
									<tr>
										<td colspan="5">
											<strong>해당 내용이 없습니다.</strong>
										</td>
									</tr>
								</c:if>				
								<c:forEach items="${resList }" var="res">
									<tr style='font-size:0.85em;cursor:pointer;' onclick="OpenWindow('<%=request.getContextPath() %>/rent/detail.do?rent_no=${rent.rent_no }','상세보기',802,730);">
										<td style='vertical-align:middle'>${res.rownum }</td>
										<td id="boardTitle" style="vertical-align:middle; max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;vertical-align:middle">${res.title }</td>			
										<td style='vertical-align:middle'>${res.writer }</td>
										<td style='vertical-align:middle'>
											<fmt:formatDate value="${res.res_date }" pattern="yyyy-MM-dd" var="res_date" />
											${res_date }
										</td>		
										<td style='vertical-align:middle' onclick="event.stopPropagation()">
											<c:if test="${real_end eq null }">
												<button type="button" class="btn-sm btn-block btn-primary" onclick="returnBook('${rent.rent_no }');">반납하기</button>
											
											</c:if> 
											<c:if test="${real_end ne null }">
												<button type="button" class="btn-sm btn-block btn-secondary">반납완료</button>
											
											</c:if>
										
										</td>	
										
									</tr>
								</c:forEach>
							</table>				
						</div>
		

	
	<script>
	function returnBook(rent_no){
		location.href="returnBookWait?rent_no="+rent_no;
		
	}
	</script>    
</body>