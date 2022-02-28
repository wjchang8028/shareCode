<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="functions" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>shareCode</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script
	src="https://netdna.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="css/writing.css" type="text/css">
<link rel="stylesheet" href="css/comments.css" type="text/css">
</head>
<body>

	<jsp:include page="header.jsp" />

	<div class="main-content">
		<div class="write-content">
			<div class="post-title">
				<h2>
					<c:out value="${postInfo.post_title}" escapeXml="false"/>
				</h2>
			</div>


			<div class="form-row">
				<div class="section">
					<span class="title post">카테고리</span> ${postInfo.post_category}
				</div>
				<div class="section">
					<span class="title post">조회수</span> ${postInfo.post_hit}
				</div>
			</div>

			<div class="form-row">
				<div class="section">
					<span class="title post">작성자</span>
					<c:out value="${postInfo.user_id}"></c:out>
				</div>
				<div class="section">
					<span class="title post">작성일</span> ${postInfo.post_date}
				</div>
			</div>

			<div class="title-border"></div>
			

			<div class="form-row post-content">
				<%
				pageContext.setAttribute("CRCN", "\r\n");
				pageContext.setAttribute("CR", "\r");
				pageContext.setAttribute("CN", "\n");
				pageContext.setAttribute("BR", "<br/>");
				pageContext.setAttribute("SP", "&nbsp;");
				%> 
				<!-- jstl로 변환처리 -->
				<c:set var="cmt" value="${functions:replace(postInfo.post_content,CRCN,BR)}" />
				<c:set var="cmt" value="${functions:replace(cmt,CR,BR)}" />
				<c:set var="cmt" value="${functions:replace(cmt,CN,BR)}" />
				<c:set var="cmt" value="${functions:replace(cmt,' ',SP)}" />
				
				<!-- 화면에 출력하기 -->
				<c:out value="${cmt}" escapeXml="false"/> 
			</div>

			<c:catch>
				<c:choose>
					<c:when test="${postInfo.user_no eq member.user_no}">
						<button class="write-btn" type="button"
							onclick="location.href='postModifyInfo.do?post_no=${postInfo.post_no}'">수정</button>
						<button class="write-btn" type="button"
							onclick="location.href='postDelete.do?post_no=${postInfo.post_no}'">삭제</button>
					</c:when>
				</c:choose>
			</c:catch>

		</div>
	</div>

	<div class="main-reply">
		<div class="panel">
			<div class="panel-body">
				<c:choose>
					<c:when test="${member.user_no eq null}">
						<textarea class="form-control" name="com_content"
							placeholder="로그인이 필요합니다" disabled></textarea>
					</c:when>
					<c:otherwise>
						<form action="#" id="commentsForm" name="commentsForm"
							method="post">
							<input type="hidden" name="post_no" value="${postInfo.post_no}">
							<input type="hidden" name="user_no" value="${member.user_no}">
							<input type="hidden" name="com_job" value="0">
							<textarea class="form-control" name="com_content2" id="com_content2" placeholder="댓글을 작성하세요"></textarea>
							<span style="display: none"><textarea name="com_content"></textarea></span>
							<button type="button" class="write-btn reply-btn" id="commentsReg">등록</button>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<div class="panel">
			<div>
				<div id="commentsShow"></div>
				<!-- 댓글 구역 -->
			</div>
		</div>
	</div>


