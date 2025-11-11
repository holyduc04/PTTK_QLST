package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ImportSlip;

public class ImportSlipDAO {

    private final String SQL_GET_SLIPS = 
            "SELECT i.id, i.date, i.totalPrice, u.name AS StaffName, u.user AS StaffCode " + // Lấy cả Mã NV Kho
            "FROM tblImportSlip i " +
            "JOIN tblWarehouseStaff w ON i.tblWarehouseStaffId = w.id " +
            "JOIN tblStaff s ON w.tblStaffId = s.id " +
            "JOIN tblUser u ON s.tblUserId = u.id " +
            "WHERE i.tblSupplierId = ? AND i.date BETWEEN ? AND ?" + 
            "ORDER BY i.date DESC"; // Các lần nhập hàng gần đây sẽ ưu tiên 

    public List<ImportSlip> getImportSlipBySupplier(int supplierId, String fromDate, String endDate) {
        List<ImportSlip> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_GET_SLIPS)) {
            
            ps.setInt(1, supplierId);
            ps.setString(2, fromDate);
            ps.setString(3, endDate);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String dummySupplierName = "Nhà cung cấp ID: " + supplierId; 
                    list.add(new ImportSlip(
                        rs.getInt("id"),
                        rs.getDate("date"),
                        rs.getFloat("totalPrice"),
                        dummySupplierName,
                        rs.getString("StaffName") 
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}