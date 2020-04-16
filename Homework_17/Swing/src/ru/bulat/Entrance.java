package ru.bulat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Entrance extends JFrame {
    private JProgressBar progressBar;


    public Entrance() {
        initComponents();
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Entrance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(() -> new Entrance().setVisible(true));
    }

    private void initComponents() {
        JLabel title = new JLabel();
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);

        JButton decrease = new JButton();
        JButton increase = new JButton();

        decrease.setText("+ 10%");
        decrease.addActionListener(e -> {
            int value = progressBar.getValue() + 10;
            int maximum = progressBar.getMaximum();
            if (value > maximum) {
                value = maximum;
            }
            progressBar.setValue(value);
            if (progressBar.getValue() == progressBar.getMaximum()) {
                setVisible(false);
                Program.start();
            }
        });

        increase.setText("- 10%");
        increase.addActionListener(e -> {
            int value = progressBar.getValue() - 10;
            int minimum = progressBar.getMinimum();
            if (value < minimum) {
                value = minimum;
            }
            progressBar.setValue(value);
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        title.setText("         Welcome to the program!");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        setJMenuBar(menuBar);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(100, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(increase)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(decrease))
                                        .addComponent(title, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(progressBar, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(title, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(increase)
                                        .addComponent(decrease))
                                .addContainerGap(99, Short.MAX_VALUE))
        );

        pack();
    }

    public JMenu createFileMenu() {
        JMenu file = new JMenu("Файл");
        JMenuItem open = new JMenuItem("О программе",
                new ImageIcon("src\\resources\\open.png"));
        ExitAction exitAction = new ExitAction();
        JMenuItem exit = new JMenuItem(exitAction);
        exit.setIcon(new ImageIcon("src\\resources\\exit.png"));
        file.add(open);
        file.addSeparator();
        file.add(exit);
        open.addActionListener(arg0 -> JOptionPane.showMessageDialog(Entrance.this,
                "(Something about my application)"));
        return file;
    }

    public static class ExitAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        ExitAction() {
            putValue(NAME, "Выход");
        }

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }
}
