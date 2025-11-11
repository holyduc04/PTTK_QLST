<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CHỌN XEM THỐNG KÊ</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <style>
        .report-menu-container {
            text-align: center;
            padding: 50px 0;
            display: flex;
            justify-content: center;
            gap: 20px;
        }
        .menu-btn {
            display: inline-block;
            background-color: #28a745; /* Màu xanh lá nổi bật */
            color: white;
            padding: 10px 25px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 16px;
            font-weight: bold;
            margin-top: 15px;
            transition: background-color 0.3s ease;
        }
        .menu-btn:hover {
            background-color: #d4edda;
            text-decoration: none;
        }
        .btn-quaylai-container {
            text-align: right;
            margin-top: 50px;
        }
    </style>
</head>
<body>
    <c:set var="user" value="${sessionScope.LOGIN_USER}"/>
    
    <div class="main-container">
        <h2 style="text-align: center;">CHỨC NĂNG THỐNG KÊ</h2>

        <p style="font-size: 1.1em; padding-bottom: 5px; border-bottom: 1px solid #ddd;">
            Xin chào, <strong>${user.name}</strong>!
        </p>
        
        <h3 style="padding-top: 5px; padding-bottom: 5px; margin-bottom: 15px; border-bottom: 1px solid #ddd;">
            Chọn xem thống kê
        </h3>
        
        <div class="report-menu-container">
            <a href="SupplierStatisticServlet" class="menu-btn">Nhà cung cấp</a>
            
            <a href="#" class="menu-btn" onclick="alert('Chức năng Mặt hàng chưa được triển khai.'); return false;">Mặt hàng</a>
            <a href="#" class="menu-btn" onclick="alert('Chức năng Doanh thu chưa được triển khai.'); return false;">Doanh thu</a>
        </div>

        <div class="btn-quaylai-container">
            <a href="ManagerMainView.jsp" class="btn-quaylai">Quay lại</a>
        </div>
    </div>
</body>
</html>