package admin.dao;

public interface ABusinessDao {

	// ÉýÐÇ
	public void changeaddStar(String bid) throws Exception;

	// ½µÐÇ
	public void changejianStar(String bid) throws Exception;

	public void stopBusiness(String bid) throws Exception;

	//½â½ûÓÃ»§
	public void openBusiness(String bid) throws Exception;

	//ÔÊÐíµêÆÌ×¢²á
	public void okbusiness(String bid) throws Exception;
	
}
