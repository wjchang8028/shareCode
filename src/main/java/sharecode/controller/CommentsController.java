package sharecode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sharecode.service.CommentsService;
import sharecode.vo.CommentsVO;

@Controller
public class CommentsController {

	@Autowired
	CommentsService service;

	//댓글 작성
	@RequestMapping(value="commentsReg.do")
	public String commentsInsert(CommentsVO vo) {
		service.commentsInsert(vo);

		return "redirect:/commentsList.do?post_no="+vo.getPost_no();
	}
	
	// 댓글 리스트 출력 
	@RequestMapping(value = "commentsList.do")
	@ResponseBody
	public List<CommentsVO> commentsList(int post_no) {
		return service.commentsList(post_no);
	}
	
	//댓글 삭제
	@RequestMapping(value="commentsDelete.do")
	public String commentsDelete(int com_no,int post_no) {
		service.commentsDelete(com_no);
		
		return "redirect:/commentsList.do?post_no="+post_no;
	}
	
	//댓글 수정
	@RequestMapping(value="commentsModify.do")
	public String commentsUpdate(CommentsVO vo) {
		service.commentsUpdate(vo);
		
		return "redirect:/commentsList.do?post_no="+vo.getPost_no();
	}
}
