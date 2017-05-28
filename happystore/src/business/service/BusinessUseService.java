package business.service;

import business.entity.Business;

public interface BusinessUseService {
	
	// �޸�����
	public Business updatePwd(String bid,String pwd) throws Exception;
	
	// ��½
	public Business login(String bname,String pwd) throws Exception;
	
	// ע��
	public void regist(Business business) throws Exception;
	
	// ����Ƿ�ע��
	public boolean checkBusiness(String username) throws Exception;
	
	// �޸Ļ�����Ϣ
	public Business updateBusiness(Business business) throws Exception;
	
}
