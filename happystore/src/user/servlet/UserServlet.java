package user.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import user.entity.User;
import user.other.MyConventer;
import user.service.UserService;
import user.service.impl.UserServiceImpl;
import user.utils.MD5Utils;

/**
 * ���û���ص�servlet
 */
public class UserServlet extends BaseServlet {

	/**
	 * ajax �����˺��Ƿ��ظ�
	 */
	public String checkUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");

		String userName = null;
		userName = request.getParameter("username");
		UserService userService = new UserServiceImpl();
		try {
			if (userService.checkUser(userName)) {
				response.getWriter().write("no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �û�ע��
	 */
	public String regist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1.��װ����
		User user = new User();

		String yzmcode = request.getParameter("yzmcode");

		String zcmsg = (String) request.getSession(false).getAttribute("yzmsg");
		if (!yzmcode.equalsIgnoreCase(zcmsg)) {
			request.setAttribute("msg", "��֤�����!!");
			return "/user/jsp/msg.jsp";
		}

		// ע���Զ���ת����
		ConvertUtils.register(new MyConventer(), Date.class);
		BeanUtils.populate(user, request.getParameterMap());

		// �����û�id
		user.setUid(UUID.randomUUID().toString().replace("-", "").toUpperCase());

		// ��������
		user.setPassword(MD5Utils.md5(user.getPassword()));

		// ����service���ע��
		UserService s = new UserServiceImpl();
		s.regist(user);

		// ҳ������ת��
		request.setAttribute("msg",
				"�û�ע���ѳɹ�,����<a href='" + request.getContextPath()
						+ "/user/jsp/login.jsp'>��½</a>");

		return "/user/jsp/msg.jsp";
	}

	/**
	 * ��ת����¼ҳ��
	 */
	public String loginUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return "/user/jsp/login.jsp";
	}

	/**
	 * ��¼
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 1.��ȡ�û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String logincode = request.getParameter("logincode");

		String loginmsg = (String) request.getSession(false).getAttribute(
				"yzmsg");

		if (!logincode.equalsIgnoreCase(loginmsg)) {
			request.setAttribute("msg", "��֤�����!!");
			return "/user/jsp/login.jsp";
		}

		password = MD5Utils.md5(password);

		// 2.����serive��ɵ�¼���� ����user
		UserService s = new UserServiceImpl();
		User user = s.login(username, password);

		// 3.�ж��û�
		if (user == null) {
			// �û������벻ƥ��
			request.setAttribute("msg", "�˺����벻ƥ��,�����µ�½");
			return "/user/jsp/login.jsp";
		} else {
			// �����ж��û���״̬�Ƿ��������ʹ��
			if (0 != user.getState()) {
				request.setAttribute("msg", "�˺Ų�������ʹ��,���ܱ�ע���򱻽���,����ϵͳ����Ա��ϵ!!");
				return "/user/jsp/msg.jsp";
			}
		}

		// 4.��user����session�� �ض���
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath());

		return null;
	}

	/**
	 * �û��ǳ�
	 */
	public String logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// �ɵ�session
		request.getSession().invalidate();

		// �ض���
		response.sendRedirect(request.getContextPath() + "/");

		return null;
	}

	/**
	 * �û��˺�ע��
	 */
	public String stopUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ͨ��session����ȡ�˻���uid
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String uid = user.getUid();

		// �ɵ�session
		session.invalidate();

		// ����Service��ע���˺�
		UserService s = new UserServiceImpl();
		try {
			s.cancel(uid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// �ض���
		response.sendRedirect(request.getContextPath() + "/");
		return null;
	}

	/**
	 * �û��˺Ż�����Ϣ�޸�
	 */
	public String updateUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ͨ��session����ȡ�˻���uid
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// ���ǰ̨ȡ�õ�����
		String email = request.getParameter("email");
		user.setEmail(email);
		String name = request.getParameter("name");
		user.setName(name);
		String sex = request.getParameter("sex");
		user.setSex(sex);
		String birthday = request.getParameter("birthday");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(birthday);
			user.setBirthday(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String telephone = request.getParameter("telephone");
		user.setTelephone(telephone);

		// ����Service��������Ϣ
		UserService s = new UserServiceImpl();
		User newUser = null;
		try {
			newUser = s.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// �ɵ�session,�����°��û���ӵ�session��
		session.invalidate();
		request.getSession().setAttribute("user", newUser);

		// �ض���
		response.sendRedirect(request.getContextPath() + "/");
		return null;
	}

	/**
	 * �û��˺������޸�
	 */
	public String updatePwd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ͨ��session����ȡ�˻���uid
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String uid = user.getUid();

		// ����˻�������
		String oldpwd = request.getParameter("oldpwd");
		if(!MD5Utils.md5(oldpwd).equals(user.getPassword())) {
			request.setAttribute("msg", "�޸�ʱ���������!!");
			return "/user/jsp/msg.jsp";
		}
		
		// ����˻�������
		String newPwd = request.getParameter("newpwd");
		if(newPwd == null) {
			request.setAttribute("msg", "������Ϊ��!!");
			return "/user/jsp/msg.jsp";
		}
		// ��������
		String pwd = MD5Utils.md5(newPwd);

		// ����Service��ע���˺�
		UserService s = new UserServiceImpl();
		User newUser = null;
		try {
			// ��ȡ���µ�User�����浽session��
			newUser = s.updatePwd(uid, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// �ɵ�session
		session.invalidate();
		// �����û����浽session��
		request.getSession().setAttribute("user", newUser);
		response.sendRedirect(request.getContextPath());

		return null;
	}

}
