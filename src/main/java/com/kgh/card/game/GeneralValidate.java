package com.kgh.card.game;

import java.util.List;

import com.kgh.card.bean.Card;
import com.kgh.card.bean.Resp;
import com.kgh.card.constant.Code;

public class GeneralValidate implements Validate {

	@Override
	public Resp validate(List<Card> current, List<Card> previous) {
		Resp resp = new Resp(Code.SUCCESS);
		return resp;
	}

	@Override
	public Resp players(int num) {
		Resp resp = new Resp(Code.SUCCESS);
		return resp;
	}

}
