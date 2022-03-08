<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
window.onload=function(){
  	MemberPictureThumb($('#pictureView')[0],'${member.picture}',
  			'<%=request.getContextPath()%>');
}  

function  changePicture_go(){
	//alert("click picture btn");
	var picture = $('input#inputFile')[0];
	
	var fileFormat = picture.value.substr(picture.value.lastIndexOf(".")+1).toUpperCase();
	
	//이미지 확장자 jpg 확인
	if(!(fileFormat=="JPG" || fileFormat=="JPEG")){
		alert("이미지는 jpg 형식만 가능합니다.");
		return;
	} 
	//이미지 파일 용량 체크
	if(picture.files[0].size>1024*1024*1){
		alert("사진 용량은 1MB 이하만 가능합니다.");
		return;
	};

	document.getElementById('inputFileName').value=picture.files[0].name;
	
	if (picture.files && picture.files[0]) {
		 var reader = new FileReader();
		 
		 reader.onload = function (e) {
	        	//이미지 미리보기	        	
	        	$('div#pictureView')
	        	.css({'background-image':'url('+e.target.result+')',
					  'background-position':'center',
					  'background-size':'cover',
					  'background-repeat':'no-repeat'
	        		});
	        }
	        
	       reader.readAsDataURL(picture.files[0]);
	} 
	
	// 이미지 변경 확인
	$('input[name="uploadPicture"]').val(picture.files[0].name);
	
}

function modify_go(){
	var form=$('form[role="form"]');	
	form.submit();
	console.log(form);
} 


/* function modify_go(){
	
	var id 	   		   = $('input[name="id"]').val($('.id').val());
	var name 	   	   = $('input[name="name"]').val($('.name').val());
	var birth          = $('input[name="birth"]').val($('.birth').val());
	var phone    	   = $('input[name="phone"]').val($('.phone').val());
	var email  	 	   = $('input[name="email"]').val($('.email').val());
	var address  	   = $('input[name="address"]').val($('.address').val());
	var detailAddress  = $('input[name="detailAddress"]').val($('.detailAddress').val());
	
	var form = $("#modifyData").serialize();
	
	var data = {"id":$('.id').val(), "name":$('.name').val(), "birth":$('.birth').val(),  "phone":$('.phone').val(),  "email":$('.email').val(),  "address":$('.address').val(),  "detailAddress":$('.detailAddress').val()};
	console.log(data);
	
	$.ajax({
		type : "post",
		contentType : 'application/json; charset=utf-8',
		url : "modify.do",
 		data : JSON.stringify(data),
 		dataType : "json",
		success: function(data){
			alert("회원정보가 수정되었습니다.");
			window.opener.parent.location.reload(true);
			window.close();
		},
		error:function(error){
			alert("에러가 발생했습니다. ");
		}
	})

} */
</script>  





