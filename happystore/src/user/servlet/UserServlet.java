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
 * 和用户相关的servlet
 */
public class UserServlet implements BaseServlet {

	/**
	 * 跳转到 注册页面
	 */
	public String registUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
	}

	/**
	 * 用户注册
	 */
	public String regist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 1.封装数据
		User user = new User();

		// 注册自定义转化器
		ConvertUtils.register(new MyConventer(), Date.class);
		BeanUtils.populate(user, request.getParameterMap());

		// 设置用户id
		user.setUid(UUID.randomUUID().toString().replace("-", "").toUpperCase());

		// 加密密码
		user.setPassword(MD5Utils.md5(user.getPassword()));

		// 调用service完成注册
		UserService s = new UserServiceImpl();
		s.regist(user);

		// 页面请求转发
		request.setAttribute("msg", "用户注册已成功,请点击<a href='www.baidu.com'>登陆</a>");

		return "/jsp/msg.jsp";
	}

	/**
	 * 跳转到登录页面
	 */
	public String loginUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 1.获取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = MD5Utils.md5(password);

		// 2.调用serive完成登录操作 返回user
		UserService s = new UserServiceImpl();
		User user = s.login(username, password);

		// 3.判断用户
		if (user == null) {
			// 用户名密码不匹配
			request.setAttribute("msg", "账号密码不匹配,请重新<a href='www.baidu.com'>登陆</a>");
			return;
		} else {
			// 继续判断用户的状态是否激活
			if (Constant.USER_IS_ACTIVE != user.getState()) {
				request.setAttribute("msg", "用户未激活");
				return "/jsp/login.jsp";
			}
		}

		// 4.将user放入session中 重定向
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/");// /store

		return null;
	}

	public String logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 干掉session
		request.getSession().invalidate();

		// 重定向
		response.sendRedirect(request.getContextPath());

		// 处理自动登录

		return null;
	}
}
