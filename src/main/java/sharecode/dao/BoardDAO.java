package sharecode.dao;

import java.util.List;
import java.util.Map;

import sharecode.vo.PostVO;

public interface BoardDAO {
	
	
	public int countArticle(Map<String, Object> map) ;


	public List<PostVO> boardAll(Map<String, Object> map);

}
