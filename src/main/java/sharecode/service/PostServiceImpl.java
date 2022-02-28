package sharecode.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sharecode.dao.CommentsDao;
import sharecode.dao.PostDao;
import sharecode.vo.PostVO;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostDao dao;
	@Autowired
	CommentsDao comDao;

	@Override
	public void postInsertAction(PostVO vo) {
		dao.insertPost(vo);
	}

	@Override
	public PostVO postInfoSelect(int post_no) {
		return dao.selectPostInfo(post_no);
	}

	@Override
	public void postHitUpdate(int post_no) {
		dao.updatePostHit(post_no);
	}

	@Override
	public void postDelete(int post_no) {
		dao.deletePost(post_no);
		comDao.deletePostComments(post_no);
	}

	@Override
	public void postInfoUpdate(PostVO vo) {
		dao.updatePostInfo(vo);
	}


	@Override
	public List<PostVO> listAction(HashMap<String, Object>map) {
		return dao.selectLangCategory(map);
	}
	
	@Override
	public List<PostVO> alllistAction(HashMap<String, Object> map) {
		
		return dao.selectLangAllCategory(map);
	}

	@Override
	public Integer listCount(String category) {
		System.out.println("서비스임플 category : "+category);
		return dao.listCount(category);
		
	}
	
	

	

}
