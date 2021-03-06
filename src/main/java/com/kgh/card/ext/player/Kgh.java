package com.kgh.card.ext.player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.kgh.card.face.Play;
import com.kgh.card.core.bean.Card;
import com.kgh.card.core.constant.Code;

public class Kgh implements Play {

    private static final Logger logger = LoggerFactory.getLogger(Kgh.class);

    private List<Card> myCards;

    private List<Card> currentSend;

    private String name;

    @Override
    public void init(String name, List<Card> cards) {
        this.myCards = cards;
        this.name = name;
        logger.debug("接收到的牌为：" + myCards);
    }

    @Override
    public List<Card> send(String name, List<Card> previous) {

        // 错误的情况处理
        if (myCards.size() <= 0) {
            return null;
        }

        // 第一次发牌
        if (previous == null || previous.size() == 0) {
            currentSend = Lists.newArrayList(myCards.get(0));
            return currentSend;
        }

        int n = previous.size();
        if (n == 1) {
            Card card = previous.get(0);
            Card m = getGreater(card);
            // 这里很重要，需要判断前面最大的牌是不是自己发的，如果是自己发的，则选出一个最小的值
            if (m == null && name.equals(this.name)) {
                m = getGreater(new Card("0", 0, -100));
            }
            currentSend = Lists.newArrayList(m);
            return currentSend;
        }
        return null;
    }

    /**
     * 从集合中获取一个比指定的card大的牌
     *
     * @param card
     * @return
     */
    private Card getGreater(Card card) {
        if (card == null) {
            card = new Card(null, 1, -1);
        }

        Card temp = card;

        return myCards
                .stream()
                .filter(c -> c.getPosition() > temp.getPosition())
                .sorted(Comparator.comparing(Card::getPosition))
                .findFirst().orElse(null);

    }

    @Override
    public void succeed(int code, String msg) {
        if (Code.SUCCESS == code) {
            myCards.removeAll(currentSend);
        }
    }

    @Override
    public void watch(List<Card> cards, String name) {

    }

    @Override
    public String say() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void receive(String msg) {
        // TODO Auto-generated method stub

    }

}
