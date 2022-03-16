<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="cri" value="${pageMaker.cri }" />

<head></head>

<title>도서목록</title>

<body>
<script>
function returnBookWait3(rent_no){
	var result = confirm("반납 하시겠습니까?");
	if (result) {
		location.href="returnBookWait?rent_no="+rent_no;
	}
	
}
</script>
<input type="hidden" id="loginUser" value="${loginUser}">
		
		<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-3">
					<h1>대여/예약 목록</h1>

				</div>
				<div class="col-sm-7" style="margin-bottom : 0px">
				</div>
				<div class="col-sm-2">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="list.do"> 
								<i class="fa fa-dashboard"></i>
									대여
							</a>
						</li>
						<li class="breadcrumb-item active">목록</li>
					</ol>
				</div>
			</div>
		</div>
	</section>		
	
	<section class="content">	
		<div class="card">	
			
			<div class="card-body">
				
					<div class="card card-primary card-outline card-outline-tabs">
						<div class="card-header p-0 border-bottom-0">
							<ul class="nav nav-tabs" id="custom-tabs-four-tab" role="tablist">
								<li class="nav-item">
									<a class="nav-link active" id="custom-tabs-four-home-tab" data-toggle="pill" href="#custom-tabs-four-home" role="tab" aria-controls="custom-tabs-four-home" aria-selected="true">대여내역</a>
								</li>
								<li class="nav-item">
									<a class="nav-link" id="custom-tabs-four-profile-tab" data-toggle="pill" href="#custom-tabs-four-profile" role="tab" aria-controls="custom-tabs-four-profile" aria-selected="false">예약내역</a>
								</li>
							</ul>
						</div>
						<div class="card-body">
							<div class="tab-content" id="custom-tabs-four-tabContent">
							
								<div class="tab-pane fade show active" id="custom-tabs-four-home" role="tabpanel" aria-labelledby="custom-tabs-four-home-tab">
									
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
												<tr style='font-size:0.85em;cursor:pointer;' onclick="OpenWindow('<%=request.getContextPath() %>/rent/detail.do?rent_no=${rent.rent_no }','상세보기',804,753);">
													<td style='vertical-align:middle'>${rent.rownum }</td>
													<td style='vertical-align:middle'>
														<c:choose>
														
															<c:when test="${rent.rent_status eq 1 }">
																<span style="color : red">
																	반납 반려
																</span>
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
													</td>
													<td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;vertical-align:middle">
														${rent.title }
													</td>			
													<td style='vertical-align:middle'>${rent.writer }</td>
													<td style='vertical-align:middle'>
														<fmt:formatDate value="${rent.rent_start }" pattern="yyyy-MM-dd" var="rent_start" />${rent_start }
													</td>
													<td style='vertical-align:middle'>
														<fmt:formatDate value="${rent.rent_end }" pattern="yyyy-MM-dd" var="rent_end" />${rent_end }
													</td>
													<td style='vertical-align:middle'>
														<fmt:formatDate value="${rent.real_end }" pattern="yyyy-MM-dd" var="real_end" />${real_end }
													</td>		
			
													<td style='vertical-align:middle' onclick="event.stopPropagation()">
														<c:choose>
															<c:when test="${rent.rent_status eq 0 ||rent.rent_status eq 1}">
																<button type="button" class="btn-sm btn-block btn-primary" onclick="returnBookWait3('${rent.rent_no}')">반납하기</button>
															</c:when>
															<c:when test="${rent.rent_status eq 2 }">
																<button type="button" class="btn-sm btn-block btn-secondary">반납완료</button>
															</c:when>
															<c:when test="${rent.rent_status eq 3 }">
																<button type="button" class="btn-sm btn-block btn-secondary">반납대기</button>
															</c:when>
														</c:choose>
													</td>		
												</tr>
											</c:forEach>
										</table>
											
								</div>
								
								<div class="tab-pane fade" id="custom-tabs-four-profile" role="tabpanel" aria-labelledby="custom-tabs-four-profile-tab">
									<%@include file="./reseveration.jsp" %>
								</div>
								
								
							</div>
						</div>
					
					</div>
						
			</div>
			
		</div>
	</section>	


</body>