package sharecode.service;



import java.util.HashMap;
import java.util.List;

import sharecode.vo.PostVO;

public interface PostService {
	public void postInsertAction(PostVO vo);
	public PostVO postInfoSelect(int post_no);
	public void postHitUpdate(int post_no);
	public void postInfoUpdate(PostVO vo);
	public List<PostVO> listAction(HashMap<String, Object> map);
	public List<PostVO> alllistAction(HashMap<String, Object> map);
	public void postDelete(int post_no);
	public Integer listCount(String category);
	
}
