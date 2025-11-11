package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDAO {

    // CÁC CÂU TRUY VẤN MỚI SỬ DỤNG SUBQUERY ĐỂ LẤY TÊN NCC GẦN NHẤT
    private final String BASE_PRODUCT_SELECT = 
            "SELECT p.id, p.code, p.name, p.salePrice, p.description, p.quantity, " +
            "       (SELECT s.name FROM tblSupplier s " +
            "        JOIN tblImportSlip i ON s.id = i.tblSupplierId " +
            "        JOIN tblImportSlipDetail d ON i.id = d.tblImportSlipId " +
            "        WHERE d.tblProductId = p.id " +
            "        ORDER BY i.date DESC, i.id DESC " + // Lấy giao dịch gần nhất
            "        LIMIT 1) AS SupplierName " +       
            "FROM tblProduct p WHERE p.quantity > 0 ";

    private Product createProductFromResultSet(ResultSet rs) throws Exception {
        // Tên nhà cung cấp được lấy từ cột alias "SupplierName"
        String supplierName = rs.getString("SupplierName");
        // Nếu sản phẩm chưa từng được nhập, SupplierName có thể là NULL
        if (supplierName == null) {
            supplierName = "Chưa có thông tin NCC"; 
        }

        return new Product(
            rs.getInt("id"),
            rs.getString("code"),
            rs.getString("name"),
            supplierName, // <--- SỬ DỤNG DỮ LIỆU TỪ DB
            rs.getFloat("salePrice"),
            rs.getString("description")
        );
    }
    
    // 1. getProduct()
    public List<Product> getProduct() {
        List<Product> list = new ArrayList<>();
        // Sử dụng câu truy vấn mới
        String SQL = BASE_PRODUCT_SELECT + " ORDER BY p.id ASC"; 
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                list.add(createProductFromResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. getProductByName(String name)
    public List<Product> getProductByName(String name) {
        List<Product> list = new ArrayList<>();
        // Sử dụng câu truy vấn mới và thêm điều kiện lọc
        String SQL = BASE_PRODUCT_SELECT + " AND p.name LIKE ? ORDER BY p.id ASC";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL)) {
            
            ps.setString(1, "%" + name + "%"); // Tìm kiếm gần đúng
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(createProductFromResultSet(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3. getProductDetail(int productId) 
    public Product getProductDetail(int productId) {
        Product product = null;
        // Sử dụng câu truy vấn mới
        String SQL = BASE_PRODUCT_SELECT + " AND p.id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL)) {
            
            ps.setInt(1, productId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String supplierName = rs.getString("SupplierName");
                    if (supplierName == null) {
                        supplierName = "Chưa có thông tin NCC"; 
                    }
                    
                    product = new Product(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("name"),
                        supplierName, 
                        rs.getFloat("salePrice"),
                        rs.getString("description"),
                        rs.getInt("quantity")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
}