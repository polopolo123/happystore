package admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ����ʵ��
 */
public class Business implements Serializable{

	private String bid;		// id
	private String bname;	// ��������
	private String address;	// ���̵�ַ
	private Date createdate;  // ���̴���ʱ��
	
	private String buserpwd; // �������� ��ͨ����������¼��
	
	private String busername; // ��������������
	private String telephone; // ���������ߵ绰
	private String email; 	  // ����������email
	private String sex;	      // �����������Ա�
	
	private Integer stars;	   // ���̵��Ǽ���1-5�ǣ�
	private Integer state = 0; // ���̵�״̬ 
	// 0 ����� 1 ���� 2 .��
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getBuserpwd() {
		return buserpwd;
	}
	public void setBuserpwd(String buserpwd) {
		this.buserpwd = buserpwd;
	}
	public String getBusername() {
		return busername;
	}
	public void setBusername(String busername) {
		this.busername = busername;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getStars() {
		return stars;
	}
	public void setStars(Integer stars) {
		this.stars = stars;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
