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
				<li class="nav-item">
					<a class="nav-link" id="custom-tabs-four-home-tab" data-toggle="pill" href="#custom-tabs-four-home" role="tab" aria-controls="custom-tabs-four-home" aria-selected="false">대여내역</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" id="custom-tabs-four-profile-tab" data-toggle="pill" href="#custom-tabs-four-profile" role="tab" aria-controls="custom-tabs-four-profile" aria-selected="false">예약내역</a>
				</li>
<!-- 				<li class="nav-item"> -->
<!-- 					<a class="nav-link" id="custom-tabs-four-messages-tab" data-toggle="pill" href="#custom-tabs-four-messages" role="tab" aria-controls="custom-tabs-four-messages" aria-selected="false">Messages</a> -->
<!-- 				</li> -->
<!-- 				<li class="nav-item"> -->
<!-- 					<a class="nav-link active" id="custom-tabs-four-settings-tab" data-toggle="pill" href="#custom-tabs-four-settings" role="tab" aria-controls="custom-tabs-four-settings" aria-selected="true">Settings</a> -->
<!-- 				</li> -->
			</ul>
		</div>

		<div class="card-body">
			<div class="tab-content" id="custom-tabs-four-tabContent">
				<div class="tab-pane fade" id="custom-tabs-four-home" role="tabpanel" aria-labelledby="custom-tabs-four-home-tab">
<!-- 					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin malesuada lacus ullamcorper dui molestie, sit amet congue quam finibus. Etiam ultricies nunc non magna feugiat commodo. Etiam odio magna, mollis auctor felis vitae, ullamcorper ornare ligula. Proin pellentesque tincidunt nisi, vitae ullamcorper felis aliquam id. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Proin id orci eu lectus blandit suscipit. Phasellus porta, ante et varius ornare, sem enim sollicitudin eros, at commodo leo est vitae lacus. Etiam ut porta sem. Proin porttitor porta nisl, id tempor risus rhoncus quis. In in quam a nibh cursus pulvinar non consequat neque. Mauris lacus elit, condimentum ac condimentum at, semper vitae lectus. Cras lacinia erat eget sapien porta consectetur. -->
						
						
						<div class="card-body">
							<table class="table table-bordered text-center" >					
								<tr style="font-size:0.95em;">
									<th style="width:6%;">번 호</th>
<!-- 									<th style="width:6%;">num</th> -->
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
									<tr style='font-size:0.85em;cursor:pointer;' onclick="OpenWindow('<%=request.getContextPath() %>/rent/detail.do?rent_no=${rent.rent_no }','상세보기',802,730);">
										<td style='vertical-align:middle'>${rent.rownum }</td>
<%-- 										<td style='vertical-align:middle'>${rent.rent_no }</td> --%>
										<td style='vertical-align:middle'>
											<c:choose>
											
												<c:when test="${rent.rent_status eq 1 }">
													반납 반려
												</c:when>
												<c:when test="${rent.rent_status eq 3 }">
													반납 대기
												</c:when>
												<c:when test="${empty rent.real_end && rent.rent_status eq 0}">
													대여중
												</c:when>
												<c:when test="${rent.rent_status eq 2 }">
													반납 완료
												</c:when>
												
										
											</c:choose>
<%-- 											<c:if test="${real_end eq null}">대여중</c:if> --%>
<%-- 											<c:if test="${real_end ne null}">반납완료</c:if> --%>
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
												<c:if test="${rent.book_status ne 3 }">
											
													<button type="button" class="btn-sm btn-block btn-primary" onclick="location.href='returnBookWait?rent_no=${rent.rent_no}'">반납하기</button>
												</c:if>
												<c:if test="${rent.book_status eq 3 }">
													<button type="button" class="btn-sm btn-block btn-secondary">반납대기</button>
											
												</c:if>
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
				<div class="tab-pane fade" id="custom-tabs-four-profile" role="tabpanel" aria-labelledby="custom-tabs-four-profile-tab">
						<%@include file="./reseveration.jsp" %>
				</div>
				
				
			</div>
		</div>

</div>
	<script>
	function returnBookWait(rent_no){
		
		location.href="returnBookWait?rent_no="+rent_no;
		
	}
	</script>
</body>