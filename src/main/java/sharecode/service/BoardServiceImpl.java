package sharecode.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import sharecode.dao.BoardDAO;
import sharecode.vo.PostVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	BoardDAO boardDAO;

	@Override
	public List<PostVO> boardAll(Map<String, Object> map) throws Exception {
		return boardDAO.boardAll(map);
	}

	@Override
	public int countArticle(Map<String, Object> map) {
		return boardDAO.countArticle(map);
	}

}
