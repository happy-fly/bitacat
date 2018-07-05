package com.kgh.card.bean;

import com.kgh.card.userinterface.Play;

/**
 * 玩家
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
			throw new RuntimeException("初始化玩家失败，必须要实现 Play 接口");
		} catch (Exception e) {
			throw new RuntimeException("初始化玩家失败 " + className, e);
		}
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", className=" + className + ", play=" + play + "]";
	}
	
	
}
