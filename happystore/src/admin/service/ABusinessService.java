package admin.service;

public interface ABusinessService {

	//�����Ǽ�
	public void changeaddStar(String bid) throws Exception;
	
	//�����Ǽ�
	public void changejianStar(String bid) throws Exception;

	//�����û�
	public void stopBusiness(String bid) throws Exception;

	//����û�
	public void openBusiness(String bid) throws Exception;

	//�������ע��
	public void okbusiness(String bid) throws Exception;
	
}
