<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TÌM KIẾM MẶT HÀNG</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <style>
        /* CẬP NHẬT: Căn chỉnh Form Tìm kiếm */
        .search-form {
            display: flex;
            align-items: center; /* Căn chỉnh theo chiều dọc */
            justify-content: center;
            margin-bottom: 20px;
        }
        .search-form label {
            margin-right: 10px;
            white-space: nowrap;
        }
        .search-form input[type="text"] {
            width: 300px; /* Vẫn giữ chiều rộng phù hợp */
            margin-right: 10px;
            /* Bỏ display: inline-block; vì flex đã xử lý */
        }
        .search-form input[type="submit"] {
            width: auto;
            padding: 8px 15px;
        }
        
        /* CẬP NHẬT: Tối ưu hóa chiều rộng các cột và style bảng */
        .data-table th, .data-table td {
            padding: 8px 5px;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .data-table th {
            background-color: #f0f0f0;
        }
        
        /* Định nghĩa chiều rộng từng cột (tổng 100%): */
        .data-table col.stt { width: 5%; }
        .data-table col.tenmh { width: 25%; } 
        .data-table col.mamh { width: 10%; } 
        .data-table col.ncc { width: 15%; } 
        .data-table col.gia { width: 15%; } 
        .data-table col.mota { width: 25%; }
        .data-table col.chitiet { width: 5%; }
        
        .data-table td.mota {
            white-space: normal; 
            text-align: left !important;
        }
        .data-table td.tenmh {
             text-align: left !important;
        }
        
        /* CẬP NHẬT: Căn giá trị Giá (VNĐ) vào giữa ô */
        .data-table td.gia {
             text-align: center !important; 
        }
    </style>
</head>
<body>
    <div class="main-container">
        <h2 style="text-align: center;">TÌM KIẾM MẶT HÀNG</h2>

        <form action="ProductServlet" method="GET" class="search-form">
            <input type="hidden" name="action" value="searchByName"/>
            <label for="searchName">Nhập tên mặt hàng:</label>
            <input type="text" id="searchName" name="txtSearchName" 
                   value="${requestScope.SEARCH_VALUE != null ? requestScope.SEARCH_VALUE : ''}" />
            <input type="submit" value="Tìm kiếm"/>
        </form>
        
        <hr/>
        
        <h3 style="text-align: center;">DANH SÁCH MẶT HÀNG</h3>
        <c:set var="list" value="${requestScope.PRODUCT_LIST}"/>
        <c:if test="${empty list}">
            <p style="text-align: center;">Không tìm thấy mặt hàng nào hoặc chưa có dữ liệu.</p>
        </c:if>

        <c:if test="${not empty list}">
            <table class="data-table" border="1" style="width:100%; text-align: center; border-collapse: collapse;">
                <colgroup>
                    <col class="stt">
                    <col class="tenmh">
                    <col class="mamh">
                    <col class="ncc">
                    <col class="gia">
                    <col class="mota">
                    <col class="chitiet">
                </colgroup>
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên MH</th>
                        <th>Mã MH</th>
                        <th>NCC</th>
                        <th>Giá (VNĐ)</th>
                        <th>Mô tả</th>
                        <th>Chi Tiết</th> 
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${list}" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td class="tenmh">${product.name}</td>
                        <td>${product.code}</td>
                        <td>${product.supplierName}</td>
                        <td class="gia">
                            <fmt:formatNumber value="${product.salePrice}" type="number" maxFractionDigits="0"/>
                        </td>
                        <td class="mota">${product.description}</td>
                        <td>
                            <a href="ProductServlet?action=viewDetail&productId=${product.id}">Xem</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <br/>
        <div style="text-align: right;">
            <a href="CustomerMainView.jsp" class="btn-quaylai">Quay lại</a>
        </div>
    </div>
    
    <c:if test="${requestScope.PRODUCT_LIST == null}">
        <c:redirect url="ProductServlet?action=viewList"/>
    </c:if>
</body>
</html>