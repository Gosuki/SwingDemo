package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductListFrame extends JFrame {
    private JTable productTable;
    private ProductTableModel productTableModel;
    private JButton addButton;


    public ProductListFrame() {
        setTitle("Danh sách sản phẩm");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addButton = new JButton("Thêm sản phẩm");

        // Thêm sự kiện cho nút "Thêm sản phẩm"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mở cửa sổ thêm sản phẩm khi người dùng nhấn nút
                List<Product> products =  new ProductDAO().getAllProducts();
                AddProductFrame addProductFrame = new AddProductFrame(productTableModel);
                addProductFrame.setVisible(true);

                // Cập nhật danh sách sản phẩm sau khi thêm
                addProductFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        updateProductList();
                    }
                });
            }
        });

        // Thêm nút "Thêm sản phẩm" vào giao diện
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);

        productTableModel = new ProductTableModel();
        productTable = new JTable(productTableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        add(scrollPane, BorderLayout.CENTER);
        ProductDAO productDAO= new ProductDAO();
        // Lấy danh sách sản phẩm từ cơ sở dữ liệu và cập nhật JTable
        List<Product> products = productDAO.getAllProducts();
        productTableModel.updateTable(products);
    }

    private void updateProductList() {
        // Lấy lại danh sách sản phẩm từ cơ sở dữ liệu
        List<Product> productList = new ProductDAO().getAllProducts();

        // Cập nhật JTable với danh sách sản phẩm mới
        productTableModel.updateTable(productList);
    }


}
