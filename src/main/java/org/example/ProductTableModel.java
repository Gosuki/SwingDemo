package org.example;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductTableModel extends DefaultTableModel {
    private static final String[] COLUMN_NAMES = {"ID", "Tên sản phẩm", "Giá"};

    public ProductTableModel() {
        setColumnIdentifiers(COLUMN_NAMES);
    }

    // Xóa tất cả dữ liệu từ bảng
    public void clearTable() {
        dataVector.removeAllElements();
        fireTableDataChanged();
    }

    // Thêm một sản phẩm vào bảng
    public void addProduct(Product product) {
        Object[] rowData = {product.getId(),product.getName(), product.getPrice()};
        addRow(rowData);
    }

    // Cập nhật dữ liệu bảng từ danh sách sản phẩm
    public void updateTable(List<Product> productList) {
        clearTable();
        for (Product product : productList) {
            addProduct(product);
        }
    }

    // Không cho phép chỉnh sửa dữ liệu trực tiếp trên bảng
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
