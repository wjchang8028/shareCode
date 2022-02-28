<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="functions" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShareCode</title>
<link rel="stylesheet" href="css/writing.css" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	
	<div class="main-content">
		<div class="write-content">
			<div class="write-title">
				<h2>글 수정</h2>
			</div>
			
			<form method="post" action="postModify.do?post_no=${postInfo.post_no}">
				<input hidden="hidden" value="1" name="user_no"/>
				<div class="form-row">
					<span class="title writing">카테고리</span>
					<select name="post_category" id="post_category">
						<option value="Java">Java</option>
						<option value="Python">Python</option>
						<option value="C++">C++</option>
						<option value="C#">C#</option>
					</select>
				</div>
				
				<div class="form-row">
					<span class="title writing">제&emsp;&ensp;&nbsp;목</span> 
					<input type="text" name="post_title2" class="write" value="<c:out value='${postInfo.post_title}' escapeXml="false"/>"/>
					<span style=display:none><input type="text" name="post_title"/></span>
				</div>
				
				<div class="form-row">
					<span class="title writing title-content">내&emsp;&ensp;&nbsp;용</span>
					<textarea name="post_content2" class="write"><c:out value="${postInfo.post_content}" escapeXml="false"/></textarea>
					<span style=display:none><textarea name="post_content"></textarea></span>
				</div>
				
			</form>

			<button class="write-btn" type="button" onclick="location.href='postInfo.do?post_no=${postInfo.post_no}'">취소</button>
			<button class="write-btn" type="button" id="btn-submit">수정</button>
		</div>
	</div>
	
<script type="text/javascript">
	$("#post_category").val("${postInfo.post_category}").prop("selected", true);

	
	$("button#btn-submit").click(function(){ //확인 버튼 클릭 시
		var title=$("input[name='post_title2']").val();
		var content=$("textarea[name='post_content2']").val();
		
		if(title.replace(/\s/g,'').length==0){
			alert('제목을 입력하세요')
			return false;
		}
		
		if(content.replace(/\s/g,'').length==0){
			alert('내용을 입력하세요')
			return false;
		}
		 
		title = escapeHtml(title);
		content = escapeHtml(content);
		
		$("input[name='post_title']").val(title);
		$("textarea[name='post_content']").val(content);

		$("form").submit();
	});
	
	//escape 처리
	var entityMap = {
		'&' : '&amp;',
		'<': '&lt;',
		'>' : '&gt;',
		'"' : '&quot;',
		"'" : '&#39;',
		'/' : '&#x2F;',
		'`' : '&#x60;',
		'=' : '&#x3D;'
	};
	
	function escapeHtml(string) {
		return String(string).replace(/[&<>"'`=\/]/g, function(s) {
			return entityMap[s];
		});
	}
	
	
</script>
</body>


</html>