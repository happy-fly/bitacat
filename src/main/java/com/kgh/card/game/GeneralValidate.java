package com.kgh.card.game;

import java.util.List;

import com.kgh.card.bean.Card;
import com.kgh.card.bean.Resp;

public class GeneralValidate implements Validate {

	@Override
	public Resp validate(List<Card> current, List<Card> previous) {
		return null;
	}

	@Override
	public Resp players(int num) {
		return null;
	}

}
