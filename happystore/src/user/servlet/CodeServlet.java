package user.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ʹ��javaͼ�ν��漼������һ��ͼƬ

		int charNum = 4;
		int width = 30 * 4;
		int height = 30;

		// 1. ����һ���ڴ�ͼƬ
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 2.��û�ͼ����
		Graphics graphics = bufferedImage.getGraphics();

		// 3�����Ʊ�����ɫ
		graphics.setColor(Color.YELLOW);
		graphics.fillRect(0, 0, width, height);

		// 4������ͼƬ�߿�
		graphics.setColor(Color.BLUE);
		graphics.drawRect(0, 0, width - 1, height - 1);

		// 5�������֤������
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("����", Font.BOLD, 20));

		// ������4���ַ�
		Graphics2D graphics2d = (Graphics2D) graphics;
		String s = "ABCDEFGHGKLMNPQRSTUVWXYZ23456789";
		Random random = new Random();
		// session��Ҫ�õ�
		String msg = "";
		int x = 5;
		for (int i = 0; i < 4; i++) {
			int index = random.nextInt(32);
			String content = String.valueOf(s.charAt(index));
			msg += content;
			double theta = random.nextInt(45) * Math.PI / 180;
			// ������Ť��
			graphics2d.rotate(theta, x, 18);
			graphics2d.drawString(content, x, 18);
			graphics2d.rotate(-theta, x, 18);
			x += 30;
		}

		// 6�����Ƹ�����
		graphics.setColor(Color.GRAY);
		for (int i = 0; i < 5; i++) {
			int x1 = random.nextInt(width);
			int x2 = random.nextInt(width);

			int y1 = random.nextInt(height);
			int y2 = random.nextInt(height);
			graphics.drawLine(x1, y1, x2, y2);
		}

		// 
		System.out.println("msg=" + msg);
		
		// �ͷ���Դ
		graphics.dispose();

		// ͼƬ��� ImageIO
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}