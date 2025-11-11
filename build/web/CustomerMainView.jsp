<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TRANG CHỦ KHÁCH HÀNG</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <style>
        /* CSS nội tuyến để căn chỉnh nút tìm kiếm ở giữa */
        .function-area {
            text-align: center;
            padding: 30px 0;
            border-top: 1px solid #eee;
            
            /* THÊM FLEXBOX VÀ GAP ĐỂ TẠO KHOẢNG CÁCH */
            display: flex;
            justify-content: center;
            gap: 20px; /* Tăng khoảng cách giữa các nút */
        }
    </style>
</head>
<body>
    <c:set var="user" value="${sessionScope.LOGIN_USER}"/>
    <c:if test="${empty user || user.role ne 'Customer'}">
        <c:redirect url="login.jsp"/> 
    </c:if>

    <div class="main-container">
        
        <div class="top-right-link">
             <a href="LogoutServlet" class="logout-btn">Đăng Xuất</a>
        </div>
        
        <h2 style="text-align: center; margin-top: 0;">TRANG CHỦ KHÁCH HÀNG</h2>
        
        <p style="font-size: 1.1em;">Xin chào, <strong>${user.name}</strong>!</p>
        
        <hr/>
        
        <h3>Chức năng Khách hàng</h3>
        
        <div class="function-area">
            <a href="SearchProductView.jsp" class="main-function-btn" >Tìm kiếm mặt hàng</a>
            <a href="#" class="main-function-btn" onclick="alert('Chức năng Đăng kí thành viên chưa được triển khai.'); return false;" >Đăng kí thành viên</a>
            <a href="#" class="main-function-btn" onclick="alert('Chức năng Đặt hàng trực tuyến chưa được triển khai.'); return false;" >Đặt hàng trực tuyến</a>
            
        </div>
        
        <%-- <hr/> --%>
        <%-- <a href="LogoutServlet">Đăng Xuất</a> --%>
        
    </div>
</body>
</html>