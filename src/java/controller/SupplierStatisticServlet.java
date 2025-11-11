package controller;

import dao.SupplierStatisticDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SupplierStatistic;

@WebServlet(name = "SupplierStatisticServlet", urlPatterns = {"/SupplierStatisticServlet"})
public class SupplierStatisticServlet extends HttpServlet {

    private final String SUPPLIER_STAT_VIEW = "SupplierImportQuantityStatisticView.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String url = SUPPLIER_STAT_VIEW;
        
        try {
            SupplierStatisticDAO dao = new SupplierStatisticDAO();
            List<SupplierStatistic> list;
            
            String fromDate = request.getParameter("txtFromDate");
            String endDate = request.getParameter("txtEndDate");
            
            if ("filter".equals(action) && fromDate != null && !fromDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                list = dao.getSupplierStatisticByQuantityImportWithDate(fromDate, endDate); 
            } else {
                list = dao.getSupplierStatisticByQuantityImport(); 
            }
            
            request.setAttribute("STAT_LIST", list);
            request.setAttribute("FROM_DATE", fromDate);
            request.setAttribute("END_DATE", endDate);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Lỗi xử lý thống kê: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}