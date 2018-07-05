package com.kgh.card.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {

	/**
	 * 读取class path下的文件
	 * 
	 * @param classpath
	 * @return
	 */
	public static String readFile(String classpath) {
		StringBuffer content = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream(classpath)));

			String line = null;
			while ((line = reader.readLine()) != null) {
				content.append(line);
			}
			return content.toString();
		} catch (IOException e) {
			throw new RuntimeException("读取用户配置文件失败！", e);
		}
	}

}
