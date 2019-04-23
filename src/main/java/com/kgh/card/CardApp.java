package com.kgh.card;

import com.kgh.card.engine.CardEngine;

/**
 * 系统核心启动类
 *
 * @author 孔冠华
 */
public class CardApp {

    public static void main(String[] args) throws Exception {
        new CardEngine().init();
    }

}
