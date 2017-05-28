package business.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.utils.MD5Utils;
import business.entity.Business;
import business.service.BusinessUseService;
import business.service.impl.BusinessUseServiceImpl;
public class BusinessServlet_a extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * �û��˺������޸�
	 */
	public String updatePwd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ͨ��session����ȡ�˻���bid
		HttpSession session = request.getSession();
		Business businessUse = (Business) session.getAttribute("Business");
		String bid = businessUse.getBid();

		// ����˻��ľ�����
		String oldpwd = request.getParameter("oldpwd");
		if(!MD5Utils.md5(oldpwd).equals(businessUse.getBuserpwd())) {
			request.setAttribute("msg", "�޸�ʱ���������!!");
			return "/business/jsp/msg.jsp";
		}
		
		// ����˻���������
		String newPwd = request.getParameter("newpwd");
		if(newPwd == null) {
			request.setAttribute("msg", "������Ϊ��!!");
			return "/business/jsp/msg.jsp";
		}
		// ��������
		String pwd = MD5Utils.md5(newPwd);

		// ����Service��ע���˺�
		BusinessUseService bus = new BusinessUseServiceImpl();
		Business newBusiness = null;
		try {
			// ��ȡ���µ�Business�����浽session��
			newBusiness = bus.updatePwd(bid, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// �ɵ�session
		session.invalidate();
		// �����û����浽session��
		request.getSession().setAttribute("Business", newBusiness);
		request.setAttribute("msg", "�����޸ĳɹ�!!!");
		return "/business/jsp/msg.jsp";
	}
	
	/**
	 * �˺�ע��
	 */
	public String register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��ȡ����
		String busername = request.getParameter("username");
		String email = request.getParameter("email");
		String bname = request.getParameter("name"); //����
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		String buserpwd = request.getParameter("password");
		
		// ��֤����֤
		String code = request.getParameter("code");
		String yzmsg = (String) request.getSession(false).getAttribute(
				"yzmsg");

		if (!code.equalsIgnoreCase(yzmsg)) {
			request.setAttribute("msg", "�ڽ��е��̹���ϵͳע��ʱ,��֤�����!!");
			return "/user/jsp/msg.jsp";
		}
		
		// ���ò���
		Business business = new Business();
		business.setAddress(address);
		business.setBid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		business.setBname(bname);
		business.setBusername(busername);
		business.setBuserpwd(MD5Utils.md5(buserpwd));
		business.setCreatedate(new Date());
		business.setEmail(email);
		// ��ʼʱ��state=0 �ύ��ע�� ��δ���� 
		business.setState(0);
		business.setTelephone(telephone);
		// Ĭ�ϣ����̴���ʱ�Ǽ�Ϊ3
		business.setStars(3);
		
		// ����service���ע��
		BusinessUseService bus = new BusinessUseServiceImpl();
		try {
			bus.regist(business);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		// ҳ������ת��
		request.setAttribute("msg",
				"����ע���ѳɹ�,�ȴ���Ӫ����׼������<a href='" + request.getContextPath()
						+ "/business/jsp/index.jsp'>��½</a>");

		return "/user/jsp/msg.jsp";
	}
	

	/**
	 * ��¼
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 1.��ȡ�û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String logincode = request.getParameter("yz");

		String loginmsg = (String) request.getSession(false).getAttribute(
				"yzmsg");

		if (!logincode.equalsIgnoreCase(loginmsg)) {
			request.setAttribute("msg", "�ڽ��е��̹���ϵͳ��½ʱ,��֤�����!!");
			return "/business/jsp/index.jsp";
		}

		password = MD5Utils.md5(password);

		// 2.����serive��ɵ�¼���� ����user
		BusinessUseService bus = new BusinessUseServiceImpl();
		Business business = bus.login(username, password);

		// 3.�ж��û�
		if (business == null) {
			// �˺����벻ƥ��
			request.setAttribute("msg", "�˺����벻ƥ��,�����µ�½");
			return "/business/jsp/index.jsp";
		} else {
			// �����ж��û���״̬�Ƿ��������ʹ��
			if (1 != business.getState()) {
				request.setAttribute("msg", "�˺Ų�������ʹ��,����δ����ע��,�򱻽��õ�,����ϵͳ����Ա��ϵ!!");
				return "/business/jsp/index.jsp";
			}
		}

		// 4.��user����session�� �ض���
		request.getSession().setAttribute("Business", business);
		response.sendRedirect(request.getContextPath() + "/business/jsp/home.jsp" );

		return null;
	}
	
	/**
	 * ajax �����˺��Ƿ��ظ�
	 */
	public String checkBusiness(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");

		String userName = null;
		userName = request.getParameter("username");
		BusinessUseService bus = new BusinessUseServiceImpl();
		try {
			if (bus.checkBusiness(userName)) {
				response.getWriter().write("no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// �˺���Ϣ�޸�
	public String updateBusiness(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ��ȡ����
		String busername = request.getParameter("busername");
		String email = request.getParameter("email");
//		String bname = request.getParameter("bname"); //����
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		
		// ���ò���
		Business business = (Business) request.getSession().getAttribute("Business");
		business.setAddress(address);
		business.setBusername(busername);
		business.setEmail(email);
		business.setTelephone(telephone);
		
		// ����service���ע��
		BusinessUseService bus = new BusinessUseServiceImpl();
		try {
			bus.updateBusiness(business);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		// �ɵ�session,�����°���ӵ�session��
		request.getSession().invalidate();
		request.getSession().setAttribute("Business", business);
		
		// ҳ������ת��
		request.setAttribute("msg","���̻�����Ϣ�޸ĳɹ�!!!");
		return "/business/jsp/msg.jsp";
	}
	
	
}
