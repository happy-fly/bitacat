package com.kgh.card.config;

import java.util.List;

import com.kgh.card.bean.Player;

/**
 * 玩家配置
 * 
 * @author lenovo
 *
 */
public class UserConfig {

	private List<Player> players; // 玩家
	private String playRule; // 玩法规则
	private Integer cardNum = 1; // 使用几封牌

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public String getPlayRule() {
		return playRule;
	}

	public void setPlayRule(String playRule) {
		this.playRule = playRule;
	}

	public Integer getCardNum() {
		return cardNum;
	}

	public void setCardNum(Integer cardNum) {
		this.cardNum = cardNum;
	}

	@Override
	public String toString() {
		return "UserConfig [players=" + players + ", playRule=" + playRule + ", cardNum=" + cardNum + "]";
	}

}
