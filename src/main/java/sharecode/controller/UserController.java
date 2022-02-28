package sharecode.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sharecode.dao.UserDao;
import sharecode.vo.UserVO;

@Controller
public class UserController {

	@Autowired
	private UserDao dao;

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value = "/shareCode/signup.do")
	public String userSignUpAction(UserVO vo) {
		System.out.println("컨트롤러 실행");
		dao.signupUser(vo);
		return "redirect:/shareCode/list.do";
	}

	/* 이메일 인증 */
	@RequestMapping(value = "/shareCode/mailCheck.do", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {

		/* 뷰(View)로부터 넘어온 데이터 확인 */
		System.out.println("이메일데이터전송확인");
		System.out.println("인증번호 : " + email);

		/* 인증번호(난수) 생성 */
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		System.out.println("인증번호 : " + checkNum);

		/* 이메일 보내기 */
		String setForm = "wjchang8028@naver.com";
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content = "홈페이지를 방문해주셔서 감사합니다." + "<br><br>" + "인증 번호는 " + checkNum + "입니다." + "<br>"
				+ "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setForm);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}

		String num = Integer.toString(checkNum);

		return num;

	}

	/* 아이디 중복 체크 */
	@RequestMapping(value = "/shareCode/idCheck.do", method = RequestMethod.GET) // 아이디 중복체크
	@ResponseBody
	public String userIdCheckAction(String user_id) {
		System.out.println("아이디체크 실행");
		System.out.println("아이디? : " +user_id);
		int dbid = dao.idCheck(user_id);
		System.out.println("ㅇㅇㅇㅇ : "+dbid);
		if (dbid != 0) {
			return "1";
		}
		return "0";
	}

}
