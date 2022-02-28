package sharecode.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sharecode.dao.KakaoDAO;
import sharecode.service.KakaoService;

@Controller
public class KakaoController {
	
	@Autowired 
	KakaoDAO dao;
	
    @Autowired
    private KakaoService kakaoService;

    @RequestMapping("shareCode/home.do")
    public String home(@RequestParam(value = "code", required = false) String code, HttpServletRequest req) throws Exception{
        System.out.println("#########" + code);
        String access_Token = kakaoService.getAccessToken(code);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
        
        
        HttpSession session = req.getSession();
        HttpSession kakaomail = req.getSession();
        HttpSession kakaonic = req.getSession();
        HttpSession kakaoid = req.getSession();
        
        
        kakaoid.setAttribute("kakaoid", userInfo.get("email"));
        kakaonic.setAttribute("kakaonic", userInfo.get("nickname"));
        kakaomail.setAttribute("kakaomail", userInfo.get("id"));
        kakaoid.setMaxInactiveInterval(30*60);
        kakaonic.setMaxInactiveInterval(30*60);
        kakaomail.setMaxInactiveInterval(30*60);
        
        String id = userInfo.get("id").toString();
        String pw = userInfo.get("nickname").toString();
        String mail = userInfo.get("email").toString();
        
        String user_no = dao.initKakao(id, pw, mail);
        System.out.println(user_no);
        session.setAttribute("user_no",user_no );
        
        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###userInfo#### : " + userInfo.get("id"));
        System.out.println("###email#### : " + userInfo.get("email"));
        System.out.println("###nickname#### : " + userInfo.get("nickname"));
//        return "redirect:post.do";
        
		return "redirect:list.do";

    }
    
}