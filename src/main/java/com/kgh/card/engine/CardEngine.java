package com.kgh.card.engine;

import java.util.List;
import java.util.Map;

import com.kgh.card.core.config.SysConfig;
import com.kgh.card.core.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kgh.card.core.bean.Card;
import com.kgh.card.core.bean.Message;
import com.kgh.card.core.bean.Player;
import com.kgh.card.core.bean.RuleConfig;
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

	// 系统配置
	private SysConfig sysConfig;

	// 系统界面
	private UI ui;

	static {

	}

	public void init() throws Exception{
		// 初始化系统配置
		sysConfig = ConfigUtils.getSysConfig();

		// 初始化用户配置
		userConfig = ConfigUtils.getUserConfig();

		// 初始化游戏玩法
		validate = ConfigUtils.getValidate(userConfig);

		// 初始化配置完成，开始游戏
		start();
	}

	public void start() throws Exception{
		// 初始化ui
		String uiName = sysConfig.getUi();
		if(uiName == null) {
			logger.warn("无效的ui");
			System.exit(1);
		}
		ui = (UI)Class.forName(uiName).newInstance();
		// 初始化ui
		ui.init(userConfig);

		List<Player> players = userConfig.getPlayers();
		if (players == null || players.isEmpty() || players.size() < 2) {
			logger.warn("初始化玩家信息失败！！");
			ui.message(new Message(Code.ERROR, "初始化玩家信息失败！！"));
			System.exit(1);
		}

		// 人数校验
		Message pv = validate.players(players.size());
		if (pv == null || Code.SUCCESS != pv.getCode()) {
			logger.warn("游戏人数不通过校验, 校验返回消息：" + pv);
			ui.message(new Message(Code.ERROR, "游戏人数校验不通过"));
			System.exit(-1);
		}

		// 初始化牌
		validate.init(new RuleConfig().setCardNum(userConfig.getCardNum()));

		logger.info(
				"游戏初始化成功，游戏规则为：" + validate.getClass() + " 游戏人数：" + players.size() + " 牌数：" + userConfig.getCardNum());
		ui.message(new Message(Code.SUCCESS, "游戏初始化成功！ 开始发牌..."));

		// 系统发牌
		playerCards = validate.handOut(players);
		ui.initCards(playerCards);

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
					ui.handOutError(p, current);
					current = p.getPlay().send(previousName, previous);
					sp = validate.validate(current, previous);
				}
				p.getPlay().succeed(0, "");
				System.out.println(name + " 发牌  " + current);
				playerCards.get(name).removeAll(current); // 移除当前发的牌
				ui.handOutSuccess(p, current);

				if (current != null && current.size() > 0) {
					previous = current;
					previousName = name;
				}

				if (succeed(p)) {
					ui.succeed(p);
					System.out.println(p.getName() + " 获胜！");
					System.exit(0);
				}
			}
		}

	}

	/**
	 * 判断是否胜利
	 * 
	 * @param p
	 *            玩家
	 * @return
	 */
	private boolean succeed(Player p) {
		return playerCards.get(p.getName()).size() == 0;
	}

}
