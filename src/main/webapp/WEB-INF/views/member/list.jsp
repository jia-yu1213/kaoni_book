 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="cri" value="${pageMaker.cri }" />

<head></head>

<title>회원목록</title>

<body>

<c:if test="${from eq 'regist' }">
	<script>
		alert("회원등록에 성공했습니다.\n 회원리스트 페이지로 이동합니다.");
		window.opener.location.reload();
		window.close();
	</script>
</c:if>

	 <!-- Main content -->
	<section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>회원목록</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="list.do">
				        	<i class="fa fa-dashboard"></i>회원관리
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
	 
	 
   	<section class="content">
   		<div class="card">
   			<div class="card-header with-border">
   				<button type="button" class="btn btn-primary" onclick="OpenWindow('registForm.do','회원등록',708, 600);" >회원등록</button>
   				<div id="keyword" class="card-tools" style="width:550px;">
   					 <div class="input-group row">
   					 	<!-- search bar -->
   					 	
   					 	 <!-- sort num -->
					  	<select class="form-control col-md-3" name="perPageNum" id="perPageNum"
					  		onchange="list_go(1);">
					  		<option value="10" >정렬개수</option>
					  		<option value="2" ${cri.perPageNum == 2 ? 'selected':''}>2개씩</option>
					  		<option value="3" ${cri.perPageNum == 3 ? 'selected':''}>3개씩</option>
					  		<option value="5" ${cri.perPageNum == 5 ? 'selected':''}>5개씩</option>
					  		
					  	</select>
					  	
					  	  <!-- search bar -->
					 	<select class="form-control col-md-3" name="searchType" id="searchType">
							<option value=""  ${cri.searchType eq '' ? 'selected':''}>검색구분</option>
							<option value="n"  ${cri.searchType eq 'n' ? 'selected':''}>이름</option>
							<option value="i"  ${cri.searchType eq 'i' ? 'selected':''}>아이디</option>
							<option value="p"  ${cri.searchType eq 'p' ? 'selected':''}>전화번호</option>
							<option value="s"  ${cri.searchType eq 's' ? 'selected':''}>상태</option>
						</select>
						<!-- keyword -->
						
						<c:choose>
							<c:when test="${cri.searchType eq 's' &&  cri.keyword eq '활성'}">
	   					 		<input  class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value=""/>
	 					 	</c:when>
							<c:when test="${cri.searchType eq 's' &&  cri.keyword eq '휴면'}">
	   					 		<input  class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value=""/>
	 					 	</c:when>
	 					 	<c:otherwise>
	   					 		<input  class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${cri.keyword }"/>
	 					 	</c:otherwise>
						</c:choose>
						
						<span class="input-group-append">
							<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span>
					<!-- end : search bar -->		
   					 </div>
   				</div>   			
   			</div>
   			<div class="card-body" style="text-align:center;">
    		  <div class="row"> 
	             <div class="col-sm-12">	
		    		<table class="table table-bordered">
		    			<tr>
		                	<th>아이디</th>
		                	<th>이름</th>
		                	<th>생년월일</th>
		                	<th>전화번호</th>
		                	<th>상태</th>
		               	</tr>
		            
		               	<c:forEach items="${memberList }" var="member" >
		               	  <tr  onclick="OpenWindow('detail.do?id=${member.id}','','704','606');" style="cursor:pointer;">
		               		<td>${member.id }</td>
		               		<td>${member.name }</td>
		              		<td>
		              			<fmt:formatDate value="${member.birth_date }" pattern="yyyy-MM-dd"/>
		              		</td>
		              		<c:set var="phone" value="${member.phone.replace('-','') }" />
		              		<td>
		              			${phone.substring(0,3) }-${phone.substring(3,7) }-${phone.substring(7)}
		              		</td>
          				     <td>
          				     	<c:choose>
          				     		<c:when test="${member.enabled eq '0'}">활성</c:when>
          				     		<c:when test="${member.enabled eq '1'}"><span style="color : red">휴면</span></c:when>
          				     		<c:when test="${member.enabled eq '2'}">탈퇴</c:when>
          				     	</c:choose>
       				     	</td>
		              	  </tr>
		               	</c:forEach>   	
		              
		               	
		            </table>
    		     </div> <!-- col-sm-12 -->
    	       </div> <!-- row -->
    		</div> <!-- card-body -->
    		<div class="card-footer">
    			<%@ include file="/WEB-INF/views/common/pagination.jsp" %>
    		</div>
	     </div>
   	</section>

</body>





