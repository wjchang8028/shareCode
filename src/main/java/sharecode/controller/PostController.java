package sharecode.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sharecode.service.PostService;
import sharecode.vo.PagingVO;
import sharecode.vo.PostVO;

@Controller
public class PostController {

	@Autowired
	PostService postService;

	@RequestMapping(value = "shareCode/post.do")
	public String postInsertAction(PostVO vo) {
		postService.postInsertAction(vo);

		return "redirect:/shareCode/list.do";
	}

	@RequestMapping(value = "shareCode/postInfo.do")
	public String postInfoSelect(int post_no, Model model) {
		postService.postHitUpdate(post_no);
		model.addAttribute("postInfo", postService.postInfoSelect(post_no));

		return "/shareCode/post-detail";
	}

	@RequestMapping(value = "shareCode/postModifyInfo.do")
	public String postModifyInfoSelect(int post_no, Model model) {
		model.addAttribute("postInfo", postService.postInfoSelect(post_no));

		return "/shareCode/post-modify";
	}

	@RequestMapping(value = "shareCode/postDelete.do")
	public String postDelete(int post_no) {
		postService.postDelete(post_no);

		return "redirect:/shareCode/list.do";
	}

	@RequestMapping(value = "shareCode/postModify.do")
	public String postInfoUpdate(PostVO vo, RedirectAttributes redirect) {
		postService.postInfoUpdate(vo);
		redirect.addAttribute("post_no", vo.getPost_no());

		return "redirect:/shareCode/postInfo.do";
	}

	@RequestMapping(value = "shareCode/list.do")
	public String mainlistAction(Model model, @RequestParam(value = "category", defaultValue = "all") String category,
			@RequestParam(value = "page", defaultValue = "1") int page) {

		System.out.println("첫카테고리 값 : " + category);
		HashMap<String, Object> map = new HashMap<String, Object>();

		PagingVO vo = new PagingVO(page, postService.listCount(category));
		System.out.println(postService.listCount(category)+"카운트");
		map.put("page", page);
		map.put("totalPage", vo.getTotalPage());
		map.put("start", vo.getStartList());
		map.put("end", vo.getEndList());
		map.put("startPage", vo.getStartPage());
		map.put("endPage", vo.getEndPage());
		map.put("category", category);

		if (category.equals("all")) { //카테고리가 all 일때 전체 리스트 출력
			model.addAttribute("selectLang", postService.alllistAction(map));
			model.addAttribute("pageList", map);
		} else { // 카테고리 항목이 정해져 있을 때는 해당 카테고리를 선택해 옴
			model.addAttribute("selectLang", postService.listAction(map));
			model.addAttribute("pageList", map);
			
			
		}
		
		if (category.equals("C++")) { //jsp -> 자바 변환과정중 특수기호를 인식 못하는 문제 때문에 작성
			category = "C%2B%2B";
		}else if (category.equals("C#")) {
			category = "C%23";
		}
		System.out.println("바뀐 카테고리값 : " + category);
		model.addAttribute("category", category);

		return "/shareCode/list";
	}

}