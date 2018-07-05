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

		// ��ʼ����Ϸ����
		validate = new GeneralValidate();

		// �ҵ����
		UserConfig userConfig = getUserConfig();
		List<Player> players = userConfig.getPlayers();
		if (players == null) {
			System.exit(1);
		}

		// ����
		Cards cards = new Cards(4);
		playerCards = cards.send(players);

		// ��ҳ���
		List<Card> previous = null;
		while (!succeed()) {
			for (Player p : players) {
				String name = p.getName();
				List<Card> current = p.getPlay().send(previous);
				while (!validate.validate(current, previous)) {
					p.getPlay().succeed(-1, "");
					current = p.getPlay().send(previous);
					// System.out.println(name + " ���� " + current);
				}
				p.getPlay().succeed(0, "");
				System.out.println(name + " ���� " + current);
				playerCards.get(name).removeAll(current); // �Ƴ��û����е���
				previous = current;
			}
		}

		// ��Ϸ����
		System.out.println("Game Over");
	}

	private boolean succeed() {
		return false;
	}

	private UserConfig getUserConfig() {
		return null;
	}

}
