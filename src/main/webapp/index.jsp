<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

충돌이 일어날꺼야


이거 충돌이야
<c:if test="${!empty loginUser }">
	<script>
		location.href="index.do";
	</script>
</c:if>
<c:if test="${empty loginUser }">
	<jsp:forward page="/WEB-INF/views/common/loginForm.jsp"/>
</c:if>