package business.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import business.entity.Business;
import business.entity.Category;
import business.entity.Product;
import business.entity.Promotion;
import business.service.BusinessService;
import business.service.impl.BusinessServiceImpl;
import business.utils.UploadUtils;

public class EditProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			// ����map ����ǰ̨���ݵ�����
			HashMap<String, Object> map = new HashMap<>();
			// ���������ļ���
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// ���������ϴ�����
			ServletFileUpload upload = new ServletFileUpload(factory);
			// ����request
			List<FileItem> list = upload.parseRequest(request);
			// ��������
			for (FileItem fi : list) {
				// �ж��Ƿ�����ͨ���ϴ����
				if (fi.isFormField()) {
					// ��ͨ�ϴ����
					map.put(fi.getFieldName(), fi.getString("utf-8"));
				} else {
					// �ļ��ϴ����
					// ��ȡ�ļ�����
					String name = fi.getName();
					// ��ȡ�ļ�����ʵ����
					String realName = UploadUtils.getRealName(name);
					// ��ȡ�ļ����������
					String uuidName = UploadUtils.getUUIDName(realName);
					// ��ȡ�ļ��Ĵ��·��
					String path = this.getServletContext().getRealPath(
							"/user/image");
					// ��ȡ�ļ���
					InputStream is = fi.getInputStream();
					// ����ͼƬ
					FileOutputStream os = new FileOutputStream(new File(path,
							uuidName));
					IOUtils.copy(is, os);
					os.close();
					is.close();

					// ɾ����ʱ�ļ�
					fi.delete();

					// ��map�������ļ���·��
					map.put(fi.getFieldName(), "/user/image/" + uuidName);

				}
			}

			// 1.��װ����
			Product p = new Product();
			BeanUtils.populate(p, map);
			// 1.1 ��Ʒid
			p.setPid((String) map.get("pid"));

			// 1.3 ��װ cateogry
			Category c = new Category();
			c.setCid((String) map.get("cid"));
			p.setCategory(c);
			// 1.4 ��װpromotion
			Promotion pro = new Promotion();
			pro.setPnid((String) map.get("pnid"));
			p.setPromotion(pro);
			// 1.5 ��װbusiness
			Business b = new Business();
			b.setBid((String) map.get("bid"));
			p.setBusiness(b);

			// 2.����service������
			BusinessService businessService = new BusinessServiceImpl();
			businessService.update(p);

			// 3.ҳ���ض���
			response.sendRedirect(request.getContextPath()
					+ "/Business_Pro?method=findAllByBid");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "�޸�ʧ��");
			request.getRequestDispatcher("/business/jsp/msg.jsp").forward(
					request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
