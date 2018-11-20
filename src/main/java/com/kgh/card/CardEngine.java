package com.kgh.card;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kgh.card.core.bean.Card;
import com.kgh.card.core.bean.Cards;
import com.kgh.card.core.bean.Player;
import com.kgh.card.core.bean.Message;
import com.kgh.card.core.config.UserConfig;
import com.kgh.card.core.constant.Code;
import com.kgh.card.core.rule.PlayRule;
import com.kgh.card.core.utils.ConfigUtils;

public class CardEngine {
	
	private static final Logger logger = LoggerFactory.getLogger(CardEngine.class);

	// 玩家手中持有的牌
	private Map<String, List<Card>> playerCards;

	// 当前的玩法
	private PlayRule validate;

	// 用户配置
	private UserConfig userConfig;

	static {

	}

	public void init() {
		// 初始化用户配置
		userConfig = ConfigUtils.getUserConfig();

		// 初始化游戏玩法
		validate = ConfigUtils.getValidate(userConfig);
		
		// 初始化配置完成，开始游戏
		start();
	}

	public void start() {
		List<Player> players = userConfig.getPlayers();
		if (players == null || players.isEmpty() || players.size() < 2) {
			logger.warn("初始化玩家信息失败！！");
			System.exit(1);
		}

		// 人数校验
		Message pv = validate.players(players.size());
		if (pv == null || Code.SUCCESS != pv.getCode()) {
			logger.warn("游戏人数不通过校验, 校验返回消息：" + pv);
			System.exit(-1);
		}

		// 初始化牌
		Cards cards = new Cards(userConfig.getCardNum());

		logger.info(
				"游戏初始化成功，游戏规则为：" + validate.getClass() + " 游戏人数：" + players.size() + " 牌数：" + userConfig.getCardNum());

		// 系统发牌
		playerCards = cards.send(players);

		// 开始
		List<Card> previous = null;
		String previousName = "";
		while (true) {
			for (Player p : players) {
				String name = p.getName();
				List<Card> current = p.getPlay().send(previousName, previous);
				Message sp = validate.validate(current, previous);
				while (sp == null || Code.SUCCESS != sp.getCode()) {
					p.getPlay().succeed(-1, "");
					current = p.getPlay().send(previousName, previous);
					sp = validate.validate(current, previous);
				}
				p.getPlay().succeed(0, "");
				System.out.println(name + " 发牌  " + current);
				playerCards.get(name).removeAll(current); // 移除当前发的牌

				if (current != null && current.size() > 0) {
					previous = current;
					previousName = name;
				}

				if (succeed(p)) {
					System.out.println(p.getName() + " 获胜！");
					System.exit(0);
				}
			}
		}

	}
	
	/**
	 * 判断是否胜利
	 * 
	 * @param p 玩家
	 * @return
	 */
	private boolean succeed(Player p) {
		return playerCards.get(p.getName()).size() == 0;
	}

}
