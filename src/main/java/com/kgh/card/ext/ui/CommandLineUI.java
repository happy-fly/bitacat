package com.kgh.card.ext.ui;

import com.kgh.card.core.bean.Card;
import com.kgh.card.core.bean.Message;
import com.kgh.card.core.bean.Player;
import com.kgh.card.core.config.UserConfig;
import com.kgh.card.core.ui.UI;

import java.util.List;
import java.util.Map;

public class CommandLineUI implements UI {

    @Override
    public void init(UserConfig userConfig) {
        System.out.println("**********************************************");
        System.out.println("**********************************************");
        System.out.println("************初始化成功************************");
        System.out.println("**********************************************");
        System.out.println("**********************************************");
        System.out.println("**********************************************");
    }

    @Override
    public void message(Message message) {

    }

    @Override
    public void initCards(Map<String, List<Card>> cards) {

    }

    @Override
    public void handOutError(Player player, List<Card> cards) {

    }

    @Override
    public void handOutSuccess(Player player, List<Card> cards) {

    }

    @Override
    public void succeed(Player player) {

    }
}
