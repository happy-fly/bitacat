package com.kgh.card.example;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.kgh.card.bean.Card;
import com.kgh.card.constant.Code;
import com.kgh.card.userinterface.Play;

public class Kgh implements Play {
	
	private static final Logger logger = LoggerFactory.getLogger(Kgh.class);
	
	private List<Card> myCards;
	
	private List<Card> currentSend;

	@Override
	public void init(List<Card> cards) {
		this.myCards = cards;
		logger.debug("接收到的牌为：" + myCards);
	}

	@Override
	public List<Card> send(String name, List<Card> previous) {
		
		// 错误的情况处理
		if(myCards.size() <= 0) {
			return null;
		}
		
		// 第一次发牌
		if(previous == null || previous.size() == 0) {
			currentSend = Lists.newArrayList(myCards.get(0));
			return currentSend;
		}
		
		int n = previous.size();
		if(n == 1) {
			Card card = previous.get(0);
			Card m = getGreater(card);
			currentSend = Lists.newArrayList(m);
			return currentSend;
		}
		return null;
	}

	/**
	 * 从集合中获取一个比指定的card大的牌
	 * 
	 * @param card
	 * @return
	 */
	private Card getGreater(Card card) {

		if(card == null) {
			card = new Card(null, 1, -1);
		}
		for(Card c : myCards) {
			int n = c.getPosition();
			if(n > card.getPosition()) {
				return c;
			}
		}
		return null;
	}

	@Override
	public void succeed(int code, String msg) {
		if(Code.SUCCESS == code) {
			myCards.removeAll(currentSend);
		}
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
