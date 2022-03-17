<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>도서상세보기</title>

<body>
<style>

.container{
	
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
	height : 289px;
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
<!--    	 <section class="content-header"  style="background-color: ghostwhite;"> -->
<!-- 	  	<div class="container-fluid"> -->
<!-- 	  		<div class="row md-2"> -->
<!-- 	  			<div class="col-sm-6"> -->
	  							
<!-- 	  			</div> -->
<!-- 	  			<div class="col-sm-6"> -->
<!-- 	  				<ol class="breadcrumb float-sm-right"> -->
			              
<!-- 	    	  </ol> -->
<!-- 	  			</div> -->
<!-- 	  		</div> -->
<!-- 	  	</div> -->
<!--   	</section> -->
    <!-- Main content -->
    <section class="content container-fluid" style="background-color: ghostwhite;">       
		<div class="row justify-content-center" > 
			<div class="col-md-10">
			
				<c:if test="${loginUser.authority == 0}">
					<div class="card-header">
			        	<div style="text-align: right;width: 100%;">
			        		<div style="display: inline-block;">
								<button type="button" class="btn" id="modifyBtn" style="background-color : #ededed" onclick="modify_go();">수 정</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-secondary"  id="deleteBtn" onclick="delete_go();">삭 제</button>
							</div> 
			        	</div>
	<!-- 					<div style="text-align:center"> -->
	<!-- 						<h1></h1> -->
	<!-- 					</div> -->
			        </div>
				</c:if>

	
		    	
	        	<div class="card-body" >
					<div class="row">
						<div class="col-sm-4">
							<div class="form-inline form-group" style="margin-top:15px">
								<label style="color: grey;font-weight: 400;">도&nbsp;서&nbsp;번&nbsp;호 [ ${book.book_no } ]</label>
                 			</div>
							<input type="hidden" name="picture" id="picture"/>
							<div class="input-group" >
								<div class="mailbox-attachments clearfix" style="text-align: center;">
									<div class="has-img" id="pictureView" data-id="${book.book_no}"style="border: 1px solid green; height:240px;width: 160px; margin: 0 auto;margin-top:15px"></div>
									
								</div>
							</div>
						</div>
						<div class="col-sm-8" style="padding-left:0px">
							<div class="form-inline form-group" >
	                   			<h1 data-toggle="tooltip" title="${book.title }"style="width : 389px;overflow: hidden;white-space: nowrap; text-overflow: ellipsis;">${book.title }</h1>
                 			</div>
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="ori_title" class="col-sm-3">원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제</label>
	                   			<input name="ori_title" id="ori_title"type="text" class="form-control col-sm-9" style="width: 80px;" value="${book.ori_title }" readonly>
                 			</div>
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="writer" class="col-sm-3">저&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;자</label>
	                   			<input name="writer"id="writer" type="text" class="form-control col-sm-9" style="width: 80px;" value="${book.writer }" readonly>
                 			</div>
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="translator" class="col-sm-3">번&nbsp;&nbsp;역&nbsp;&nbsp;자</label>
								
	                   			<input name="translator"id="translator" type="text" class="form-control col-sm-9" style="width: 80px;" value="${book.translator }" readonly>
                 			</div>
                 		
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="publisher" class="col-sm-3">출&nbsp;&nbsp;판&nbsp;&nbsp;사</label>
	                   			<input name="publisher" id="publisher"type="text" class="form-control col-sm-9" style="width: 80px;" value="${book.publisher }" readonly>
                 			</div>
                 	
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="pdate" class="col-sm-3">출&nbsp;&nbsp;판&nbsp;&nbsp;일</label>
								
								<input type="text" class="form-control col-sm-9" style="width: 80px;"  id="pdate" readonly 
								value="<fmt:formatDate value="${book.publishing_date}" pattern="yyyy-MM-dd" />" />
						
<%-- 		                   			<input name="pdate"id="pdate" type="text" class="form-control col-sm-9" style="width: 80px;" value="<fmt:formatDate value="${book.publishing_date} " pattern="yyyy-MM-dd"/>" readonly> --%>
                 			</div>
                 			<div class="form-inline form-group" style="margin: 5px;">
								<label for="cate_no" class="col-sm-3">분&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;류</label>
								<input name="cate_no"id="cate_no" type="text" class="form-control col-sm-9" style="width: 80px;" value="${book.cate_name }"readonly>
	                   			
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
							<div id="book_intro" >${book.book_intro }</div>
						</div>
						<div id="tab-2" class="tab-content">
							<div id="book_index" >${book.book_index}</div>
						</div>
						<div id="tab-3" class="tab-content">
							<div id="writer_intro" >${book.writer_intro}</div>
						</div>
					</div>
					<br>
					<input type="hidden" id="loginUser" value="${loginUser}" >
					<div class="row" style="margin : auto;">
<%-- 						<c:choose> --%>
<%-- 							<c:when test="${book.book_status eq 0}"> --%>
<%-- 								<button type="button" style="margin : auto;" class="btn btn-primary" onclick="rentBook('${book.book_no }');">대여하기</button> --%>
<%-- 							</c:when> --%>
<%-- 							<c:when test="${book.book_status eq 1}"> --%>
<!-- 								<button type="button"  style="margin : auto;" class="btn btn-secondary">대여불가</button> -->
<%-- 							</c:when> --%>
							
<%-- 						</c:choose> --%>

						<c:choose>
							<c:when test="${book.rent_able eq 0}">
								<button type="button" style="margin : auto;" class="btn btn-primary" onclick="rentBook2('${book.book_no }','${book.rent_able }');">대여하기</button>
							</c:when>

							<c:when test="${book.rent_able eq 1}">
								<button type="button" style="margin : auto;" class="btn btn-secondary"onclick="resBook('${book.book_no}','${loginUser.id}');">예약하기</button>

							</c:when>
							<c:when test="${book.rent_able eq 2}">
								<button type="button" style="margin : auto;" class="btn btn-secondary">대여불가</button>
							</c:when>
								
						</c:choose>
						<button type="button" class="btn btn-secondary" style="margin : auto;"onclick="CloseWindow();">닫기</button>
					
      					
					</div>
                 </div>	
		 
			</div>
		</div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
<form role="form">
	<input type="hidden" name="book_no" value="${book.book_no }" />
</form>
 <jsp:include page="./js/rent_js.jsp" />
 
 <script>
<c:if test="${from eq 'modify'}" >
	alert("정상적으로 수정되었습니다.");	
	window.opener.location.reload();
</c:if>
<c:if test="${from eq 'remove'}" >
	alert("삭제되었습니다.");
	window.opener.location.reload();
	window.close();
</c:if>
 function BookPictureThumb(targetObj, fileName,contextPath){ //(대상, 이미지파일명) 
		
		targetObj.style.backgroundImage="url('"+contextPath+"/book/getPicture.do?picture="+fileName+"')";
		targetObj.style.backgroundPosition="center";
		targetObj.style.backgroundRepeat="no-repeat";
		targetObj.style.backgroundSize="cover";
	}
window.onload=function(){
	BookPictureThumb(document.querySelector('[data-id="${book.book_no}"]'),'${book.photo}','<%=request.getContextPath()%>');
	$('ul.tabs li').click(function(){
		var tab_id = $(this).attr('data-tab');

		$('ul.tabs li').removeClass('current');
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	})	
	$('[data-toggle="tooltip"]').tooltip();
	
}
function delete_go(){
	var formObj = $("form[role='form']");
	//alert("click remove btn");
	var answer = confirm("삭제 후에는 복원이 어렵습니다. \n정말 삭제하시겠습니까?");
	if(answer){		
		formObj.attr("action", "remove.do");
		formObj.attr("method", "post");
		formObj.submit();
	}
}
function modify_go(){
	var formObj = $("form[role='form']");
	//alert("click modify btn");
	formObj.attr({
		'action':'modifyForm.do',
		'method':'post'
	});
	formObj.submit();
}

</script>
 

 
 
</body>






