package com.kgh.card.core.ui;

import com.kgh.card.core.bean.Card;
import com.kgh.card.core.bean.Message;
import com.kgh.card.core.bean.Player;
import com.kgh.card.core.config.UserConfig;

import java.util.List;
import java.util.Map;

public interface UI {

    /**
     * 初始化
     *
     * @param userConfig
     */
    void init(UserConfig userConfig);

    /**
     * 展示消息
     *
     * @param message
     */
    void message(Message message);

    /**
     * 初始化玩家手中的牌
     *
     * @param cards
     */
    void initCards(Map<String, List<Card>> cards);

    /**
     * 发牌失败
     *
     * @param player
     * @param cards
     */
    void handOutError(Player player, List<Card> cards);

    /**
     * 发牌
     *
     * @param player
     * @param cards
     */
    void handOutSuccess(Player player, List<Card> cards);

    /**
     * 获胜
     *
     * @param player
     */
    void succeed(Player player);
}
