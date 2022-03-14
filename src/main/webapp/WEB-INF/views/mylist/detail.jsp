<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>도서상세보기</title>

<body>
<style>

.container{
	width: 534px;
	margin: 0 auto;
	padding:0px;
}

ul.tabs{
	margin: 0px;
	padding: 0px;
	list-style: none;
}
ul.tabs li{
	background: none;
	color: #222;
	display: inline-block;
	padding: 10px 15px;
	cursor: pointer;
}

ul.tabs li.current{
	background: #ededed;
	color: #222;
}

.tab-content{
	display: none;
	background: #ededed;
	padding: 15px;
	height : 240px;
	overflow-y : scroll;
}

.tab-content.current{
	display: inherit;
}

.tooltip{
	font-size : 20px;
}
</style>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" >
  
    <!-- Main content -->
    <section class="content container-fluid" style="background-color: ghostwhite;">       
		<div class="row justify-content-center" > 
			<div class="col-md-9">
			
	        	<div class="card-body" >
					<div class="row">
						<div class="col-sm-4">
							<div class="form-inline form-group" style="margin-top:15px">
								<label style="color: grey;font-weight: 400;">도&nbsp;서&nbsp;번&nbsp;호 [ ${rent.book_no } ]</label>
                 			</div>
							<input type="hidden" name="picture" id="picture"/>
							<div class="input-group" >
								<div class="mailbox-attachments clearfix" style="text-align: center;">
									<div class="has-img" id="pictureView" data-id="${rent.book_no}"style="border: 1px solid green; height:240px;width: 160px; margin: 0 auto;margin-top:15px"></div>
									
								</div>
							</div>
						</div>
						<div class="col-sm-8" style="padding-left:0px">
							<div class="form-inline form-group" >
	                   			<h1 data-toggle="tooltip" title="${rent.title }"style="width : 389px;overflow: hidden;white-space: nowrap; text-overflow: ellipsis;">${book.title }</h1>
                 			</div>
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="ori_title" class="col-sm-3">원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제</label>
	                   			<input name="ori_title" id="ori_title"type="text" class="form-control col-sm-9" style="width: 80px;" value="${rent.ori_title }" readonly>
                 			</div>
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="writer" class="col-sm-3">저&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;자</label>
	                   			<input name="writer"id="writer" type="text" class="form-control col-sm-9" style="width: 80px;" value="${rent.writer }" readonly>
                 			</div>
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="translator" class="col-sm-3">번&nbsp;&nbsp;역&nbsp;&nbsp;자</label>
								
	                   			<input name="translator"id="translator" type="text" class="form-control col-sm-9" style="width: 80px;" value="${rent.translator }" readonly>
                 			</div>
                 		
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="publisher" class="col-sm-3">출&nbsp;&nbsp;판&nbsp;&nbsp;사</label>
	                   			<input name="publisher" id="publisher"type="text" class="form-control col-sm-9" style="width: 80px;" value="${rent.publisher }" readonly>
                 			</div>
                 	
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="pdate" class="col-sm-3">출&nbsp;&nbsp;판&nbsp;&nbsp;일</label>
								
								<input type="text" class="form-control col-sm-9" style="width: 80px;"  id="pdate" readonly 
								value="<fmt:formatDate value="${rent.publishing_date}" pattern="yyyy-MM-dd" />" />
						
<%-- 		                   			<input name="pdate"id="pdate" type="text" class="form-control col-sm-9" style="width: 80px;" value="<fmt:formatDate value="${book.publishing_date} " pattern="yyyy-MM-dd"/>" readonly> --%>
                 			</div>
                 			<div class="form-inline form-group" style="margin: 5px;">
								<label for="cate_no" class="col-sm-3">분&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;류</label>
								<input name="cate_no"id="cate_no" type="text" class="form-control col-sm-9" style="width: 80px;" value="${rent.cate_name }"readonly>
	                   			
                 			</div>
                 	
						</div>
					
					</div>
					<hr>
					<div class="container">
						<ul class="tabs">
							<li class="tab-link current" data-tab="tab-1">책 소개</li>
							<li class="tab-link" data-tab="tab-2">책 목차</li>
							<li class="tab-link" data-tab="tab-3">작가 소개</li>
						</ul>
						<div id="tab-1" class="tab-content current">
							<div id="book_intro" >${rent.book_intro }</div>
						</div>
						<div id="tab-2" class="tab-content">
							<div id="book_index" >${rent.book_index}</div>
						</div>
						<div id="tab-3" class="tab-content">
							<div id="writer_intro" >${rent.writer_intro}</div>
						</div>
					</div>
					<br>
					<input type="hidden" id="loginUser" value="${loginUser}" >
					<div class="row" style="margin : auto;">
						<c:choose>
							<c:when test="${rent.real_end eq null}">
								<button type="button" style="margin : auto;" class="btn btn-primary" onclick="returnBook('${rent.book_no }');">반납하기</button>
							</c:when>
							<c:otherwise>
								<button type="button"  style="margin : auto;" class="btn btn-secondary">반납완료</button>
							</c:otherwise>
							
						</c:choose>
						<button type="button" class="btn btn-secondary" style="margin : auto;"onclick="window.close()">닫기</button>
					
      					
					</div>
                 </div>	
		 
			</div>
		</div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
<form role="form">
	<input type="hidden" name="rent_no" value="${rent.rent_no }" />
</form>

<script>

function returnBook(book_no){
		$.ajax({
			url:"<%=request.getContextPath()%>/rent/modify",
			type:"post",
			success : function(){
				
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
					
						location = "<%=request.getContextPath()%>/rent/registRent.do?rent_no="+rent_no;
					}
				}
					
			},
			error : function(){
				alert("반납ajax 에러");		
			}
		});		
	}
	



</script>
 

 
 
</body>






