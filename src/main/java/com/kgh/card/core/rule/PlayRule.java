package com.kgh.card.core.rule;

import java.util.List;
import java.util.Map;

import com.kgh.card.core.bean.Card;
import com.kgh.card.core.bean.Message;
import com.kgh.card.core.bean.Player;
import com.kgh.card.core.bean.RuleConfig;

/**
 * 玩法规则校验
 * 
 * @author 孔冠华
 *
 */
public interface PlayRule {
	
	void init(RuleConfig config);

	/**
	 * 人数校验
	 * 
	 * @param num
	 * @return
	 */
	Message players(int num);

	/**
	 * 给玩家发牌
	 * 
	 * @param players
	 * @return
	 */
	Map<String, List<Card>> handOut(List<Player> players);

	/**
	 * 发牌校验
	 * 
	 * @param current
	 * @param previous
	 * @return
	 */
	Message validate(List<Card> current, List<Card> previous);
}
