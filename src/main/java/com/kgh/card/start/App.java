package com.kgh.card.start;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.kgh.card.bean.Card;
import com.kgh.card.bean.Player;
import com.kgh.card.bean.Resp;
import com.kgh.card.config.UserConfig;
import com.kgh.card.constant.Code;
import com.kgh.card.game.GeneralValidate;
import com.kgh.card.game.Validate;
import com.kgh.card.utils.FileUtils;

public class App {

	private Map<String, List<Card>> playerCards;
	private Validate validate;
	private UserConfig userConfig;
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		new App().start();
	}

	private void start() {

		// 初始化用户配置
		userConfig = getUserConfig();

		// 初始化游戏玩法
		validate = getValidate(userConfig);

		List<Player> players = userConfig.getPlayers();
		if (players == null) {
			logger.warn("初始化玩家信息失败！！");
			System.exit(1);
		}
		
		// 人数校验
		Resp pv = validate.players(players.size());
		if(pv == null || Code.SUCCESS != pv.getCode()) {
			logger.warn("游戏人数不通过校验, 校验返回消息：" + pv);
			System.exit(-1);
		}

		// 初始化牌
		Cards cards = new Cards(userConfig.getCardNum());

		logger.info("游戏初始化成功，游戏规则为：" + validate.getClass() + " 游戏人数：" + players.size() + " 牌数：" + userConfig.getCardNum());

		// 系统发牌
		playerCards = cards.send(players);

		// 开始
		List<Card> previous = null;
		String previousName = "";
		while (true) {
			for (Player p : players) {
				String name = p.getName();
				List<Card> current = p.getPlay().send(previousName, previous);
				Resp sp = validate.validate(current, previous);
				while (sp == null || Code.SUCCESS != sp.getCode()) {
					p.getPlay().succeed(-1, "");
					current = p.getPlay().send(previousName, previous);
					sp = validate.validate(current, previous);
				}
				p.getPlay().succeed(0, "");
				System.out.println(name + " 发牌  " + current);
				playerCards.get(name).removeAll(current); // 移除当前发的牌
				
				if(current != null && current.size() > 0) {
					previous = current;
					previousName = name;
				}
			
				if(succeed(p)) {
					System.out.println(p.getName() + " 获胜！");
					System.exit(0);
				}
			}
		}

	}

	/**
	 * 获得游戏的玩法
	 * 
	 * @param userConfig
	 * @return
	 */
	private Validate getValidate(UserConfig userConfig) {
		String playRule = userConfig.getPlayRule();
		if (StringUtils.isBlank(playRule)) {
			return new GeneralValidate();
		}
		try {
			Object obj = Class.forName(playRule).newInstance();
			if (obj instanceof Validate) {
				return (Validate) obj;
			}
			throw new RuntimeException("指定了错误的规则！" + playRule);
		} catch (Exception e) {
			throw new RuntimeException("指定的规则不存在！", e);
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

	/**
	 * 读取用户配置，并封装成为 UserConfig
	 * 
	 * @return
	 */
	private UserConfig getUserConfig() {
		return JSONObject.parseObject(FileUtils.readFile("userconfig.json"), UserConfig.class);
	}

}
