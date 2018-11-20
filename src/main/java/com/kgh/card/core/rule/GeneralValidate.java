package com.kgh.card.core.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kgh.card.core.bean.Card;
import com.kgh.card.core.bean.Message;
import com.kgh.card.core.bean.Player;
import com.kgh.card.core.bean.RuleConfig;
import com.kgh.card.core.constant.Code;

/**
 * 最基础玩法，允许随意发牌
 * 
 * @author 孔冠华
 *
 */
public class GeneralValidate implements PlayRule {
	
	private static final Logger logger = LoggerFactory.getLogger(GeneralValidate.class);
	
	public static final String[] CARDS = { "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2" };

	private List<Card> cards;

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

	@Override
	public Map<String, List<Card>> handOut(List<Player> players) {
		if (players == null) {
			return null;
		}
		Map<String, List<Card>> result = new HashMap<>();

		int cardNum = cards.size();
		Random r = new Random();
		for (int i = 0; i < cardNum/players.size(); i++) {
			for (Player player : players) {
				String name = player.getName();
				List<Card> cs = result.get(name);
				if (cs == null) {
					cs = new ArrayList<>();
					result.put(name, cs);
				}
				int index = r.nextInt(cards.size());
				cs.add(cards.remove(index));
			}
		}

		// 给玩家发牌
		for (Player p : players) {
			String name = p.getName();
			List<Card> pcards = result.get(name);
			logger.debug("发给 " + name + " 的牌的数量为： " + pcards.size() + " 牌为：" + pcards);
			p.getPlay().init(pcards);
		}
		return result;
	}

	@Override
	public void init(RuleConfig config) {
		cards = new LinkedList<>();

		for (int i = 0; i < config.getCardNum(); i++) {
			cards.add(new Card("w", 0, 14));
			cards.add(new Card("W", 0, 15));
			for (int k = 0; k < 4; k++) {
				for (int j = 0; j < CARDS.length; j++) {
					String name = CARDS[j];
					cards.add(new Card(name, k, j));
				}
			}
		}
	}

}
