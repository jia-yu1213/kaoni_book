<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cri" value="${pageMaker.cri }" />

<head>

</head>

<title>도서목록</title>

<body>	
	<input type="hidden" id="loginUser" value="${loginUser}" >

	<div class="content-wrapper" >
    	<section class="content container-fluid" style="background-color: ghostwhite;">
    		<div class="card-header">
		    	<div style="text-align: right;width: 100%;">
		        	<div style="display: inline-block;">
<!-- 						<button type="button" class="btn btn-primary" id="registBtn" onclick="regist_go();">등 록</button> -->
<!-- 						&nbsp;&nbsp;&nbsp;&nbsp; -->
						<button type="button" class="btn btn-warning" id="cancelBtn" onclick="CloseWindow();">취 소</button>
					</div> 
		        </div>
				<div style="text-align:center">
					<h1>반납 대기</h1>
				</div>
		    </div>       
			<div class="card-body">
				<table class="table table-bordered text-center" >					
					<tr style="font-size:0.95em;">
						<th style="width:8%;">번 호</th>
						<th style="width:10%;">분 류</th>
						<th style="width:40%;">제 목</th>
						<th style="width:13%;">대여종료일</th>
						<th>대여자</th>
						<th style="width:18%;">승인하기</th>
					</tr>				
					<c:if test="${empty bookList }" >
						<tr>
							<td colspan="5">
								<strong>해당 내용이 없습니다.</strong>
							</td>
						</tr>
					</c:if>				
					<c:forEach items="${bookList }" var="book">
						<tr style='font-size:0.85em;' >
							<td style='vertical-align:middle'>${book.rownum }</td>
							<td style='vertical-align:middle'>${book.cate_name }</td>
							<td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden; 
												white-space: nowrap; text-overflow: ellipsis;vertical-align:middle">
							${book.title }
							</td>			
							<td style='vertical-align:middle'>
							<fmt:formatDate value="${book.real_end }" pattern="yyyy-MM-dd" var="real_end" />
							${real_end }
							</td>
							<td style='vertical-align:middle'>
							${book.name }
							
							</td>
							<td style='vertical-align:middle' onclick="event.stopPropagation()">
								<div class="row" style="margin : auto">
									<button type="button" class="btn btn-primary" style="margin : auto" id="registBtn" onclick="return_go('${book.book_no}','${book.rent_no }');">승 인</button>
									<button type="button" class="btn btn-secondary" style="margin : auto" id="registBtn" onclick="cancle_go('${book.book_no}','${book.rent_no }')">반 려</button>
								
								</div>
							</td>		
						</tr>
					</c:forEach>
				</table>				
			</div>

    </section>
    <!-- /.content -->
  </div>
<script>
function return_go(book_no,rent_no){
	location = "returnAccept.do?book_no="+book_no+"&rent_no="+rent_no;
	
}
function cancle_go(book_no,rent_no){
	location = "returnCancle.do?book_no="+book_no+"&rent_no="+rent_no;
	
}


</script>
</body>





