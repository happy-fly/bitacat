package com.kgh.card.start;

import java.util.List;
import java.util.Map;

import com.kgh.card.bean.Card;
import com.kgh.card.bean.Player;
import com.kgh.card.config.UserConfig;
import com.kgh.card.game.GeneralValidate;
import com.kgh.card.game.Validate;

public class App {

	private Map<String, List<Card>> playerCards;
	private Validate validate;

	public static void main(String[] args) {
		new App().start();
	}

	private void start() {

		// 初始化游戏规则
		validate = new GeneralValidate();

		// 找到玩家
		UserConfig userConfig = getUserConfig();
		List<Player> players = userConfig.getPlayers();
		if (players == null) {
			System.exit(1);
		}

		// 发牌
		Cards cards = new Cards(4);
		playerCards = cards.send(players);

		// 玩家出牌
		List<Card> previous = null;
		while (!succeed()) {
			for (Player p : players) {
				String name = p.getName();
				List<Card> current = p.getPlay().send(previous);
				while (!validate.validate(current, previous)) {
					p.getPlay().succeed(-1, "");
					current = p.getPlay().send(previous);
					// System.out.println(name + " 出牌 " + current);
				}
				p.getPlay().succeed(0, "");
				System.out.println(name + " 出牌 " + current);
				playerCards.get(name).removeAll(current); // 移除用户手中的牌
				previous = current;
			}
		}

		// 游戏结束
		System.out.println("Game Over");
	}

	private boolean succeed() {
		return false;
	}

	private UserConfig getUserConfig() {
		return null;
	}

}
