<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>회원등록</title>

<head>

<style>

.picdiv{

    border: 1px solid green;;
    height: 200px;
    width: 160px;
    float: left;
    object-fit: cover;
    background-size: contain;
}

#inputFile, #oldFile{
	
	height: 200px;
    width: 160px;
    object-fit: cover;
}

.register-card {
  background-color: #E9ECEF;
  border-top: 0;
  color: #666;
  padding: 20px;
  text-align :center
  
}

[class*='pull-right']{
	width: 75px;
    margin-top: 10px;
    margin-right: 5px;
}

.md-2, .card-footer {
	background-color: ghostwhite;
}

</style>

</head>

<body>

<div>
	<!-- Content Wrapper. Contains page content -->
  	 <!-- <section class="content-header" style="background-color:  #E9ECEF; padding: 0;"> -->
	  		<div class="row md-2">
          		<div>
          			<button type="button" onclick="regist_go();return false;" id="registBtn" class="btn btn-primary pull-right" style="margin-left: 20px;">등록</button>
          		</div>
         		
          	</div>
  <!-- 	</section> -->
    <!-- Main content -->
    <section class="content register-page" style="background-color: ghostwhite;">       
		<div class="register-box" style="width:500px;">         
	    	<form role="form" class="form-horizontal" action="regist.do" method="post">
	    		<div class="register-card-header" >
					<h1 class="text-center" style="font-size: 35px;">회원등록 </h1>
	    		</div>
	    		
	        	<div class="register-card-body" style="width:500px; height: 440px;">
						
           	  			<div class="top3">
           	  				<div class="form-inline form-group">
           	  				<input type="hidden" name="picture" />
           	  					<label for="inputFile" class=" btn btn-warning btn-sm btn-flat input-group-addon" style="border-radius: 3px; height:38px;">파일선택</label>
								<input id="inputFileName" class="form-control" type="text" name="tempPicture" style="width: 310px;" disabled/>
								<span class="input-group-append-sm">											
									<button type="button" class="btn btn-info btn-sm btn-append" onclick="upload_go();" style="width: 70px; height:38px; ">업로드</button>											
								</span>
                 			</div>
                 		</div>
                 		
						<div class = "picdiv" id="pictureView" style="margin-top: 20px; border: 1px solid green;"></div>
           	  			<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 0px 5px 5px;">
								<label for="id" class="col-sm-4">아&nbsp;&nbsp;이&nbsp;&nbsp;디</label>
	                   			<input name="id" onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, &#39;&#39;);" type="text" class="form-control col-sm-5 id" id="id" placeholder="영문자,숫자" style="width: 50px; text-align:center;">
									<button type="button" onclick="idCheck_go();"  class="btn btn-info btn-sm btn-append col-sm-3" style="width: 30px; height:38px; font-size:0.87rem;">중복확인</button>
                 			</div>
                 		</div>
           	  			<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 0px 5px 5px;">
								<label for="id" class="col-sm-4">패스워드</label>
								<input class="form-control" name="pwd" type="password" class="form-control col-sm-8 pwd" id="pwd" placeholder="20글자 영문자,숫자 조합" style="width: 196px; text-align:center;" />
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="name" class="col-sm-4">이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</label>
	                   			<input name="name" type="text" class="form-control col-sm-8 name" style="width: 80px; text-align:center;">
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="birth_date" class="col-sm-4">생년월일</label>
	                   			<input name="birth_date" type="date" class="form-control col-sm-8 birth" placeholder="0000-00-00" style="width: 80px; text-align:center;">
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="phone" class="col-sm-4">전화번호</label>
	                   			<input name="phone" type="text" class="form-control col-sm-8 phone" placeholder="010-1234-1234" style="width: 80px; text-align:center;">
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="email" class="col-sm-4">이&nbsp;&nbsp;메&nbsp;&nbsp;일</label>
	                   			<input name="email" type="text" class="form-control col-sm-8 email" placeholder="example@naver.com" style="width: 80px; text-align:center;" >
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px 0;">
								<label for="address"  style="padding: 0; width:80px;" >주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소</label>
	                   			<input name="address" type="text" class="form-control address" style="width: 375px; text-align:center;" value="${member.address }" >
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px 0;">
								<label for="detail_address"  style="padding: 0; width:80px;">상세주소</label>
	                   			<input name="detail_address" type="text" class="form-control detailAddress" style="width: 375px; text-align:center;" value="${member.detail_address }" >
                 			</div>
                 		</div>
                 		
                 	</div>		
 	                
		          <div class="card-footer" style="padding:10px 0;" >
		          		<div class="row">
			          		<div class="col-sm-12 text-center">
<!-- 			            	<button type="button" id="listBtn" onclick="CloseWindow();" class="btn btn-primary pull-right">닫 기</button> -->
			            		<button type="button" id="listBtn" onclick="CloseWindow()" class="btn btn-sm btn-secondary float-center" style="width:60px;">닫기 </button>
			            		
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
</body>