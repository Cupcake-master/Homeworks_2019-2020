package ru.bulat;

import javax.swing.*;
import java.awt.*;

public class Program extends JFrame {
    private static JTextArea statuses;

    public static void start() {
        new Program();
    }

    private Program(){
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        JPanel panel = (JPanel)this.getContentPane();
        panel.setLayout(null);
        statuses = new JTextArea(20, 20);
        statuses.setLineWrap(true);
        JScrollPane scroll= new JScrollPane(statuses, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setSize(550, 300);
        scroll.setLocation(10,10);
        panel.add(scroll);
        JMenuBar menuBar = new JMenuBar();
        Entrance entrance = new Entrance();
        menuBar.add(entrance.createFileMenu());
        menuBar.add(createChangeColorMenu(panel));
        setJMenuBar(menuBar);
        setVisible(true);
    }

    public JMenu createChangeColorMenu(JPanel panel) {
        JMenu background = new JMenu("Фон");
        JMenuItem red = new JMenuItem("Красный");
        JMenuItem blue = new JMenuItem("Синий");
        JMenuItem yellow = new JMenuItem("Желтый");
        JMenuItem white = new JMenuItem("Белый");
        background.add(red);
        background.add(blue);
        background.add(yellow);
        background.add(white);
        red.addActionListener(e -> {
            panel.setBackground(Color.RED);

            statuses.setText("Вы сменили цвет на красный!");
        });
        blue.addActionListener(e -> {
            panel.setBackground(Color.BLUE);
            statuses.setText("Вы сменили цвет на синий!");
        });
        yellow.addActionListener(e ->{
            panel.setBackground(Color.YELLOW);
            statuses.setText("Вы сменили цвет на жёлтый!");
        });
        white.addActionListener(e -> {
            panel.setBackground(Color.WHITE);
            statuses.setText("Вы сменили цвет на белый!");
        });
        return background;
    }

}
