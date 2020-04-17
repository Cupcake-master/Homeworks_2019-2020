package sample;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Registration extends JFrame {
    private JButton enter;
    private JPasswordField fieldPassword;
    private JTextField fieldNickname;
    private JTextField fieldEmail;
    private JTextField fieldCountry;

    public Registration() {
        initComponents();
    }

    private void initComponents() {
        setResizable(false);
        fieldPassword = new JPasswordField();
        JLabel labelEmail = new JLabel();
        JLabel labelPassword = new JLabel();
        fieldEmail = new JTextField();
        enter = new JButton();
        JLabel labelRegistration = new JLabel();
        fieldNickname = new JTextField();
        JLabel labelNickname = new JLabel();
        fieldCountry = new JTextField();
        JLabel labelCountry = new JLabel();


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        labelEmail.setText("  E-mail:");

        labelPassword.setText("Password:");

        enter.setText("Enter");
        enter.addActionListener(this::jButton3ActionPerformed);

        labelRegistration.setText("Registration");

        labelNickname.setText("Nickname:");

        labelCountry.setText("Country:");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(labelPassword)
                                                .addComponent(labelEmail, GroupLayout.Alignment.TRAILING))
                                        .addComponent(labelNickname)
                                        .addComponent(labelCountry))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fieldCountry)
                                        .addComponent(fieldNickname)
                                        .addComponent(fieldEmail)
                                        .addComponent(fieldPassword, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(298, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(labelRegistration)
                                                .addGap(231, 231, 231))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(enter)
                                                .addGap(239, 239, 239))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(labelRegistration)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(fieldEmail, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelEmail))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(fieldPassword, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelPassword))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(fieldNickname, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelNickname))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(fieldCountry, GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelCountry))
                                .addGap(30, 30, 30)
                                .addComponent(enter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(68, 68, 68))
        );

        fieldPassword.getAccessibleContext().setAccessibleName("password");
        labelEmail.getAccessibleContext().setAccessibleName("label_email");
        labelEmail.getAccessibleContext().setAccessibleDescription("");
        labelPassword.getAccessibleContext().setAccessibleName("label_password");

        pack();
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new Registration().setVisible(true));
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher  = pattern.matcher(fieldEmail.getText());
        if (evt.getActionCommand().equals("Enter") && matcher.matches() && fieldPassword.getText().trim().length() > 5 &&
                fieldPassword.getText().length() < 31 && fieldNickname.getText().trim().length() > 4 &&
                fieldNickname.getText().trim().length() < 15 && !fieldCountry.getText().trim().equals("")) {
            Person person = new Person(fieldEmail.getText(), fieldPassword.getText(), fieldNickname.getText(), fieldCountry.getText());
            fieldEmail.setText("Успешно!");
            fieldPassword.setText("Успешно!");
            fieldNickname.setText("Успешно!");
            fieldCountry.setText("Успешно!");
        }
    }
}