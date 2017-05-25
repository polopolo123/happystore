package user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import user.entity.Category;
import user.service.CategoryService;
import user.service.impl.CategoryServiceImpl;

public class CategoryServlet extends BaseServlet {
	/**
	 * ��ѯ���еķ���
	 */
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1.����categoryservice ��ѯ���еķ��� ����ֵlist
		CategoryService cs = new CategoryServiceImpl();
		List<Category> clist = null;
		try {
			clist = cs.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 2.������ֵת��json��ʽ ���ص�ҳ����
		String json = JSONArray.fromObject(clist).toString();

		// 3.д��ȥ
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(json);

		return null;
	}
}
