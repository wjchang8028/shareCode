package sharecode.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sharecode.service.BoardService;
import sharecode.vo.PagingVO;
import sharecode.vo.PostVO;

@Controller
public class BoardController {

	@Inject
	BoardService boardService;


	@RequestMapping("shareCode/board.do")
	public ModelAndView board(@RequestParam(defaultValue = "all") String searchOption,
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam(value = "page", defaultValue = "1") int page) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		
		if (searchOption.equals("C%2B%2B")) { //jsp -> 자바 변환과정중 특수기호를 인식 못하는 문제 때문에 작성
			searchOption = "C++";
		}else if (searchOption.equals("C#")) {
			searchOption = "C%23";
		}
		
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		
		int count = boardService.countArticle(map);

		ModelAndView mav = new ModelAndView();

		PagingVO vo = new PagingVO(page, count);
		

		map.put("page", page);
		map.put("totalPage", vo.getTotalPage());
		map.put("start", vo.getStartList());
		map.put("end", vo.getEndList());
		map.put("startPage", vo.getStartPage());
		map.put("endPage", vo.getEndPage());
		map.put("searchOption", searchOption);
		List<PostVO> board = boardService.boardAll(map);
//		mav.addObject(vo);

		if (count != 0) {
			mav.setViewName("shareCode/list-select");
		} else {
			mav.addObject("msg","검색 결과가 없습니다.");
			mav.addObject("url","list.do");
			mav.setViewName("shareCode/select-alert");
		}
	
		if (searchOption.equals("C++")) { //jsp -> 자바 변환과정중 특수기호를 인식 못하는 문제 때문에 작성
			searchOption = "C%2B%2B";
		}else if (searchOption.equals("C#")) {
			searchOption = "C%23";
		}
		map.put("searchOption", searchOption);
		
		map.put("count", count);
		mav.addObject("map", map);
		map.put("board", board);
		System.out.println(board);
		return mav;

	}
}
