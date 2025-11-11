<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TRANG CHỦ QUẢN LÝ</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <style>
        /* CSS nội tuyến để căn chỉnh nút chức năng ở giữa */
        .function-area {
            text-align: center;
            padding: 30px 0;
            border-top: 1px solid #eee;
        }
    </style>
</head>
<body>
    <c:set var="user" value="${sessionScope.LOGIN_USER}"/>
    
    <%-- Kiểm tra vai trò: Nếu không phải ManagementStaff, chuyển về trang login --%>
    <c:if test="${empty user || user.role ne 'ManagementStaff'}">
        <c:redirect url="login.jsp"/> 
    </c:if>

    <div class="main-container">
        
        <div class="top-right-link">
             <a href="LogoutServlet" class="logout-btn">Đăng Xuất</a>
        </div>
        
        <h2 style="text-align: center; margin-top: 0;">TRANG CHỦ QUẢN LÝ</h2>
        
        <p style="font-size: 1.1em;">Xin chào, <strong>${user.name}</strong>!</p>
        
        <hr/>
        
        <h3>Chức năng Quản lý</h3>
        
        <div class="function-area">
            <%-- Dựa trên tài liệu, Nhân viên quản lí chọn menu xem báo cáo để chuyển đến trang chọn loại báo cáo [cite: 9] --%>
            <a href="SelectReportMenuView.jsp" class="main-function-btn">Xem báo cáo</a>
        </div>
        
    </div>
</body>
</html>