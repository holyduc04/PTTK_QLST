package controller;

import dao.ImportSlipDetailDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SlipInfo;
import model.ImportSlipDetail; // Import lớp ImportSlipDetail

@WebServlet(name = "ImportSlipDetailServlet", urlPatterns = {"/ImportSlipDetailServlet"})
public class ImportSlipDetailServlet extends HttpServlet {

    private final String SLIP_DETAIL_VIEW = "ImportSlipView.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = SLIP_DETAIL_VIEW;
        try {
            int importSlipId = Integer.parseInt(request.getParameter("importSlipId"));
            ImportSlipDetailDAO dao = new ImportSlipDetailDAO();
            
            // 1. Lấy và lưu các tham số lọc (Supplier ID, From Date, End Date)
            request.setAttribute("SUPPLIER_ID_PARAM", request.getParameter("sId"));
            request.setAttribute("FROM_DATE_PARAM", request.getParameter("fDate"));
            request.setAttribute("END_DATE_PARAM", request.getParameter("eDate"));
            
            // 2. Lấy thông tin chi tiết hóa đơn
            List<ImportSlipDetail> detailList = dao.getImportSlipDetails(importSlipId);
            request.setAttribute("DETAIL_LIST", detailList);
            
            // 3. TÍNH TỔNG TIỀN THỰC TẾ TỪ CHI TIẾT
            float calculatedTotal = 0;
            for (ImportSlipDetail detail : detailList) {
                // subTotal = quantity * importPrice
                calculatedTotal += detail.getQuantity() * detail.getImportPrice(); 
            }
            request.setAttribute("CALCULATED_TOTAL", calculatedTotal); // <-- LƯU TỔNG TIỀN ĐÚNG
            
            // 4. Lấy thông tin chung của hóa đơn
            SlipInfo info = dao.getImportSlipInfo(importSlipId);
            request.setAttribute("SLIP_INFO", info);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Lỗi xử lý chi tiết hóa đơn: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}