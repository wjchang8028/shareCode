package sharecode.service;

import java.util.List;

import sharecode.vo.CommentsVO;

public interface CommentsService {
	public List<CommentsVO> commentsList(int post_no);
	public void commentsInsert(CommentsVO vo);
	public void commentsDelete(int com_no);
	public void commentsUpdate(CommentsVO vo);
}
