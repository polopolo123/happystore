package user.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class UserServlet implements BaseServlet {

	/**
	 * ��ת�� ע��ҳ��
	 */
	public String registUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
	}

	/**
	 * �û�ע��
	 */
	public String regist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1.��װ����
		User user = new User();

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
		request.setAttribute("msg", "�û�ע���ѳɹ�,����<a href='www.baidu.com'>��½</a>");

		return "/jsp/msg.jsp";
	}

	/**
	 * ��ת����¼ҳ��
	 */
	public String loginUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}

	/**
	 * ��¼
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 1.��ȡ�û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = MD5Utils.md5(password);

		// 2.����serive��ɵ�¼���� ����user
		UserService s = new UserServiceImpl();
		User user = s.login(username, password);

		// 3.�ж��û�
		if (user == null) {
			// �û������벻ƥ��
			request.setAttribute("msg", "�˺����벻ƥ��,������<a href='www.baidu.com'>��½</a>");
			return;
		} else {
			// �����ж��û���״̬�Ƿ񼤻�
			if (Constant.USER_IS_ACTIVE != user.getState()) {
				request.setAttribute("msg", "�û�δ����");
				return "/jsp/login.jsp";
			}
		}

		// 4.��user����session�� �ض���
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/");// /store

		return null;
	}

	public String logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// �ɵ�session
		request.getSession().invalidate();

		// �ض���
		response.sendRedirect(request.getContextPath());

		// �����Զ���¼

		return null;
	}
}
