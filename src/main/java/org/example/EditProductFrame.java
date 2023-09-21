package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProductFrame extends JFrame {
    private JTextField nameField;
    private JTextField priceField;
    private JButton saveButton;
    public EditProductFrame(Product selectedProduct) {
        setTitle("Thêm sản phẩm");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        nameField = new JTextField(20);
        nameField.setText(selectedProduct.getName());
        priceField = new JTextField(10);
        priceField.setText(String.valueOf(selectedProduct.getPrice()));
        saveButton = new JButton("Lưu");

        // Thêm sự kiện cho nút "Lưu"
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy thông tin sản phẩm từ các trường nhập liệu
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());

                // Thêm sản phẩm mới vào cơ sở dữ liệu hoặc danh sách sản phẩm
                // (Bạn cần xử lý tạo sản phẩm mới ở đây)

                selectedProduct.setName(name);
                selectedProduct.setPrice(price);
                boolean success = new ProductDAO().updateProduct(selectedProduct);
                if (success) {
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể thêm sản phẩm.");
                }
                // Đóng cửa sổ sau khi lưu sản phẩm
                dispose();
            }
        });

        // Định bố cục của cửa sổ
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Tên sản phẩm:"));
        panel.add(nameField);
        panel.add(new JLabel("Giá:"));
        panel.add(priceField);
        panel.add(new JLabel(""));
        panel.add(saveButton);

        add(panel, BorderLayout.CENTER);
    }
}
