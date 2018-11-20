package com.kgh.card.core.bean;

public class RuleConfig {

	private int cardNum;

	public int getCardNum() {
		return cardNum;
	}

	public RuleConfig setCardNum(int cardNum) {
		if(cardNum <= 0) {
			this.cardNum = 1;
		} else {
			this.cardNum = cardNum;
		}
		return this;
	}

}
