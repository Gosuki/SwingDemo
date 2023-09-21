package org.example;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField txt_username;
    private JPasswordField txt_password;
    public LoginFrame (){
        setTitle("Login");
        setSize(300,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel lb_username = new JLabel("Username");
        JLabel lb_password = new JLabel("Password");
        txt_username = new JTextField(20);
        txt_password = new JPasswordField(20);
        JButton btn_login = new JButton("Login");
        panel.setLayout(new GridLayout(3,2));
        panel.add(lb_username);
        panel.add(lb_password);
        panel.add(txt_username);
        panel.add(txt_password);
        panel.add(new JLabel());
        panel.add(btn_login);

        btn_login.addActionListener(e -> {
            String username = txt_username.getText();
            String password = new String (txt_password.getPassword());
            if (username.equals("admin") && password.equals("password")){
                ProductListFrame productListFrame = new ProductListFrame();
                productListFrame.setVisible(true);
                setVisible(false);
//                JOptionPane.showMessageDialog(null,"Login complete");
            }else {
                JOptionPane.showMessageDialog(null,"Login fail");
            }
        });
        add(panel);
    }
}
