<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cri" value="${pageMaker.cri }" />

<head></head>

<title>도서목록</title>

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
									<tr style='font-size:0.85em;cursor:pointer;' onclick="OpenWindow('<%=request.getContextPath() %>/rent/detail.do?book_no=${res.book_no }','상세보기',802,730);">
										<td style='vertical-align:middle'>${res.rownum }</td>
										<td id="boardTitle" style="vertical-align:middle; max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;vertical-align:middle">${res.title }</td>			
										<td style='vertical-align:middle'>${res.writer }</td>
										<td style='vertical-align:middle' style="width:50px;">
											<fmt:formatDate value="${res.res_date }" pattern="yyyy-MM-dd" var="res_date" />
											${res_date }
										</td>		
										<td style='padding-top: 12px;' onclick="event.stopPropagation()">
											<c:choose>
												<c:when test="${res.book_status eq '0' }">
													<button type="button" class="btn-sm btn-block btn-primary" onclick="rentBook('${res.book_no }'), removeSuccessRes('${res.book_no }','${loginUser.id }');" style="width:76px;; display: inline-block;">대여</button>
													<button type="button" class="btn-sm btn-block btn-secondary" onclick="removeRes('${res.book_no }','${loginUser.id }');" style="width:76px;; display: inline-block;">취소</button>
												</c:when>
												<c:otherwise>
													<button type="button" class="btn-sm btn-block btn-secondary" onclick="removeRes('${res.book_no }','${loginUser.id }');" style="padding-top:12px;">에약취소</button>
												</c:otherwise>
											</c:choose>
										
										</td>	
										
									</tr>
								</c:forEach>
							</table>		
							
		

	
<script>
	function returnBook(rent_no){
		location.href="returnBookWait?rent_no="+rent_no;
		
	}
	
	function rentBook(book_no){
		var login = $('#loginUser').val();
		
		
			$.ajax({
				url:"<%=request.getContextPath()%>/rent/checkRent",
				type:"post",
				success : function(dataMap){
					
					var status = dataMap.status;
					var data = dataMap.data;
				
					var answer;
					if (status=="overdueRent") {
						answer = confirm(data+"권이 연체중입니다. \n연체중에는 이용이 불가합니다. \n반납하시겠습니까?");
						if(answer){
							location = "<%=request.getContextPath()%>/mylist/list.do";
						}
					}else if (status=="overdueDate") {
						 alert("연체 기간입니다. \n"+data.split(" ")[0]+"까지 이용이 불가합니다.");
					}else if (status=="nowRent") {
						//대여가능
						answer = confirm(data+"권이 대여중입니다. \n대여하시겠습니까?");
						if(answer){
						
							location = "<%=request.getContextPath()%>/rent/registRent.do?book_no="+book_no;
						}
					}
					window.location.reload();
				},
				error : function(){
					alert("대여ajax 에러");		
				}
			});		
		
	}
	
	
	function removeSuccessRes(book_no, id){
		
		$.ajax({
			url:"<%=request.getContextPath()%>/rent/delSuccesRes",
			data : {"book_no":book_no, "id":id},
			success: function(data){
				window.location.reload();
			},
			error : function(error){
				console.log(error);
				alert("에러 발생");
			}
			});
	}

function removeRes(book_no, id){
	
	
	var res_status = '0'; 
	
	
	if (confirm("예약을 취소하시겠습니까?") == true){

		$.ajax({
			url:"<%=request.getContextPath()%>/rent/delRes",
			data : {"book_no":book_no, "id":id, "res_status": res_status},
			success: function(data){
					alert("예약이 취소되었습니다.")
					window.location.reload();
				
			},
			error : function(error){
				console.log(error);
				alert("에러 발생");
			}
		});
		
	}else{
		window.location.reload();
	}
	
}


	
</script>  

</body>