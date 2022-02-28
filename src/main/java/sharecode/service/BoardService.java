package sharecode.service;

import java.util.List;
import java.util.Map;

import sharecode.vo.PostVO;

public interface BoardService {
	public List<PostVO> boardAll(Map<String, Object> map) throws Exception;

	public int countArticle(Map<String, Object> map);
}
