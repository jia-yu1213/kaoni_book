<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>도서 수정</title>

<head>
	 <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote-bs4.css">
<style>

.picdiv{

    background-color: #E9ECEF;
    height: 200px;
    width: 160px;
    float: left;
}


</style>

</head>
<body>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" >
    <!-- Main content -->
    <section class="content container-fluid" style="background-color: ghostwhite;">       
		<div class="row justify-content-center" > 
			<div class="col-md-9">
		        <div class="card-header">
		        	<div style="text-align: right;width: 100%;">
		        		<div style="display: inline-block;">
							<button type="button" class="btn btn-primary" id="registBtn"  onclick="modify_go();">수 정</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-warning" id="cancelBtn" onclick="history.go(-1);">취 소</button>
						</div> 
		        	</div>
					<div style="text-align:center">
						<h1>도서수정</h1>
					</div>
		        </div>
	
		    	<form role="form" class="form-horizontal" action="modify.do" method="post" enctype="multipart/form-data">
		        	<div class="card-body" >
						<div class="row">
							<input type="hidden" value="${book.book_no }"/>
							<div class="col-sm-4">
							
								<input id="inputFile" name="picture" type="file" class="form-control" onchange="changePicture_go();" style="display:none;">
								<input id="oldPicture" type="hidden" name="oldPicture" value="${book.photo }" />
								<div class="input-group" >
									<div class="mailbox-attachments clearfix" style="text-align: center;">
										<div class="has-img" id="pictureView" data-id="${book.book_no}" style="border: 1px solid green; height:220px;width: 160px; margin: 0 auto;"></div>
										<div class="mailbox-attachment-info">
											<div class="input-group " style="height: 30px;">
												<label for="inputFile"  class=" btn btn-primary btn-sm btn-flat input-group-addon">사진 변경</label>
											</div>
											<input id="inputFileName" class="form-control" type="text" name="tempPicture" disabled value="${book.photo.split('\\$\\$')[1] }"/>
											<input id="picture" class="form-control" type="hidden" name="uploadPicture" />
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-8" style="padding-left:0px">
								<div class="form-inline form-group" style="margin: 0px 5px 5px;">
		                   			<input name="title" id="title"type="text" class="form-control col-sm-12" value="${book.title }">
	                 			</div>
	           	  				<div class="form-inline form-group" style="margin: 5px;">
									<label for="ori_title" class="col-sm-3">원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제</label>
		                   			<input name="ori_title" id="ori_title"type="text" class="form-control col-sm-9" style="width: 80px;" value="${book.ori_title }">
	                 			</div>
	           	  				<div class="form-inline form-group" style="margin: 5px;">
									<label for="writer" class="col-sm-3">저&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;자</label>
		                   			<input name="writer"id="writer" type="text" class="form-control col-sm-9" style="width: 80px;" value="${book.writer }">
	                 			</div>
	           	  				<div class="form-inline form-group" style="margin: 5px;">
									<label for="translator" class="col-sm-3">번&nbsp;&nbsp;역&nbsp;&nbsp;자</label>
									
		                   			<input name="translator"id="translator" type="text" class="form-control col-sm-9" style="width: 80px;" value="${book.translator }">
	                 			</div>
	                 		
	           	  				<div class="form-inline form-group" style="margin: 5px;">
									<label for="publisher" class="col-sm-3">출&nbsp;&nbsp;판&nbsp;&nbsp;사</label>
		                   			<input name="publisher" id="publisher"type="text" class="form-control col-sm-9" style="width: 80px;" value="${book.publisher }">
	                 			</div>
	                 	
	           	  				<div class="form-inline form-group" style="margin: 5px;">
									<label for="publishing_date" class="col-sm-3">출&nbsp;&nbsp;판&nbsp;&nbsp;일</label>
		                   			<input name="publishing_date"id="publishing_date" type="date" class="form-control col-sm-9" style="width: 80px;" value="<fmt:formatDate value="${book.publishing_date}" pattern="yyyy-MM-dd" />" />
	                 			</div>
	                 			<div class="form-inline form-group" style="margin: 5px;">
									<label for="cate_no" class="col-sm-3">분&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;류</label>
	<!-- 	                   			<input name="email" type ="text"> -->
		                   			<select name="cate_no" id="cate_no"  class="form-control col-sm-9" style="width: 80px;" >
		                   			<c:forEach items="${cateList }" var="cate">
		                   				<option value="${cate.cate_no }"<c:if test="${cate.cate_no == book.cate_no }"> selected </c:if> >${cate.cate_name }</option>
		                   			</c:forEach>
									    
									</select>
		                   			
	                 			</div>
	                 	
							</div>
						
						</div>
						<div class="row">
							<div class="form-inline form-group">
								<label for="book_intro" style="height: 40px">책 소개</label>
								<textarea class="textarea" name="book_intro" id="book_intro" rows="5" style="display: none;" >${book.book_intro }</textarea>
							</div>
						</div>
						<div class="row">
							<div class="form-inline form-group">
								<label for="book_index" style="height: 40px">책 목차</label>
								<textarea class="textarea" name="book_index" id="book_index" rows="5" style="display: none;">${book.book_index }</textarea>
							</div>
						</div>
						<div class="row">
							<div class="form-inline form-group">
								<label for="writer_intro" style="height: 40px">저자 소개</label>
								<textarea class="textarea" name="writer_intro" id="writer_intro" rows="5" style="display: none;">${book.writer_intro }</textarea>
							</div>
						</div>
	                 		
	                 	</div>	
		      	  </form>
			</div>
		</div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->


<jsp:include page="./js/modify_js.jsp" />  
 
 
 
 <script>
 function BookPictureThumb(targetObj, fileName,contextPath){ //(대상, 이미지파일명) 
		
		targetObj.style.backgroundImage="url('"+contextPath+"/book/getPicture.do?picture="+fileName+"')";
		targetObj.style.backgroundPosition="center";
		targetObj.style.backgroundRepeat="no-repeat";
		targetObj.style.backgroundSize="cover";
	}
window.onload=function(){
	Summernote_start($('#book_intro')); 
	Summernote_start($('#book_index')); 
	Summernote_start($('#writer_intro'));
	BookPictureThumb(document.querySelector('[data-id="${book.book_no}"]'),'${book.photo}','<%=request.getContextPath()%>');
}



</script>
 
 
 <%@ include file="/WEB-INF/views/common/summernote.jsp" %>
 
 
 
</body>






