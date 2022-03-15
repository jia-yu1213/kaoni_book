<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="cri" value="${pageMaker.cri }" />

<head>

</head>

<title>도서목록</title>

<body>
	<style type="text/css">
label {
	text-align: center;
	border: 1px solid #ccc;
	display: inline-block;
	position: relative;
	width: 120px;
}

label input {
	display: none;
}

label span {
	width: 120px;
	height: 30px;
	overflow: hidden;
	line-height: 1;
	text-align: center;
	padding: 5px;
	font-size: 10pt;
	left: 0;
	top: 50%;
	margin-top: -7.5px;
}
</style>
	<script>
		window.onload = function() {
			var id = $('input[name="cateType"]:checked').val();
			console.log(id);
			$('#label' + id).css({
				"background" : "#d2d2d2",
				"border-color" : "#d2d2d2"
			});

			target = document.getElementById('excelFile');
			target.addEventListener('change', function() {
				fileList = "";
				for (i = 0; i < target.files.length; i++) {
					fileList += target.files[i].name + '<br>';
				}
				console.log(target.files[0].name)
				target2 = document.getElementById('file_route');
				target2.innerHTML = fileList;
			});
		}
	</script>

	<input type="hidden" id="loginUser" value="${loginUser}">
	<c:if test="${from eq 'regist'}">
		<script>
			alert("도서 등록이 성공했습니다.");
			window.opener.location.reload();
			window.close();
		</script>
	</c:if>
	<c:if test="${from eq 'login'}">
		<script>
			alert("로그인이 필요한 서비스입니다. \n로그인 후 이용해주시기 바랍니다.")
			location.reload();
		</script>
	</c:if>
	<!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1>도서 목록</h1>

					<c:choose>
						<c:when test="${loginUser eq null || loginUser.authority eq 1}">
						</c:when>
						<c:when test="${loginUser.authority eq 0}">
							<button type="button" class="btn btn-secondary"
								style="height: 40px;" id="registBtn"
								onclick="OpenWindow('returnBookMaster.do','도서반납',900,700);">반납
								관리</button>
						</c:when>
					</c:choose>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="list.do"> <i
								class="fa fa-dashboard"></i>도서
						</a></li>
						<li class="breadcrumb-item active">목록</li>
					</ol>
				</div>
			</div>
		</div>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="card">
			<div class="card-header with-border">
				<div class="row">
					<div style="float: left; margin-right: 10px">
						<button type="button" class="btn btn-secondary" id="saveBookBtn"
							onclick="saveBook();">목록 엑셀 저장</button>

					</div>
					<c:choose>
						<c:when test="${loginUser eq null || loginUser.authority eq 1}">
						</c:when>
						<c:when test="${loginUser.authority eq 0}">
							<button type="button" class="btn btn-secondary"
								style="height: 40px;" id="registBtn"
								onclick="OpenWindow('registForm.do','도서등록',900,700);">도서등록</button>
							<form id="excelUpload" name="excelUpload"
								enctype="multipart/form-data" method="post" action="excelUpload">
								<div
									style="border: 2px solid lightgrey; border-radius: 5px; margin-left: 10px">
									<label class="btn btn-secondary"
										style="float: left; font-weight: 400"> 엑셀 등록 <input
										id="excelFile" type="file" name="excelFile"
										style="display: none">
									</label>
									<div id="file_route"
										style="width: 200px; float: left; padding-top: 5px; text-align: left; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"></div>
									<button class="btn btn-secondary" id="addexcelImport"
										onclick="check()">추가</button>
								</div>
							</form>
						</c:when>
					</c:choose>


					<div id="keyword" class="card-tools"
						style="width: 400px; margin-left: auto">
						<div class="input-group row">
							<select style="display: none" class="form-control col-md-3"
								name="perPageNum" id="perPageNum" onchange="list_go2();">
								<option value="10">정렬개수</option>
								<option value="20" ${cri.perPageNum == 20 ? 'selected':''}>20개씩</option>
								<option value="50" ${cri.perPageNum == 50 ? 'selected':''}>50개씩</option>
								<option value="100" ${cri.perPageNum == 100 ? 'selected':''}>100개씩</option>
							</select> <select class="form-control col-md-4" name="searchType"
								id="searchType">
								<option value="tcw" ${cri.searchType eq 'tcw' ? 'selected':'' }>전
									체</option>
								<option value="t" ${cri.searchType eq 't' ? 'selected':'' }>제
									목</option>
								<option value="w" ${cri.searchType eq 'w' ? 'selected':'' }>저
									자</option>
								<option value="c" ${cri.searchType eq 'c' ? 'selected':'' }>출판사</option>
							</select> <input class="form-control" type="text" name="keyword"
								placeholder="검색어를 입력하세요." value="${param.keyword }" /> <span
								class="input-group-append">
								<button class="btn btn-primary" type="button"
									onclick="list_go2(1);" data-card-widget="search">
									<i class="fa fa-fw fa-search"></i>
								</button>
							</span>
						</div>
					</div>
				</div>
			</div>

			<div class="card-body">
				<div class="row" style="padding-left: 15px">
					<c:forEach items="${cateList }" var="cate">
						<label id="label${cate.cate_no }"><input type="radio"
							name="cateType" value="${cate.cate_no }" onclick="list_go2()"
							${cri.cateType eq cate.cate_no? 'checked':''} /><span>${cate.cate_name }</span></label>
					</c:forEach>
				</div>
				<table class="table table-bordered text-center">
					<tr style="font-size: 0.95em;">
						<th style="width: 8%;">번 호</th>
						<th style="width: 8%;">분 류</th>
						<th style="width: 45%;">제 목</th>
						<th style="width: 15%;">저 자</th>
						<th>대여종료일</th>
						<th style="width: 15%;">대여하기</th>
					</tr>
					<c:if test="${empty bookList }">
						<tr>
							<td colspan="5"><strong>해당 내용이 없습니다.</strong></td>
						</tr>
					</c:if>
					<c:forEach items="${bookList }" var="book">
						<tr style='font-size: 0.85em; cursor: pointer;'
							onclick="OpenWindow('detail.do?book_no=${book.book_no }','상세보기',800,800);">
							<td style='vertical-align: middle'>${book.rownum }</td>
							<td style='vertical-align: middle'>${book.cate_name }</td>
							<td id="boardTitle"
								style="text-align: left; max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis; vertical-align: middle">
								${book.title }</td>
							<td style='vertical-align: middle'>${book.writer }</td>
							<td style='vertical-align: middle'><jsp:useBean id="now"
									class="java.util.Date" /> <fmt:formatDate value="${now}"
									pattern="yyyy-MM-dd" var="today" /> <fmt:formatDate
									value="${book.rent_end }" pattern="yyyy-MM-dd" var="rent_end" />

								<c:choose>
									<c:when test="${rent_end eq null}">
									
									</c:when>

									<c:when test="${rent_end eq today}"> 
										반납예정일 
									</c:when>
									<c:when test="${rent_end < today}"> 
										연체중 
									</c:when>
									<c:when test="${rent_end > today}"> 
										${rent_end } 
									</c:when>

								</c:choose></td>
							<td style='vertical-align: middle'
								onclick="event.stopPropagation()">
								<!-- 예약한 사람한테는 예약완료로 보이게, 대여가능 상태일때도 예약이 된 경우는 예약한 사람만 가능하게 -->
								<c:choose>
									<c:when test="${book.book_status eq 0}">
										<button type="button" class="btn-sm btn-block btn-primary"
											onclick="resBook('${book.book_no }');">대여하기</button>
									</c:when>
									<c:when test="${book.book_status eq 1}">
										<button type="button" class="btn-sm btn-block btn-secondary"
										    onclick="resBook('${id}');">예약하기</button>
									</c:when>
									<c:when
										test="${book.book_status eq 2 ||book.book_status eq 3||book.book_status eq 5}">
										<button type="button" class="btn-sm btn-block btn-secondary">대여불가</button>
									</c:when>

								</c:choose>

							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="card-footer">
				<%@ include file="/WEB-INF/views/common/pagination2.jsp"%>
			</div>

		</div>

	</section>
	<!-- /.content -->
	<jsp:include page="./js/rent_js.jsp" />

</body>