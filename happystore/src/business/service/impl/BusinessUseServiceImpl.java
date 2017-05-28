package business.service.impl;

import business.dao.BusinessUseDao;
import business.dao.impl.BusinessUseDaoImpl;
import business.entity.Business;
import business.service.BusinessUseService;

public class BusinessUseServiceImpl implements BusinessUseService {
	// �޸�����
	public Business updatePwd(String bid, String pwd) throws Exception {
		BusinessUseDao bud = new BusinessUseDaoImpl();
		return bud.updatePwd(bid, pwd);
	}

	// ��½
	@Override
	public Business login(String bname, String pwd) throws Exception {
		BusinessUseDao bud = new BusinessUseDaoImpl();
		return bud.login(bname, pwd);
	}

	// ע��
	public void regist(Business business) throws Exception {
		BusinessUseDao bud = new BusinessUseDaoImpl();
		bud.regist(business);
	}

	// ����Ƿ�ע��
	@Override
	public boolean checkBusiness(String username) throws Exception {
		BusinessUseDao bud = new BusinessUseDaoImpl();
		return bud.checkBusiness(username);	
	}

	// �޸Ļ�����Ϣ
	@Override
	public Business updateBusiness(Business business) throws Exception {
		BusinessUseDao bud = new BusinessUseDaoImpl();
		return bud.updateBusiness(business);	
	}
	
	
}
