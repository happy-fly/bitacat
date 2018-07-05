package com.kgh.card.game;

import java.util.List;

import com.kgh.card.bean.Card;

public class GeneralValidate implements Validate {

	@Override
	public boolean validate(List<Card> current, List<Card> previous) {
		return false;
	}

}
