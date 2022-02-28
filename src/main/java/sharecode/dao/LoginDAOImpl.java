package sharecode.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import sharecode.vo.LoginVO;

@Repository //현재 클래스를 dao been으로 등록한다.
public class LoginDAOImpl implements LoginDAO {
	
	@Inject // sqlsession 의존성 주입
	SqlSession sqlSession;
	
	@Override
	public LoginVO login(LoginVO vo) throws Exception{
		return sqlSession.selectOne("login", vo);
	}
}
