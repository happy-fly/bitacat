package com.kgh.card.game;

import java.util.List;

import com.kgh.card.bean.Card;
import com.kgh.card.bean.Resp;

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
	Resp players(int num);

	/**
	 * 发牌校验
	 * 
	 * @param current
	 * @param previous
	 * @return
	 */
	Resp validate(List<Card> current, List<Card> previous);
}
