package sharecode.dao;

import sharecode.vo.LoginVO;

public interface LoginDAO {
	public LoginVO login(LoginVO vo) throws Exception;
}
