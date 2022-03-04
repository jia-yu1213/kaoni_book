<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cri" value="${pageMaker.cri }" />

<head></head>

<title>도서목록</title>

<body>	
<input type="hidden" id="loginUser" value="${loginUser}" >
	<c:if test="${from eq 'regist'}" >
		<script>
    	alert("도서 등록이 성공했습니다.");
    	window.opener.location.reload();	
    	window.close();
    	</script>
    </c:if>
	 <!-- Main content -->
	<section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>도서 목록</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="list.do">
				        	<i class="fa fa-dashboard"></i>도서
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	목록
			        </li>		        
	    	  </ol>
	  			</div>
	  		</div>
	  	</div>
	</section>
	 
	 <!-- Main content -->
    <section class="content">		
		<div class="card">
			<div class="card-header with-border">
				<button type="button" class="btn btn-primary" id="registBtn" onclick="OpenWindow('registForm.do','공지등록',800,700);">공지등록</button>				
				<div id="keyword" class="card-tools" style="width:540px;">
					<div class="input-group row">
						<select style="display:none"class="form-control col-md-3" name="perPageNum" id="perPageNum"
					  		onchange="list_go();">
					  		<option value="10" >정렬개수</option>
					  		<option value="20" ${cri.perPageNum == 20 ? 'selected':''}>20개씩</option>
					  		<option value="50" ${cri.perPageNum == 50 ? 'selected':''}>50개씩</option>
					  		<option value="100" ${cri.perPageNum == 100 ? 'selected':''}>100개씩</option>
					  		
					  	</select>						
						<select class="form-control col-md-4" name="searchType" id="searchType">
							<option value="tcw"  ${cri.searchType eq 'tcw' ? 'selected':'' }>전 체</option>
							<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>제 목</option>
							<option value="w" ${cri.searchType eq 'w' ? 'selected':'' }>저 자</option>
							<option value="c" ${cri.searchType eq 'c' ? 'selected':'' }>출판사</option>
						</select>					
						<input  class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${param.keyword }"/>
						<span class="input-group-append">
							<button class="btn btn-primary" type="button" onclick="list_go(1);" 
							data-card-widget="search">
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span>
					</div>
				</div>						
			</div>
			<div class="card-body">
				<table class="table table-bordered text-center" >					
					<tr style="font-size:0.95em;">
						<th style="width:8%;">번 호</th>
						<th style="width:48%;">제 목</th>
						<th style="width:15%;">저 자</th>
						<th>대여종료일</th>
						<th style="width:15%;">대여하기</th>
					</tr>				
					<c:if test="${empty bookList }" >
						<tr>
							<td colspan="5">
								<strong>해당 내용이 없습니다.</strong>
							</td>
						</tr>
					</c:if>				
					<c:forEach items="${bookList }" var="book">
						<tr style='font-size:0.85em;cursor:pointer;' onclick="OpenWindow('detail.do?book_no=${book.book_no }&from=list','상세보기',800,700);">
							<td style='vertical-align:middle'>${book.rownum }</td>
							<td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden; 
												white-space: nowrap; text-overflow: ellipsis;vertical-align:middle">
							${book.title }
							</td>			
							<td style='vertical-align:middle'>
							${book.writer }
							</td>
							<td style='vertical-align:middle'></td>
							<td style='vertical-align:middle' onclick="event.stopPropagation()">
							<c:choose>
								<c:when test="${book.book_status eq 0}">
									<button type="button" class="btn-sm btn-block btn-primary" onclick="rentBook('${book.book_no }');">대여하기</button>
								</c:when>
								<c:when test="${book.book_status eq 1}">
									<button type="button" class="btn-sm btn-block btn-secondary">대여불가</button>
								</c:when>
								
							</c:choose>
							
							</td>		
						</tr>
					</c:forEach>
				</table>				
			</div>
			<div class="card-footer">
				<%@ include file="/WEB-INF/views/common/pagination.jsp" %>
			</div>
		
		</div>
		
    </section>
    <!-- /.content -->
<script>
	function rentBook(book_no){
		var login = $('#loginUser').val();
		//로그인체크
		if (!login) {
			alert("로그인해")
		}else{
			//비동기 책 대여 중인지, 연체중인지 확인 
			//맵으로 상태를 보내서 대여 없는거, 대여중인거, 연체중인거, 연제기간인거
			$.ajax({
				url:"<%=request.getContextPath()%>/rent/checkRent",
				type:"post",
				success : function(dataMap){
					var status = dataMap.status;
					var data = dataMap.data;
					if (status=="overdueRent") {
						alert("연체가 되었습니다."+data);
					}else if (status=="overdueDate") {
						
						 alert("overdueDate"+data.split(" ")[0]);
					}else if (status=="nowRent") {
						alert("nowRent"+data);
						//대여가능
					}
						
					
						
				},
				error : function(){
						
				}
			});		
		}
	

	
	
		
	}
    
   </script> 
    
</body>





