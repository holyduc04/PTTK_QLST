package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.SupplierStatistic;

public class SupplierStatisticDAO {

    // SQL để lấy dữ liệu thống kê (Sử dụng JOIN, SUM, COUNT và GROUP BY)
    private final String BASE_SQL = 
            "SELECT s.id, s.name, COUNT(DISTINCT(i.id)) AS ImportCount, " +
            "       SUM(d.quantity) AS TotalQuantity, SUM(i.totalPrice) AS TotalMoney " +
            "FROM tblSupplier s " +
            "JOIN tblImportSlip i ON s.id = i.tblSupplierId " +
            "JOIN tblImportSlipDetail d ON i.id = d.tblImportSlipId ";
    
    // Sắp xếp NCC theo tổng tiền cao nhất
    private final String GROUP_BY_SQL = 
            "GROUP BY s.id, s.name ORDER BY TotalMoney DESC";

    // Lấy mặc định (không điều kiện thời gian)
    public List<SupplierStatistic> getSupplierStatisticByQuantityImport() {
        return executeStatisticQuery(BASE_SQL + GROUP_BY_SQL, null, null);
    }

    // Lấy có điều kiện thời gian
    public List<SupplierStatistic> getSupplierStatisticByQuantityImportWithDate(String fromDate, String endDate) {
        String WHERE_CLAUSE = "WHERE i.date >= ? AND i.date <= ? ";
        return executeStatisticQuery(BASE_SQL + WHERE_CLAUSE + GROUP_BY_SQL, fromDate, endDate);
    }

    
    private List<SupplierStatistic> executeStatisticQuery(String sql, String fromDate, String endDate) {
        List<SupplierStatistic> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            if (fromDate != null && endDate != null) {
                ps.setString(1, fromDate);
                ps.setString(2, endDate);
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new SupplierStatistic(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("ImportCount"),
                        rs.getInt("TotalQuantity"),
                        rs.getFloat("TotalMoney")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}