package com.kgh.card.ext.ui;

import com.kgh.card.core.bean.Card;
import com.kgh.card.core.bean.Message;
import com.kgh.card.core.bean.Player;
import com.kgh.card.core.config.UserConfig;
import com.kgh.card.core.ui.UI;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/23 18:09
 */
public class SwingUI extends JFrame implements UI {

    private JPanel mainPanel;
    private JPanel playerPanel;

    public SwingUI() {
        this.setTitle("浪曦云对战平台");
        this.setBounds(200, 200, 1000, 600);
        this.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 1000, 100);
        mainPanel.setBackground(Color.BLACK);

        playerPanel = new JPanel();
        playerPanel.setBounds(0, 0, 1000, 200);
        mainPanel.setBackground(Color.RED);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(playerPanel, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void init(UserConfig userConfig) {
        sleep();
    }

    @Override
    public void message(Message message) {
        sleep();
    }

    @Override
    public void initCards(Map<String, List<Card>> cards) {
        sleep();
    }

    @Override
    public void handOutError(Player player, List<Card> cards) {
        sleep();
    }

    @Override
    public void handOutSuccess(Player player, List<Card> cards) {
        sleep();
    }

    @Override
    public void succeed(Player player) {
        sleep();
    }


    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {

        }
    }
}
