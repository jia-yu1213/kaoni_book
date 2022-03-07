<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>회원상세보기</title>

<head>
	 <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote-bs4.css">
<style>

.picdiv{

    background-color: #E9ECEF;
    height: 200px;
    width: 160px;
    float: left;
}

.register-card {
  background-color: ghostwhite;
  border-top: 0;
  color: #666;
  padding: 20px;
  text-align :center
  
}

</style>

</head>
<body>

  <!-- Content Wrapper. Contains page content -->
  <div >
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
		
	        <div class="card-header" >
	        <div class ="card-tools">
							<button type="button" class="btn btn-primary" id="registBtn" onclick="regist_go();">등 록</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-warning" id="cancelBtn" onclick="CloseWindow();" >취 소</button>
						</div>  
	        	<h3 class="card-title p-1">공지등록</h3>
	        	
	        </div>
	    	<form role="form" class="form-horizontal" name="registForm" action = "regist.do" method="post">
	        	<div class="card-body" >
					<div class="row">
						<div class="col-sm-4">
							<input type="hidden" name="picture" />
							<div class="input-group" >
								<div class="mailbox-attachments clearfix" style="text-align: center;">
									<div class="has-img" id="pictureView" style="border: 1px solid green; height:220px;width: 160px; margin: 0 auto;"></div>
									<div class="mailbox-attachment-info">
										<div class="input-group ">
											<label for="inputFile" class=" btn btn-primary btn-sm btn-flat input-group-addon">파일선택</label>
										</div>
										<input id="inputFileName" class="form-control" type="text" name="tempPicture" disabled/>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-8" style="padding-left:0px">
							<div class="form-inline form-group" style="margin: 0px 5px 5px;">
	                   			<input name="title" id="title"type="text" class="form-control col-sm-12">
                 			</div>
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="ori_title" class="col-sm-3">원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제</label>
	                   			<input name="ori_title" id="ori_title"type="text" class="form-control col-sm-9" style="width: 80px;">
                 			</div>
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="writer" class="col-sm-3">저&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;자</label>
	                   			<input name="writer"id="writer" type="text" class="form-control col-sm-9" style="width: 80px;">
                 			</div>
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="translator" class="col-sm-3">번&nbsp;&nbsp;역&nbsp;&nbsp;자</label>
								
	                   			<input name="translator"id="translator" type="text" class="form-control col-sm-9" style="width: 80px;">
                 			</div>
                 		
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="publisher" class="col-sm-3">출&nbsp;&nbsp;판&nbsp;&nbsp;사</label>
	                   			<input name="publisher" id="publisher"type="text" class="form-control col-sm-9" style="width: 80px;" >
                 			</div>
                 	
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="publishing_date" class="col-sm-3">출&nbsp;&nbsp;판&nbsp;&nbsp;일</label>
	                   			<input name="publishing_date"id="publishing_date" type="date" class="form-control col-sm-9" style="width: 80px;" >
                 			</div>
                 			<div class="form-inline form-group" style="margin: 5px;">
								<label for="cate_no" class="col-sm-3">분&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;류</label>
<!-- 	                   			<input name="email" type ="text"> -->
	                   			<select name="cate_no" id="cate_no"  class="form-control col-sm-9" style="width: 80px;" >
	                   			<c:forEach items="${cateList }" var="cate">
	                   				<option value="${cate.cate_no }">${cate.cate_name }</option>
	                   			</c:forEach>
								    
								</select>
	                   			
                 			</div>
                 	
						</div>
					
					</div>
					<div class="row">
						<div class="form-inline form-group">
						<label for="book_intro" style="height: 40px">책 소개</label>
						<textarea class="textarea" name="book_intro" id="book_intro" rows="5" style="display: none;"></textarea>
					</div>
					</div>
					<div class="row">
					<div class="form-inline form-group">
						<label for="book_index" style="height: 40px">책 소개</label>
						<textarea class="textarea" name="book_index" id="book_index" rows="5" style="display: none;"></textarea>
					</div>
					</div>
					<div class="row">
					<div class="form-inline form-group">
						<label for="writer_intro" style="height: 40px">책 소개</label>
						<textarea class="textarea" name="writer_intro" id="writer_intro" rows="5" style="display: none;"></textarea>
					</div>
					</div>
				
						
								
									
           	  		
                 		
                 	</div>	
 	             
		          	   
	      	  </form>

	      	  
	      	  
	      	  
	      	  
      	  </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

 <form role="imageForm" action="upload/picture.do" method="post" enctype="multipart/form-data">
	<input id="inputFile" name="pictureFile" type="file" class="form-control" onchange="picture_go();"
			style="display:none;">
	<input id="oldFile" type="hidden" name="oldPicture" value="" />
	<input type="hidden" name="checkUpload" value="0" />	
</form>


<jsp:include page="./js/regist_js.jsp" />  
 
 
 
 <script>
window.onload=function(){
	Summernote_start($('#book_intro')); 
	Summernote_start($('#book_index')); 
	Summernote_start($('#writer_intro')); 
}

function regist_go(){
	var form = document.registForm;
	
	form.submit();
}
</script>
 
 
 <%@ include file="/WEB-INF/views/common/summernote.jsp" %>
 
 
 
</body>






