package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ProductListFrame extends JFrame {
    private JTable productTable;
    private ProductTableModel productTableModel;
    private JButton addButton;
    private JButton editButton;


    public ProductListFrame() {
        setTitle("Danh sách sản phẩm");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addButton = new JButton("Thêm sản phẩm");
        List<Product> products =  new ProductDAO().getAllProducts();
        // Thêm sự kiện cho nút "Thêm sản phẩm"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mở cửa sổ thêm sản phẩm khi người dùng nhấn nút

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

        // Tạo nút "Sửa"
        editButton = new JButton("Sửa");

        // Thêm sự kiện sửa sản phẩm
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow >= 0) {
                    // Lấy sản phẩm được chọn
                    Product selectedProduct = products.get(selectedRow);

                    // Mở cửa sổ sửa sản phẩm và truyền sản phẩm cần sửa
                    EditProductFrame editFrame = new EditProductFrame(selectedProduct);
                    editFrame.setVisible(true);

                    // Cập nhật danh sách sản phẩm sau khi sửa
                    editFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            updateProductList();
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần sửa.");
                }
            }
        });

        // Thêm nút "Thêm sản phẩm" vào giao diện
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);

        productTableModel = new ProductTableModel();
        productTable = new JTable(productTableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        add(scrollPane, BorderLayout.CENTER);
        ProductDAO productDAO= new ProductDAO();
        // Lấy danh sách sản phẩm từ cơ sở dữ liệu và cập nhật JTable
        List<Product> productLists = productDAO.getAllProducts();
        productTableModel.updateTable(productLists);
    }

    private void updateProductList() {
        // Lấy lại danh sách sản phẩm từ cơ sở dữ liệu
        List<Product> productList = new ProductDAO().getAllProducts();

        // Cập nhật JTable với danh sách sản phẩm mới
        productTableModel.updateTable(productList);
    }


}
