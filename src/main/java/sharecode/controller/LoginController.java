package sharecode.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sharecode.service.LoginService;
import sharecode.vo.LoginVO;

@Controller // 컨트롤러 빈으로 등록한다.
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "shareCode/login.do", method = RequestMethod.POST)
	public String login(LoginVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		HttpSession session = req.getSession();
		LoginVO login = loginService.login(vo);
		System.out.println(login);
		if (login == null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
			return "redirect:list.do";
		} else {
			session.setAttribute("member", login);
			session.setMaxInactiveInterval(30*60);
//			return "redirect:post.do";
			return "redirect:list.do";
		}
	}

	@RequestMapping(value = "shareCode/logout.do", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) throws Exception {
		req.getSession().invalidate();
		req.getSession(true);
		
//		return "redirect:post.do";
		return "redirect:list.do";
	}

}
