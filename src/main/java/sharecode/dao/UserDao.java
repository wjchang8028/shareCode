package sharecode.dao;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import sharecode.vo.UserVO;

@Repository
@ComponentScan(value="sharecode.dao")
public class UserDao extends SqlSessionDaoSupport {
	
	@Resource(name = "sqlSessionTemplate")
	@Inject
	protected void initDao(SqlSessionTemplate st) throws Exception {
		this.setSqlSessionTemplate(st);
	}
	
	public void signupUser(UserVO vo) { //회원정보추가
		System.out.println("dao실행");
		this.getSqlSession().insert("signUpUser",vo);

	}
	
	public int idCheck(String id) { //아이디 중복 체크
		return this.getSqlSession().selectOne("idCheck",id);
	}
}
