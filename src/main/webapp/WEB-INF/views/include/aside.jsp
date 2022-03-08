<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="index.do" class="brand-link">
      <img src="<%=request.getContextPath() %>/resources/images/main.png" alt="AdminLTE Logo" class="img-circle elevation-3" style="height : 50px">
      <span class="brand-text" style="font-size: x-large;">&nbsp;&nbsp;KAONI-BOOK</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3">
      
		<c:choose>
			<c:when test="${loginUser eq null }">
       			<div class="info">
          			<div class="row">
						<button onclick="location.href='<%=request.getContextPath() %>/common/loginForm.do';" class="btn btn-xs btn-primary col-xs-3 " type="button" >LOGIN</button>
				    </div>
		       	</div>
			</c:when>
			<c:when test="${loginUser ne null }">
        		<div style="clear:both; ">
        			<c:if test="${loginUser.picture ne null }">
		      			<img src="<%=request.getContextPath() %>/member/getPicture.do?picture=${loginUser.picture}" class="elevation-2" style="height:150px; width:140px;margin: 0 auto; display: inherit;" alt="User Image">
        			</c:if>
        		</div>
        		<br>
        		<div class="info" style="margin-left : 10px">
        			<div class="row">
						&nbsp;<a href="javascript:OpenWindow('<%=request.getContextPath() %>/member/detail.do?id=${loginUser.id }','내정보','800','700');" class="d-block">${loginUser.name }</a>&nbsp;&nbsp;
						<button onclick="location.href='<%=request.getContextPath() %>/common/logout.do';"class="btn btn-xs btn-primary col-xs-3 " type="button" >LOGOUT</button>
        			
        			</div>
				    <a href="tel:${loginUser.phone }">tel : ${loginUser.phone }</a><br/>
           			<a href="mailto:${loginUser.email }">email : ${loginUser.email }</a>
		        </div>
			</c:when>
		</c:choose>
      
      
      </div>
      <div>
      	대여중인 책
      </div>
    </div>
    <!-- /.sidebar -->
  </aside>