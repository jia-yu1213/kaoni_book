<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>회원상세보기</title>

<head>

<style>
.picdiv{
    border: 1px solid green;;
    height: 200px;
    width: 160px;
    float: left;
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
<div style="overflow-x: hidden; overflow-y: hidden; background-color: ghostwhite; height: 611px; ">

  <!-- Content Wrapper. Contains page content -->
  	 <!-- <section class="content-header" style="background-color:  #E9ECEF; padding: 0;"> -->
	  		<div class="row md-2">
          		<div>
          			<button type="button" onclick="modify_go();" id="modifyBtn" class="btn btn-primary pull-right" style="margin-left: 20px;">수정</button>
          		</div>

          		</div>
  <!-- 	</section> -->
    <!-- Main content -->
    <section class="content" style="background-color: ghostwhite;">       
		<div class="register-box" style="width:500px;">         
	    	<form role="form" class="form-horizontal" action="modify.do" method="post" enctype="multipart/form-data">
	    		<div class="register-card-header" >
	    			<h1 class="text-center" style="font-size: 35px; margin-left: 191px;">회원정보 수정</h1>
	    		</div>

	        	<div class="register-card-body" style="width:500px; height: 440px; margin-left: 97px;">

						<input type="hidden" name="oldPicture"  value="${member.picture }"/>
						<input type="file" id="inputFile" onchange="changePicture_go();" name="picture" style="display:none" />

           	  			<div class="top3">
           	  				<div class="form-inline form-group">

           	  					<label for="inputFile" class=" btn btn-warning btn-sm btn-flat input-group-addon" style="border-radius: 3px; height:38px;">사진변경</label>
								<input id="inputFileName" class="form-control" type="text" name="tempPicture" style="width: 380px;" value="${member.picture.split('\\$\\$')[1] }" disabled/>
								<input id="picture" class="form-control" type="hidden" name="uploadPicture" />
                 			</div>
                 		</div>

						<div class = "picdiv" id="pictureView" style="margin-top: 20px; border: 1px solid green;"></div>
           	  			<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 0px 5px 5px;">
       	  						<label for="id" class="col-sm-4">아&nbsp;&nbsp;이&nbsp;&nbsp;디</label>
	                   			<input name="id" type="text" class="form-control col-sm-8 id" style="width: 80px; text-align:center;" value="${member.id }" readonly >
                 			</div>
                 		</div>
           	  			<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 0px 5px 5px;">
								<label for="id" class="col-sm-4">패스워드</label>
								<input class="form-control" name="pwd" type="password" class="form-control col-sm-8 pwd" id="pwd" style="width: 196px; text-align:center;"  value="${member.pwd }" readonly>
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="name" class="col-sm-4">이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</label>
	                   			<input name="name" type="text" class="form-control col-sm-8 name" style="width: 80px; text-align:center;"  value="${member.name }">
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="birth_date" class="col-sm-4">생년월일</label>
								<fmt:formatDate value="${member.birth_date }" pattern="yyyy-MM-dd" var="birth_date"/>
	                   			<input name="birth_date" type="date" class="form-control col-sm-8 birth" style="width: 80px; text-align:right;"  value="${birth_date }" >
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="phone" class="col-sm-4">전화번호</label>
								<c:set var="phone" value="${member.phone.replace('-','') }" />
	                   			<input name="phone" type="text" class="form-control col-sm-8 phone" style="width: 80px; text-align:center;" value="${phone.substring(0,3) }-${phone.substring(3,7) }-${phone.substring(7)}">
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="email" class="col-sm-4">이&nbsp;&nbsp;메&nbsp;&nbsp;일</label>
	                   			<input name="email" type="text" class="form-control col-sm-8 email" placeholder="example@naver.com" style="width: 80px; text-align:center;" value="${member.email }" >
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
	                   			<input name="detail_address" type="text" class="form-control detailAddress" style="width: 375px; text-align:center;" value="${member.detail_address }">
                 			</div>
                 		</div>

                 	</div>	

		          	<div class="card-footer" style="padding:10px 0;" >
		          		<div class="row">
			          		<div class="col-sm-12 text-center">
			            		<button type="button" id="listBtn" onclick="history.go(-1);" class="btn btn-sm btn-secondary float-center" style="width:60px; margin-left: 198px; margin-top: 10px;">취소 </button>

			            	</div>
		          	    </div> 
		          	</div>

	      	  </form>
      	  </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
 </div>
 
<script>
 
window.onload=function(){
	MemberPictureThumb(document.querySelector('[data-id="${member.id}"]'),'${member.picture}','<%=request.getContextPath()%>');
	
	<c:if test="${param.from eq 'modify'}" >
		alert("${member.name}님의 정보가 수정되었습니다.");	
		location.href='detail.do?id=${member.id}';
		
		if(${parentReload}){			
			if(confirm('로그인 사용자의 정보가 수정되었습니다.\n현재 화면을 닫고 새로고침 하시겠습니까?')){
				window.opener.parent.location.reload(true);
				window.close();
			}	
		}
	</c:if>
	
	<c:if test="${param.from eq 'active'}" >
		alert("${activeMember.name}님의 계정이 활성상태가되었습니다.");
		<c:if test="${empty loginUser}">
			window.opener.parent.location.href="<%=request.getContextPath()%>";
		</c:if>
		window.close();
		opener.parent.location.reload();
	</c:if>
	<c:if test="${param.from eq 'stop'}" >
		alert("${stopMember.name}님의 계정이 휴면상태가되었습니다.");
		<c:if test="${empty loginUser}">
			window.opener.parent.location.href="<%=request.getContextPath()%>";
		</c:if>
		window.close();
		opener.parent.location.reload();
	</c:if>
	
	<c:if test="${param.from eq 'remove'}" >
		alert("${removeMember.name}님의 정보가 삭제되었습니다.");
		<c:if test="${empty loginUser}">
			window.opener.parent.location.href="<%=request.getContextPath()%>";
		</c:if>
		window.close();
		opener.parent.location.reload();
	</c:if>
	
}
</script>    

    <%@ include file="./js/modify_js.jsp" %>

</body> 