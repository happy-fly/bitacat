package com.kgh.card.face;

import java.util.List;

import com.kgh.card.core.bean.Card;

/**
 * 玩家需要实现这个接口，并对接口中的方法进行实现。
 *
 * 游戏过程中，对每个玩家实例化单个实例
 * 
 * @author lenovo
 *
 */
public interface Play {

	/**
	 * 接收系统发牌
	 * 
	 * @param cards
	 */
	void init(String name, List<Card> cards);
	
	/**
	 * 发牌
	 * 
	 * @param previous 上一家的发的牌,如果上
	 * @return
	 */
	List<Card> send(String name, List<Card> previous);
	
	/**
	 * 发牌是否成功
	 * 
	 * @param code
	 * @param msg
	 */
	void succeed(int code, String msg);
	
	/**
	 * 每当有玩家发牌，系统都会回调这个接口
	 * 
	 * @param cards 发的牌
	 * @param name 玩家的名字
	 */
	void watch(List<Card> cards, String name);
	
	/**
	 * 可以给其他玩家说话
	 * 
	 * @return
	 */
	String say();
	
	/**
	 * 收到其他玩家的发言
	 * 
	 * @param msg
	 */
	void receive(String msg);
	
}
