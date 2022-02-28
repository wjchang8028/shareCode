package sharecode.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import sharecode.vo.CommentsVO;
@Repository
public class CommentsDaoImpl implements CommentsDao{


	@Inject
	protected SqlSessionTemplate sqlSession;
	

	@Override
	public List<CommentsVO> selectCommentsList(int post_no) { //댓글 리스트 출력
		return sqlSession.selectList("selectCommentsList",post_no);
	}
	
	@Override
	public void insertCommentsInfo(CommentsVO vo) { //댓글 작성 
		sqlSession.insert("insertCommentsInfo",vo);
	}

	@Override
	public void deleteComments(int com_no) { //댓글 삭제 
		sqlSession.update("deleteComments",com_no);
	}

	@Override
	public int getComSequence() { //댓글 넘버
		return sqlSession.selectOne("getComSequence");
	}

	@Override
	public void updateComments(CommentsVO vo) { //댓글 수정
		sqlSession.update("updateComments",vo);
	}
	
	@Override
	public void deletePostComments(int post_no) { //글 삭제시 댓글 삭제 
		sqlSession.delete("deletePostComments",post_no);
	}

}
