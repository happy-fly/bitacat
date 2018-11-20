package com.kgh.card.core.bean;

public class Message {

	private int code;
	private String msg;
	private Object data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

	public Message(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Message(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public Message(int code) {
		super();
		this.code = code;
	}

	public Message() {
		super();
	}

}
