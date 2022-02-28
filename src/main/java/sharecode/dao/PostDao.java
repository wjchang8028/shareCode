package sharecode.dao;


import java.util.HashMap;
import java.util.List;

import sharecode.vo.PostVO;

public interface PostDao {
	public void insertPost(PostVO vo);
	public PostVO selectPostInfo(int post_no);
	public void updatePostHit(int post_no);
	public void updatePostInfo(PostVO vo);
	public List<PostVO> selectLangCategory(HashMap<String, Object> map);
	public List<PostVO> selectLangAllCategory(HashMap<String, Object> map);
	public Integer listCount(String category);
	public void deletePost(int post_no);
	
}
