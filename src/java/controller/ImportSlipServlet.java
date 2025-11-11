package controller;

import dao.ImportSlipDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ImportSlip;

@WebServlet(name = "ImportSlipServlet", urlPatterns = {"/ImportSlipServlet"})
public class ImportSlipServlet extends HttpServlet {

    private final String HISTORY_VIEW = "SupplierImportHistoryView.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = HISTORY_VIEW;
        try {
            // Lấy tham số cần thiết từ trang thống kê
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));
            String supplierName = request.getParameter("supplierName"); 
            String fromDate = request.getParameter("fromDate");
            String endDate = request.getParameter("endDate");
            
            ImportSlipDAO dao = new ImportSlipDAO();
            List<ImportSlip> list = dao.getImportSlipBySupplier(supplierId, fromDate, endDate); 
            
            request.setAttribute("SLIP_LIST", list);
            request.setAttribute("SUPPLIER_ID", supplierId);
            request.setAttribute("SUPPLIER_NAME", supplierName);
            request.setAttribute("FROM_DATE", fromDate);
            request.setAttribute("END_DATE", endDate);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Lỗi xử lý lịch sử nhập hàng: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}