<script type="text/javascript">
	//현재 사용자 정보
	var nowUserNo=$("input[name=user_no]").val();
	
	//ajax로 바로 댓글 리스트 출력
	commentsAjax('/web/commentsList.do',{"post_no":${postInfo.post_no}},'json');
	
	
	//댓글 작성 완료
	$("button#commentsReg").click(function(){ 
		if(contentCheck('com_content2')==false){
			return;
		}
		commentsAjax('${pageContext.request.contextPath}/commentsReg.do',$("form#commentsForm").serialize(),'json');
	}); 
	
	
	//댓글 내용 확인
	function contentCheck(tmp){ 
		var content=$("textarea[name='"+tmp+"']").val();
		
		if(content.replace(/\s/g,'').length==0){
			alert('내용을 작성하세요');
			return false;
		}
		
		content = escapeHtml(content);
		$("textarea[name='com_content']").val(content);
	}
	
	
	//댓글 ajax 
	function commentsAjax(url,data,dataType){
		 $.ajax({
	          url:url,
	          type:'POST',
	          data:data,
	          dataType:dataType,              
	          success:function(v){
	        	 commentsListHtml(v);
	          },
	          error:function(e){
	             alert('error'+e);
	          }
	       });   
	}
	

	//댓글 Html
 	function commentsListHtml(v){
	    var temp="";
	    $.each(v,function(index,dom){
	 		var content=dom.com_content.replace(/(?:\r\n|\r|\n)/gm, '<br/>');
	 		content=content.replaceAll(' ','&nbsp;');
	 		
	 	  	temp+="<div class=\"comments-block\" id='commentsLoc"+dom.com_no+"'>";
	 	  
	 		if (dom.com_job==0){ //댓글(0) 답글(1) 여부 확인
	 			temp+="<div class=\"media-body\">";
	 		}else{
	 			temp+="<div class=\"media-body reply-block\">";
	 		}
	 		temp+="<div class=\"mar-btm\">";
	 		temp+="<div class=\"comment-writer\">"+dom.user_id+"</div>";
			temp+="<div class=\"text-muted text-sm\">"+dom.com_date+"</div>";
			temp+="</div>";
			temp+="<p>"+content+"</p>";
			temp+="<div class=\"pad-ver\">";
			
			if (dom.com_del==0){ //삭제된 댓글인지 확인
	  			temp+="<button type=\"button\" class=\"reply-write\"onclick=\"replyWriteForm("+dom.com_no+","+dom.com_pnum+",'"+dom.user_id+"');\">답글</button>";
	  			if (dom.user_no==nowUserNo){ //댓글 작성자와 현재 사용자의 user_no가 같을 경우
					temp+="<button type=\"button\" class=\"reply-write\" onclick=\"commentsDelete("+dom.com_no+");\">삭제</button>";
					temp+="<button type=\"button\" class=\"reply-write\" onclick=\"commentsModifyForm("+dom.com_no+")\">수정</button>";
					temp+="<span style=\"display: none\"><textarea id='modifyTextArea"+dom.com_no+"'>"+dom.com_content+"</textarea></span>";
		  		} 
			}
	 		temp+="</div></div></div>";
	 		temp+="<div id='replyShow"+dom.com_no+"'></div>";
	    });
	     $("div#commentsShow").html(temp);
	     $("textarea#com_content2").val('');
	}
   
	
	//답글 작성 칸 생성
	function replyWriteForm(comNo, pnumNo, userID){ 
		if(nowUserNo == null){
			alert('로그인 후 이용해주세요');
			return
		}
		replyWriteFormClose();
	
		var temp="";
		temp+=" <div class=\"panel-body comments-block\" id=\"replyArea\">";
		temp+="	<form action=\"#\" id=\"replyForm\" name=\"replyForm\" method=\"post\">";
		temp+="	<input type=\"hidden\" name=\"post_no\" value=\"${postInfo.post_no}\">";
		temp+="	<input type=\"hidden\" name=\"user_no\" value=\"${member.user_no}\">";
		temp+="	<input type=\"hidden\" name=\"com_pnum\" value=\""+pnumNo+"\">";
		temp+="	<input type=\"hidden\" name=\"com_job\" value=\"1\">";
		temp+=" <textarea class=\"form-control\" name=\"replyContent2\">@"+userID+" </textarea>";
		temp+=" <span style=display:none><textarea name=\"com_content\"></textarea></span>";
		temp+="	<button type=\"button\" class=\"write-btn reply-btn\" onclick=\"replyReg();\">등록</button>";
		temp+="	<button type=\"button\" class=\"write-btn reply-btn\" onclick=\"replyWriteFormClose();\">취소</button>";
		temp+=" </form></div>";
		
		$('div#replyShow'+comNo).html(temp);
	}	
	
	
	//답글 작성 완료
	function replyReg(){ 
		if(contentCheck('replyContent2')==false){
			return;
		}
		commentsAjax('${pageContext.request.contextPath}/commentsReg.do',$("form#replyForm").serialize(),'json');
	}
	
	
	//답글 작성 취소 시 답글 칸 삭제
	function replyWriteFormClose(){ 
		$('div#replyArea').remove();
	}

	
	//댓글 삭제
	function commentsDelete(comNo){ 
		commentsAjax('${pageContext.request.contextPath}/commentsDelete.do',{"com_no":comNo, "post_no":${postInfo.post_no}},'json');
	}
	
	   
	var beforeComNo=0; //사용자가 기존에 수정하고 있던 댓글 번호 저장
	//댓글 수정
	function commentsModifyForm(comNo){ 
		modifyFormClose(comNo); 
	
		var content=$("#modifyTextArea"+comNo).val();
		
		var temp="";
		temp+=" <div class=\"panel-body comments-block\" id=\"modifyForm\">";
		temp+="	<form action=\"#\" id=\"modifyForm\" name=\"modifyForm\" method=\"post\">";
		temp+="	<input type=\"hidden\" name=\"post_no\" value=\"${postInfo.post_no}\">";
		temp+="	<input type=\"hidden\" name=\"com_no\" value=\""+comNo+"\">";
		temp+=" <textarea class=\"form-control\" name=\"replyModifyContent2\" id=\"replyArea\">"+content.replaceAll("<br/>", "\r\n")+"</textarea>";
		temp+=" <span style=display:none><textarea name=\"com_content\"></textarea></span>"; 
		temp+="	<button type=\"button\" class=\"write-btn reply-btn\" onclick=\"commentsModify();\">수정</button>";
		temp+="	<button type=\"button\" class=\"write-btn reply-btn\" onclick=\"modifyFormClose();\">취소</button>";
		temp+=" </form></div>";
		
		$('div#replyShow'+comNo).append(temp);
		beforeComNo=comNo;
	}
	
	
	//댓글 수정 완료
	function commentsModify(){ 
		if(contentCheck('replyModifyContent2')==false){
			return;
		}
		commentsAjax('${pageContext.request.contextPath}/commentsModify.do',$("form#modifyForm").serialize(),'json');
	}
	
	
	//수정 취소 시 댓글 수정 칸 삭제
	function modifyFormClose(comNo){
		$('div#commentsLoc'+beforeComNo).show(); //그전에 수정중이던 댓글 다시 보이기
		$('div#replyShow'+beforeComNo).empty(); //그전에 수정칸 삭제 
		$('div#commentsLoc'+comNo).hide(); //현재 댓글 숨기기
	}
	
	
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