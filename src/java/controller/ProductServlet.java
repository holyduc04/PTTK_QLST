package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

    private final String SEARCH_PRODUCT_VIEW = "SearchProductView.jsp";
    private final String PRODUCT_DETAIL_VIEW = "SearchProductDetailView.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Đảm bảo encoding cho tiếng Việt
        request.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        String url = SEARCH_PRODUCT_VIEW;
        
        try {
            ProductDAO productDAO = new ProductDAO();
            List<Product> list = null;
            
            if (action == null || "viewList".equals(action)) {
                // 1. Chức năng Hiển thị danh sách ban đầu (getProduct())
                list = productDAO.getProduct();
                
            } else if ("searchByName".equals(action)) {
                // 2. Chức năng Tìm kiếm theo tên
                String searchName = request.getParameter("txtSearchName");
                
                if (searchName != null && !searchName.trim().isEmpty()) {
                    // Nếu có từ khóa, thực hiện tìm kiếm
                    list = productDAO.getProductByName(searchName.trim());
                    request.setAttribute("SEARCH_VALUE", searchName);
                } else {
                    // Nếu chuỗi rỗng, hiển thị lại toàn bộ danh sách ban đầu
                    list = productDAO.getProduct();
                    request.setAttribute("SEARCH_VALUE", ""); // Xóa giá trị cũ khỏi ô input
                }
                
                url = SEARCH_PRODUCT_VIEW;
                
            } else if ("viewDetail".equals(action)) {
                // 3. Chức năng Xem chi tiết (giữ nguyên)
                int productId = Integer.parseInt(request.getParameter("productId"));
                Product product = productDAO.getProductDetail(productId);
                
                if (product != null) {
                    request.setAttribute("PRODUCT_DETAIL", product);
                    url = PRODUCT_DETAIL_VIEW;
                } else {
                    request.setAttribute("ERROR", "Không tìm thấy chi tiết mặt hàng.");
                    list = productDAO.getProduct(); // Quay lại danh sách
                    url = SEARCH_PRODUCT_VIEW; 
                }
            }
            
            request.setAttribute("PRODUCT_LIST", list);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Lỗi xử lý yêu cầu sản phẩm: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}