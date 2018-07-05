package com.kgh.card.bean;

import com.kgh.card.userinterface.Play;

/**
 * �����Ϣ
 * 
 * @author lenovo
 *
 */
public class Player {

	private String name;
	private String className;
	private Play play;

	public Player(String name, String className) {
		this.name = name;
		this.className = className;
		this.play = getInstance();
	}

	public Player(String className) {
		this(className,className.substring(className.lastIndexOf(".")));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public Play getPlay() {
		return this.play;
	}

	public Play getInstance()  {
		try {
			Object obj = Class.forName(className).newInstance();
			if (obj != null && obj instanceof Play) {
				return (Play) obj;
			}
			throw new RuntimeException("ʵ�������ʧ�ܣ���ʵ�� Play �ӿڣ�");
		} catch (Exception e) {
			throw new RuntimeException("ʵ�������ʧ��  " + className, e);
		}
	}
}
