package com.kgh.card.start;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.kgh.card.bean.Card;
import com.kgh.card.bean.Player;

public class Cards {

	public static final String[] CARDS = { "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2" };

	private List<Card> cards;

	public Cards(int n) {
		cards = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			for (int k = 0; k < 4; k++) {
				cards.add(new Card("w", k, 14));
				cards.add(new Card("W", k, 15));
				for (int j = 0; j < CARDS.length; j++) {
					String name = CARDS[j];
					cards.add(new Card(name, k, j));
				}
			}
		}
	}

	public Map<String, List<Card>> send(List<Player> players) {
		if (players == null) {
			return null;
		}
		Map<String, List<Card>> result = new HashMap<>();

		int cardNum = cards.size();
		Random r = new Random();
		for (int i = 0; i < cardNum; i++) {
			for (Player player : players) {
				String name = player.getName();
				List<Card> cards = result.get(name);
				if (cards == null) {
					cards = new ArrayList<>();
				}
				cards.add(cards.remove(r.nextInt(cards.size())));
			}
		}

		// ·¢ÅÆ
		for(Player p : players) {
			String name = p.getName();
			p.getPlay().init(result.get(name));
		}
		return result;
	}

}
