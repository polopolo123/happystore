package business.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ͨ�õ�servlet
 */
public class BaseServlet extends HttpServlet {
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1.��ȡ���� ����������ߵ��������ʱ�� this��������������
			Class clazz = this.getClass();

			// 2.��ȡ����ķ���
			String m = request.getParameter("method");
			if (m == null) {
				m = "index";
			}

			// 3.��ȡ��������
			Method method = clazz.getMethod(m, HttpServletRequest.class,
					HttpServletResponse.class);

			// 4.�÷���ִ�� ����ֵΪ����ת����·��
			String s = (String) method.invoke(this, request, response);

			// 5.�ж�s�Ƿ�Ϊ��
			if (s != null) {
				request.getRequestDispatcher(s).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
