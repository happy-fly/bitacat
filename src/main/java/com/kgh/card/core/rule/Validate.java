package com.kgh.card.core.rule;

import java.util.List;

import com.kgh.card.core.bean.Card;
import com.kgh.card.core.bean.Message;

/**
 * 玩法规则校验
 * 
 * @author lenovo
 *
 */
public interface Validate {

	/**
	 * 人数校验
	 * 
	 * @param num
	 * @return
	 */
	Message players(int num);

	/**
	 * 发牌校验
	 * 
	 * @param current
	 * @param previous
	 * @return
	 */
	Message validate(List<Card> current, List<Card> previous);
}
