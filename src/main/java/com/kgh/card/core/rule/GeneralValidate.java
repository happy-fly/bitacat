package com.kgh.card.core.rule;

import java.util.List;

import com.kgh.card.core.bean.Card;
import com.kgh.card.core.bean.Resp;
import com.kgh.card.core.constant.Code;

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
