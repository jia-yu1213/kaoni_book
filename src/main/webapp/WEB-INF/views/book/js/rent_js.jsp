<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
function rentBook(book_no){
	var login = $('#loginUser').val();
	//로그인체크
	if (!login) {
		alert("로그인이 필요한 서비스입니다. \n로그인 후 이용해주시기 바랍니다.")
	}else{
		//비동기 책 대여 중인지, 연체중인지 확인 
		//맵으로 상태를 보내서 대여 없는거, 대여중인거, 연체중인거, 연제기간인거
		$.ajax({
			url:"<%=request.getContextPath()%>/rent/checkRent",
			type:"post",
			success : function(dataMap){
				
				var status = dataMap.status;
				var data = dataMap.data;
			
				var answer;
				if (status=="overdueRent") {
					answer = confirm(data+"권이 연체중입니다. \n연체중에는 이용이 불가합니다. \n반납하시겠습니까?");
					if(answer){
						location = "<%=request.getContextPath()%>/mylist/list.do";
					}
				}else if (status=="overdueDate") {
					 alert("연체 기간입니다. \n"+data.split(" ")[0]+"까지 이용이 불가합니다.");
				}else if (status=="nowRent") {
					//대여가능
					answer = confirm(data+"권이 대여중입니다. \n대여하시겠습니까?");
					if(answer){
					
						location = "<%=request.getContextPath()%>/rent/registRent.do?book_no="+book_no;
					}
				}
					
			},
			error : function(){
				alert("대여ajax 에러");		
			}
		});		
	}
	
}



function saveBook(){
	
	var title = prompt('제목을 입력해주세요');
	if (title != null) {
		$.ajax({
			url : "saveBook?title="+title,
//	 		type : "post",
//			     dataType: 'application/json',
			success : function(result){
				if (result == "ok") {
					
				alert("도서목록 저장에 성공했습니다. ");
				opener.location.reload();
				//openwindow
	    		$('#acceptModal').modal('hide');
				}
			}, 
			error : function(){
				alert("실패")
			}
		});
	}


	
}


// 파일 타입 체크
function checkFileType(filePath){
	var fileFormat = filePath.split(".");
	if (fileFormat.indexOf("xls")>-1||fileFormat.indexOf("xlsx")>-1) {
		return true;
	}else{
		return false;
	}
}

function check(){
	var file = $("#excelFile").val();
	if (file==""||file==null) {
		alert("파일을 선택해주세요");
		return false;
	}else if (!checkFileType(file)) {
		alert("엑셀 파일만 업로드 가능합니다.");
		return false;
	}
	
	if (confirm("업로드 하시겠습니까?")) {
		var options = {
				success : function(data){
					alert("모든데이터가 업로드 되었습니다.");
					opener.location.reload();
				},
				processData : false,
				contentType: false,
				type:"POST"
		};
		$("#excelUpload").ajaxSubmit(options);
		
	}
}

</script>