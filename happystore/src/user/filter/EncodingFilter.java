package user.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ���봦��ͳһд������(servlet�в���Ҫ�ٴ������)
 */
public class EncodingFilter implements Filter {

	// ������ҵ������������Ĺ��õ�ҵ���߼�����
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		// ת��
		final HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// һ��������ҵ��
		request.setCharacterEncoding("UTF-8"); // POST�ύ��Ч
		response.setContentType("text/html;charset=UTF-8");

		/*
		 * ����GET�������룬����Ϊ��request.getParameter�����ڲ�û�н����ύ��ʽ�жϲ����� String name =
		 * request.getParameter("userName");
		 * 
		 * �������ָ���ӿڵ�ĳһ���������й�����չ������ʹ�ô���! ��request����(Ŀ�����)�������������
		 */
		HttpServletRequest proxy = (HttpServletRequest) Proxy.newProxyInstance(
				request.getClass().getClassLoader(), // ָ����ǰʹ�õ��ۼ�����
				new Class[] { HttpServletRequest.class }, // ��Ŀ�����ʵ�ֵĽӿ�����
				new InvocationHandler() { // �¼�������
					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						// ���巽������ֵ
						Object returnValue = null;
						// ��ȡ������
						String methodName = method.getName();
						// �жϣ���getParameter��������GET�ύ���Ĵ���
						if ("getParameter".equals(methodName)) {

							// ��ȡ��������ֵ�� <input type="text" name="userName">��
							String value = request.getParameter(args[0]
									.toString()); // ����Ŀ�����ķ���

							// ��ȡ�ύ��ʽ
							String methodSubmit = request.getMethod(); // ֱ�ӵ���Ŀ�����ķ���

							// �ж������GET�ύ����Ҫ�����ݽ��д��� (POST�ύ�Ѿ��������)
							if ("GET".equals(methodSubmit)) {
								if (value != null && !"".equals(value.trim())) {
									// ����GET����
									value = new String(value
											.getBytes("ISO8859-1"), "UTF-8");
								}
							}
							return value;
						} else {
							// ִ��request�������������
							returnValue = method.invoke(request, args);
						}

						return returnValue;
					}
				});

		// �������� (ִ����һ������������servlet)
		chain.doFilter(proxy, response); // ����������
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}
