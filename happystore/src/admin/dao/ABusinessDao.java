package admin.dao;

public interface ABusinessDao {

	// ����
	public void changeaddStar(String bid) throws Exception;

	// ����
	public void changejianStar(String bid) throws Exception;

	public void stopBusiness(String bid) throws Exception;

	//����û�
	public void openBusiness(String bid) throws Exception;

	//�������ע��
	public void okbusiness(String bid) throws Exception;
	
}
