package sharecode.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import sharecode.vo.LoginVO;
import sharecode.vo.UserVO;

@Repository
public class KakaoDAO {
	
	@Inject
	SqlSession sqlSession;
	
	public String initKakao(String id, String pw, String mail) { //회원정보추가
		System.out.println("dao dao doa dao");
		String log = this.chKakao(id);
		UserVO vo=new UserVO();
		System.out.println("log   :   "+log);
		if(log==null) {
			vo.setUser_id(id);
			vo.setUser_pw(pw);
			vo.setUser_mail(mail);
			sqlSession.insert("insertKakao",vo);
		}

		return log;
	}
	
	public String chKakao(String id) {
		String A=sqlSession.selectOne("chKakao", id);
		System.out.println(A);
		return sqlSession.selectOne("chKakao", id);
	}

}
