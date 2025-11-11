package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ImportSlipDetail;
import model.SlipInfo;

public class ImportSlipDetailDAO {

    // SQL để lấy chi tiết hóa đơn (dùng cho getImportSlipDetails)
    private final String SQL_GET_DETAILS = 
            "SELECT d.id, d.quantity, d.importPrice, p.name AS ProductName, p.unit AS ProductUnit, p.code AS ProductCode " +
            "FROM tblImportSlipDetail d " +
            "JOIN tblProduct p ON d.tblProductId = p.id " +
            "WHERE d.tblImportSlipId = ?";
            
    // SQL để lấy thông tin chung của hóa đơn (dùng cho getImportSlipInfo)
    private final String SQL_GET_SLIP_INFO = 
            // SỬA ĐỔI: Lấy w.staffCode AS StaffCodeId (Mã chuỗi NVK01, NVK02)
            "SELECT u.name AS StaffName, w.staffCode AS StaffCodeId, s.name AS SupplierName, " + 
            "       i.totalPrice, i.date " +
            "FROM tblImportSlip i " +
            "JOIN tblSupplier s ON i.tblSupplierId = s.id " +
            "JOIN tblWarehouseStaff w ON i.tblWarehouseStaffId = w.id " +
            "JOIN tblStaff st ON w.tblStaffId = st.id " +
            "JOIN tblUser u ON st.tblUserId = u.id " +
            "WHERE i.id = ?";


    // 1. Hàm lấy chi tiết hóa đơn
    public List<ImportSlipDetail> getImportSlipDetails(int importSlipId) {
        List<ImportSlipDetail> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_GET_DETAILS)) {
            
            ps.setInt(1, importSlipId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ImportSlipDetail(
                        rs.getInt("id"),
                        rs.getInt("quantity"),
                        rs.getFloat("importPrice"),
                        rs.getString("ProductName"),
                        rs.getString("ProductUnit"),
                        rs.getString("ProductCode")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Hàm lấy thông tin chung của hóa đơn (Đã sửa Mã NV Kho)
    public SlipInfo getImportSlipInfo(int importSlipId) {
        SlipInfo info = null;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_GET_SLIP_INFO)) {
            
            ps.setInt(1, importSlipId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Lấy giá trị staffCode (NVK01, NVK02) là chuỗi
                    String staffCode = rs.getString("StaffCodeId"); 
                    
                    info = new SlipInfo(
                        rs.getString("StaffName"),
                        staffCode, // <-- Mã NV Kho giờ là chuỗi (NVK01)
                        rs.getString("SupplierName"),
                        rs.getFloat("totalPrice"),
                        rs.getDate("date")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
}