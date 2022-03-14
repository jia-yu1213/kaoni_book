<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="cri" value="${pageMaker.cri }" />

<head></head>

<title>도서목록</title>

<body>
	<input type="hidden" id="loginUser" value="${loginUser}">

	<div class="card card-primary card-outline card-outline-tabs">
		<div class="card-header p-0 border-bottom-0">
			<ul class="nav nav-tabs" id="custom-tabs-four-tab" role="tablist">
				<li class="nav-item"><a class="nav-link active"
					id="custom-tabs-four-home-tab" data-toggle="pill"
					href="#custom-tabs-four-home" role="tab"
					aria-controls="custom-tabs-four-home" aria-selected="true">대여내역</a>
				</li>
				<li class="nav-item"><a class="nav-link"
					id="custom-tabs-four-profile-tab" data-toggle="pill"
					href="#custom-tabs-four-profile" role="tab"
					aria-controls="custom-tabs-four-profile" aria-selected="false">예약내역</a>
				</li>
			</ul>
		</div>
		
		
		<div class="card-body">
			<div class="tab-content" id="custom-tabs-four-tabContent">
				<div class="tab-pane fade active show" id="custom-tabs-four-home" role="tabpanel" aria-labelledby="custom-tabs-four-home-tab">
					
					
					<div class="card-body">
							<table class="table table-bordered text-center" >					
								<tr style="font-size:0.95em;">
									<th style="width:6%;">번 호</th>
									<th style="width:8%;">상 태</th>
									<th style="width:30%;">제 목</th>
									<th style="width:10%;">저 자</th>
									<th>대여시작일</th>
									<th>대여종료일</th>
									<th>반납일</th>
									<th style="width:15%;">반납하기</th>
								</tr>				
								<c:if test="${empty rentList }" >
									<tr>
										<td colspan="5">
											<strong>해당 내용이 없습니다.</strong>
										</td>
									</tr>
								</c:if>				
								<c:forEach items="${rentList }" var="rent">
									<tr style='font-size:0.85em;cursor:pointer;' onclick="OpenWindow('<%=request.getContextPath() %>/book/detail.do?book_no=${rent.book_no }','상세보기',800,700);">
										<td style='vertical-align:middle'>${rent.rownum }</td>
										<td style='vertical-align:middle'>
<%-- 											<c:choose> --%>
<%-- 												<c:when test="${empty real_end}">대여중</c:when> --%>
<%-- 												<c:otherwise>반납완료</c:otherwise> --%>
<%-- 											</c:choose> --%>
											<c:if test="${real_end ne null }">반납완료</c:if>
										</td>
										<td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden; 
															white-space: nowrap; text-overflow: ellipsis;vertical-align:middle">
										${rent.title }
										</td>			
										<td style='vertical-align:middle'>
										${rent.writer }
										</td>
										<td style='vertical-align:middle'>
											<fmt:formatDate value="${rent.rent_start }" pattern="yyyy-MM-dd" var="rent_start" />
											${rent_start }
										</td>
										<td style='vertical-align:middle'>
											<fmt:formatDate value="${rent.rent_end }" pattern="yyyy-MM-dd" var="rent_end" />
											${rent_end }
										</td>
										<td style='vertical-align:middle'>
											<fmt:formatDate value="${rent.real_end }" pattern="yyyy-MM-dd" var="real_end" />
											${real_end }
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
					
					
				</div>
				
				<div class="tab-pane fade" id="custom-tabs-four-home" role="tabpanel" aria-labelledby="custom-tabs-four-profile-tab">
				
						
				
				
				</div>
			</div>
		</div>

	</div>

	<script>
	function returnBook(rent_no){
		location.href="returnBookWait?rent_no="+rent_no;
		
	}
	</script>
</body>