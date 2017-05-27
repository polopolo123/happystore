package business.utils;

import java.util.UUID;

public class UploadUtils {

	/**
	 * ��ȡ�������
	 */
	public static String getUUIDName(String realName) {
		int index = realName.lastIndexOf(".");
		if (index == -1) {
			return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		} else {
			return UUID.randomUUID().toString().replace("-", "").toUpperCase()
					+ realName.substring(index);
		}
	}

	/**
	 * ��ȡ�ļ���ʵ����
	 */
	public static String getRealName(String name) {
		int index = name.lastIndexOf("\\");
		return name.substring(index + 1);
	}

	/**
	 * ��ȡ�ļ�Ŀ¼
	 */
	public static String getDir(String name) {
		int i = name.hashCode();
		String hex = Integer.toHexString(i);
		int j = hex.length();
		for (int k = 0; k < 8 - j; k++) {
			hex = "0" + hex;
		}
		return "/" + hex.charAt(0) + "/" + hex.charAt(1);
	}

}
