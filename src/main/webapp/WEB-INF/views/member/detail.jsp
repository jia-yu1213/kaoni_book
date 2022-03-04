<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>회원상세보기</title>

<head>
	<%-- <%@include file="./detailStyle.jsp" %>
 --%>
<style>

.picdiv{

    background-color: lightgray;
    height: 200px;
    width: 160px;
    float: left;
}
</style>

</head>
<body>

  <!-- Content Wrapper. Contains page content -->
  <div >
<!--   	 <section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>회원정보 상세보기</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="#">
				        	<i class="fa fa-dashboard">회원관리</i>
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	상세보기
			        </li>		        
	    	  </ol>
	  			</div>
	  		</div>
	  	</div>
  	</section> -->
    <!-- Main content -->
    <section class="content register-page">       
		<div class="register-box">         
	    	<form role="form" class="form-horizontal"  method="post">
	    		<div class="register-card-header" >
	    			<h1 class="text-center">회원정보 상세보기</h1>
	    		</div>
	        	<div class="register-card-body" style="width:500px; height: 350px;">
	            	
						<div class = "picdiv" data-id="${member.id }"></div>
						
           	  			<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 0px 5px 5px;">
								<label for="id" class="col-sm-4">아&nbsp;&nbsp;이&nbsp;&nbsp;디</label>
	                   			<input name="id" type="text" class="form-control col-sm-8" style="width: 100px;" value="${member.id }" readonly>
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="name" class="col-sm-4">이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</label>
	                   			<input name="name" type="text" class="form-control col-sm-8" style="width: 100px;" value="${member.name }" readonly>
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="birth" class="col-sm-4">생년월일</label>
	                   			<input name="birth" type="text" class="form-control col-sm-8" style="width: 100px;" value="${member.birth_date }" pattern="yyyy-MM-dd" readonly>
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="phone" class="col-sm-4">전화번호</label>
	                   			<input name="phone" type="text" class="form-control col-sm-8" style="width: 100px;" value="${member.phone }" readonly>
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px;">
								<label for="email" class="col-sm-4">이&nbsp;&nbsp;메&nbsp;&nbsp;일</label>
	                   			<input name="email" type="text" class="form-control col-sm-8" style="width: 60px;" value="${member.email }" readonly>
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px 0;">
								<label for="address" class="col-sm-3" style="padding: 0;">주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소</label>
	                   			<input name="address" type="text" class="form-control col-sm-9" style="width: 98px;" value="${member.address }" readonly>
                 			</div>
                 		</div>
       		          	<div class="top3">
           	  				<div class="form-inline form-group" style="margin: 5px 0;">
								<label for="detailAddress" class="col-sm-3" style="padding: 0;">상세주소</label>
	                   			<input name="detailAddress" type="text" class="form-control col-sm-9" style="width: 98px;" value="${member.detail_address }" readonly>
                 			</div>
                 		</div>
                 		
<%-- 	                <div class="form-group row">
	                  <label for="inputPassword3" class="col-sm-3 control-label text-right">이  름</label>
	
	                  <div class="col-sm-9">
	                    <input name="pwd" type="text" class="form-control" id="inputPassword3" value="${member.name }" readonly>
	                  </div>
	                </div>
	                 <div class="form-group row">
	                  <label for="inputPassword3" class="col-sm-3 control-label text-right">이메일</label>
	
	                  <div class="col-sm-9">
	                    <input name="email" type="email" class="form-control" id="inputPassword3" value="${member.email }" readonly>
	                  </div>
	                </div>
	                 <div class="form-group row">
	                  <label for="inputPassword3" class="col-sm-3 control-label text-right">전화번호</label>
	                  <div class="col-sm-9">   
	                  	<input name="phone" type="text" class="form-control" id="inputPassword3" value="${member.phone }" readonly>	                
	                  </div>                  
	                </div>               
	              </div>  
		          <div class="card-footer" style="padding:5px 0;" >
		          		<div class="row">
		          		<c:if test="${loginUser.id eq member.id }">
			          		<div class="col-sm-3 text-center">
			          			<button type="button" onclick="location.href='modifyForm.do?id=${member.id}';" id="modifyBtn" class="btn btn-warning ">수 정</button>
			          		</div>
		          		
			          		<div class="col-sm-3 text-center">
			          			<button type="button" onclick="location.href='remove.do?id=${member.id}';" 
			          			id="deleteBtn" class="btn btn-danger" >삭 제</button>
			          		</div>
		          			
			          		<div class="col-sm-3 text-center">
			          			<c:if test="${member.enabled ne 0 }">
			          			<button type="button" onclick="location.href='stop.do?id=${member.id}';" 
			          			id="stopBtn" class="btn btn-info" >비활성</button>
			          			</c:if>
			          			<c:if test="${member.enabled eq 0 }">
			          			<button type="button" onclick="location.href='active.do?id=${member.id}';" 
			          			id="activeBtn" class="btn btn-info" >활&nbsp;&nbsp;성</button>
			          			</c:if>
			          		</div>
		          		</c:if>
		          	
			          		<div class="col-sm-3 text-center">
			            		<button type="button" id="listBtn" onclick="CloseWindow();" class="btn btn-primary pull-right">닫 기</button>
			            	</div>
		          	    </div>   --%>	
		          </div>
	      	  </form>
      	  </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

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
	<c:if test="${param.from eq 'remove'}" >
		alert("${removeMember.name}님의 정보가 삭제되었습니다.");
		<c:if test="${empty loginUser}">
			window.opener.parent.location.href="<%=request.getContextPath()%>";
		</c:if>
		window.close();
	</c:if>
	
}
</script>    
  
</body>






