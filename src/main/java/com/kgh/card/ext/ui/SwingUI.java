package com.kgh.card.ext.ui;

import com.kgh.card.core.bean.Card;
import com.kgh.card.core.bean.Message;
import com.kgh.card.core.bean.Player;
import com.kgh.card.core.config.UserConfig;
import com.kgh.card.core.ui.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/23 18:09
 */
public class SwingUI extends JFrame implements UI, ActionListener {

    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel playerPanel;
    private JTextArea displayArea;
    private JTextArea playerArea;
    private JButton startButton;
    private Map<String, JButton> players = new HashMap<>();
    private SwingUI that;

    public SwingUI() {
        this.setTitle("浪曦云对战平台");
        this.setBounds(200, 200, 1000, 600);
        this.setLayout(new BorderLayout());
        this.that = this;

        titlePanel = new JPanel();
        JLabel title = new JLabel("浪曦对战平台");
        title.setFont(new Font("Monospaced", Font.PLAIN, 44));
        titlePanel.add(title);
        this.add(titlePanel, BorderLayout.NORTH);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);

        startButton = new JButton("开始");
        startButton.addActionListener(this);
        mainPanel.add(startButton);

        playerArea = new JTextArea("玩家");
        playerArea.setFont(new Font("Monospaced", Font.PLAIN, 44));
        playerArea.setColumns(10);
        playerArea.setRows(5);
        mainPanel.add(playerArea);

        displayArea = new JTextArea("准备！");
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 44));
        displayArea.setColumns(10);
        displayArea.setRows(5);
        mainPanel.add(displayArea);

        playerPanel = new JPanel();
        playerPanel.setBackground(Color.RED);
        playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(playerPanel, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void init(UserConfig userConfig) {
        waiting();

        // 展示玩家
        int playersCount = userConfig.getPlayers().size();
        for (int i = 0; i < playersCount; i++) {
            JButton btnNewButton_1 = new JButton(userConfig.getPlayers().get(i).getName());
            players.put(userConfig.getPlayers().get(i).getName(), btnNewButton_1);
            playerPanel.add(btnNewButton_1);
        }
        sleep();
    }

    @Override
    public void message(Message message) {
        String text = displayArea.getText();
        displayArea.setText(text + "\n" + message.getMsg());
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
        turn(player);
        playerArea.setText(player.getName());
        displayArea.setText("" + cards.stream().map(c -> {
            if (c != null) {
                return c.getName();
            }
            return "不要";
        }).collect(Collectors.toList()));
        sleep();
    }

    @Override
    public void succeed(Player player) {
        JOptionPane.showMessageDialog(null, player.getName() + " 胜利", "提示", JOptionPane.INFORMATION_MESSAGE);
        waiting();
    }


    /**
     * 休眠控制
     */
    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void waiting() {
        try {
            wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void turn(Player player) {
        String name = player.getName();
        players.keySet().stream().forEach(p -> {
            if (p.equals(name)) {
                players.get(name).setForeground(Color.BLUE);
            } else {
                players.get(name).setForeground(Color.BLACK);
            }
        });
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        notifyAll();
        mainPanel.remove(startButton);
    }
}
