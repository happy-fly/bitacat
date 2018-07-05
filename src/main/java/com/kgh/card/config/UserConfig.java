package com.kgh.card.config;

import java.util.List;

import com.kgh.card.bean.Player;

/**
 * 用户配置信息
 * 
 * @author lenovo
 *
 */
public class UserConfig {

	private List<Player> players;

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
