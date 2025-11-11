package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // 1. Lấy session hiện tại
            HttpSession session = request.getSession(false); // false: không tạo session mới nếu chưa có
            
            if (session != null) {
                // 2. Hủy session (hủy bỏ LOGIN_USER)
                session.invalidate(); 
            }
            
        } catch (Exception e) {
            System.err.println("Lỗi xử lý đăng xuất: " + e.getMessage());
        } finally {
            // 3. Chuyển hướng về trang đăng nhập
            response.sendRedirect(LOGIN_PAGE);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Đăng xuất thường được xử lý bằng GET, nhưng nên gọi lại doGet để đảm bảo
        doGet(request, response);
    }
}