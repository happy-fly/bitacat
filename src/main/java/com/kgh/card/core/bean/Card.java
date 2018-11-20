package com.kgh.card.core.bean;

public class Card {

	private String name;
	private int type;
	private String imgUrl;
	private int position;

	public Card(String name, int type, String imgUrl, int position) {
		this.name = name;
		this.type = type;
		this.imgUrl = imgUrl;
		this.position = position;
	}

	public Card(String name, int type, int position) {
		this.name = name;
		this.type = type;
		this.position = position;
	}

	public Card() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Card [name=" + name + ", type=" + type + ", imgUrl=" + imgUrl + ", position=" + position + "]";
	}

}
