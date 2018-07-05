package com.kgh.card.userinterface;

import java.util.List;

import com.kgh.card.bean.Card;

public interface Play {

	void init(List<Card> cards);
	
	List<Card> send(List<Card> previous);
	
	void succeed(int code, String msg);
	
	void watch(List<Card> cards, String name);
	
	String say();
	
}
