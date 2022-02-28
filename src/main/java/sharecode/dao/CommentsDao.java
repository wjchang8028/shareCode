package sharecode.dao;

import java.util.List;

import sharecode.vo.CommentsVO;

public interface CommentsDao {
	public List<CommentsVO> selectCommentsList(int post_no);
	public void insertCommentsInfo(CommentsVO vo);
	public int getComSequence();
	public void deleteComments(int com_no);
	public void updateComments(CommentsVO vo);
	public void deletePostComments(int post_no);
}
