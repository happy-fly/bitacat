package com.kgh.card.core.rule;

import java.util.List;

import com.kgh.card.core.bean.Card;
import com.kgh.card.core.bean.Message;
import com.kgh.card.core.constant.Code;

/**
 * 最基础玩法，允许随意发牌
 * 
 * @author 孔冠华
 *
 */
public class GeneralValidate implements Validate {

	@Override
	public Message validate(List<Card> current, List<Card> previous) {
		Message resp = new Message(Code.SUCCESS);
		return resp;
	}

	@Override
	public Message players(int num) {
		Message resp = new Message(Code.SUCCESS);
		return resp;
	}

}
