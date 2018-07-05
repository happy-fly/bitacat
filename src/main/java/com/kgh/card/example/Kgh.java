package com.kgh.card.example;

import java.util.List;

import com.kgh.card.bean.Card;
import com.kgh.card.userinterface.Play;

public class Kgh implements Play {

	@Override
	public void init(List<Card> cards) {
		System.out.println("init ...");
	}

	@Override
	public List<Card> send(List<Card> previous) {
		System.out.println("send...");
		return null;
	}

	@Override
	public void succeed(int code, String msg) {
		System.out.println("succeed .. " + code);
	}

	@Override
	public void watch(List<Card> cards, String name) {
		
	}

	@Override
	public String say() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void receive(String msg) {
		// TODO Auto-generated method stub
		
	}

}
