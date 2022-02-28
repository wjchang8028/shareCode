package sharecode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sharecode.dao.CommentsDao;
import sharecode.vo.CommentsVO;
@Service
public class CommentsServiceImpl implements CommentsService{

	@Autowired
	CommentsDao dao;
	
	@Override
	public List<CommentsVO> commentsList(int post_no) {
		return dao.selectCommentsList(post_no);
	}

	@Override
	public void commentsInsert(CommentsVO vo) {
		int comNo=dao.getComSequence();
		vo.setCom_no(comNo);
		
		if (vo.getCom_pnum()==0) {
			vo.setCom_pnum(comNo);
		}

		dao.insertCommentsInfo(vo);
	}

	@Override
	public void commentsDelete(int com_no) {
		dao.deleteComments(com_no);
	}

	@Override
	public void commentsUpdate(CommentsVO vo) {
		dao.updateComments(vo);
	}

}
