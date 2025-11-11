<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>THÔNG TIN CHI TIẾT MẶT HÀNG</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <style>
        /* CSS cho bảng chi tiết sản phẩm */
        .detail-table {
            width: 80%; 
            margin: 20px auto;
            border-collapse: collapse;
        }
        .detail-table td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        .detail-table tr td:first-child {
            font-weight: bold;
            background-color: #f7f7f7;
            width: 30%;
        }
        
        /* CSS cho phần thêm vào giỏ hàng */
        .add-to-cart-section {
            display: flex; 
            justify-content: center; 
            align-items: center;
            padding: 15px 0;
            border-top: 1px solid #eee;
            margin-top: 15px;
        }
        .add-to-cart-section label {
            margin-right: 10px;
        }
        .add-to-cart-section input[type="submit"] {
             width: auto;
             padding: 8px 20px;
             margin-left: 10px;
             background-color: #dc3545; 
        }
         .add-to-cart-section input[type="submit"]:hover {
             background-color: #c82333;
        }
    </style>
</head>
<body>
    <div class="main-container">
        <h2 style="text-align: center;">THÔNG TIN CHI TIẾT MẶT HÀNG</h2>
        
        <c:set var="product" value="${requestScope.PRODUCT_DETAIL}"/>
        <c:if test="${product == null}">
            <p style="color:red; text-align: center;">Không có thông tin chi tiết sản phẩm.</p>
        </c:if>
        
        <c:if test="${product != null}">
            <table class="detail-table">
                <tr><td>Mã MH</td><td>${product.code}</td></tr>
                <tr><td>Tên MH</td><td>${product.name}</td></tr>
                <tr><td>NCC</td><td>${product.supplierName}</td></tr>
                <tr><td>Giá</td><td>
                    <fmt:formatNumber value="${product.salePrice}" type="number" maxFractionDigits="0"/> VNĐ
                </td></tr>
                <tr><td>Mô tả</td><td>${product.description}</td></tr>
            </table>

            <form action="CartServlet" method="POST" class="add-to-cart-section">
                <input type="hidden" name="productId" value="${product.id}">
                
                <label for="quantity">Nhập số lượng:</label>
                <input type="number" id="quantity" name="txtQuantity" min="1" max="${product.quantity}" 
                       value="1" style="width: 80px; text-align: center;"/>
                       
                <input type="submit" value="Thêm giỏ hàng"/>
                
            </form>
            <p style="color:gray; font-size: 0.8em; text-align: center;">(Số lượng tồn kho: ${product.quantity})</p>
        </c:if>

        <br/>
        <div style="text-align: right;">
            <a href="SearchProductView.jsp" class="btn-quaylai">Quay lại</a>
        </div>
    </div>
</body>
</html>