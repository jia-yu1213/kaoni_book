<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
      <c:forEach items="${menuList }" var="menu">
      	<c:choose>
      		<c:when test="${menu.mname eq '회원관리'}">
	      		<c:choose>
	      			<c:when test="${loginUser eq null || loginUser.authority eq 1}">
	      			</c:when>
	      			<c:when test="${loginUser.authority eq 0}">
	      				<li class="nav-item d-none d-sm-inline-block">
        					<a href="javascript:goPage('${menu.murl }','${menu.mcode }');" class="nav-link">${menu.mname }</a>
      					</li>
	      			</c:when>
	      		</c:choose>
      		</c:when>
      		<c:when test="${menu.mname ne '회원관리'}">
	      		<li class="nav-item d-none d-sm-inline-block">
        			<a href="javascript:goPage('${menu.murl }','${menu.mcode }');" class="nav-link">${menu.mname }</a>
      			</li>
      		</c:when>
      	</c:choose>      
            
      </c:forEach>
      
      
    </ul>
    <ul class="navbar-nav ml-auto">

    	<li class="nav-item">
			<c:choose>
				<c:when test="${loginUser eq null}">
					<a href="common/loginForm"> 로그인</a>
				</c:when>
				<c:when test="${loginUser ne null}">
					<a href="common/logout"> 로그아웃</a>
				</c:when>
				
			</c:choose>
    	</li>
    </ul>

  </nav>
  <!-- /.navbar -->
  