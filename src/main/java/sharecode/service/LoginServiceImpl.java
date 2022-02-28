package sharecode.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import sharecode.dao.LoginDAO;
import sharecode.vo.LoginVO;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Inject
	LoginDAO loginDao;
	
	@Override
	public LoginVO login(LoginVO vo) throws Exception {
		
		return loginDao.login(vo);	
	}
	
}
