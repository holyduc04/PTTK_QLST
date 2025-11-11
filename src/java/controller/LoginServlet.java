package controller;

import dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String CUSTOMER_HOME = "CustomerMainView.jsp"; // Trang chủ Khách hàng
    private final String MANAGER_HOME = "ManagerMainView.jsp";   // Trang chủ Quản lý

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = LOGIN_PAGE;
        try {
            // 1. Lấy tham số từ form
            String userID = request.getParameter("user");
            String password = request.getParameter("password");
            
            // 2. Gọi DAO để kiểm tra đăng nhập
            UserDAO dao = new UserDAO();
            User user = dao.checkLogin(userID, password);
            
            if (user != null) {
                // 3. Đăng nhập thành công: Lưu vào Session và chuyển hướng
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", user);
                
                String role = user.getRole();
                
                if ("Customer".equals(role)) {
                    url = CUSTOMER_HOME;
                } else if ("ManagementStaff".equals(role)) {
                    url = MANAGER_HOME;
                } else {
                    // Xử lý các role khác: WarehouseStaff, SaleStaff [cite: 2]
                    url = LOGIN_PAGE; 
                    request.setAttribute("ERROR", "Vai trò của bạn chưa được hỗ trợ trên hệ thống.");
                }
            } else {
                // 4. Đăng nhập thất bại
                request.setAttribute("ERROR", "Tên đăng nhập hoặc mật khẩu không đúng.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Lỗi hệ thống: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}