<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>THỐNG KÊ NHÀ CUNG CẤP</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <style>
        .filter-form {
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 20px;
        }
        .filter-form input[type="date"] {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin: 0 10px;
            width: 150px;
        }
        .filter-form input[type="submit"] {
            width: auto;
            padding: 8px 15px;
            background-color: #d4edda; 
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        
    </style>
</head>
<body>
    <div class="main-container">
        <h2 style="text-align: center;">THỐNG KÊ NHÀ CUNG CẤP THEO SỐ LƯỢNG HÀNG NHẬP</h2>

        <form action="SupplierStatisticServlet" method="GET" class="filter-form">
            <input type="hidden" name="action" value="filter"/>
            
            Từ ngày: <input type="date" name="txtFromDate" value="${requestScope.FROM_DATE}"/>
            Đến ngày: <input type="date" name="txtEndDate" value="${requestScope.END_DATE}"/>
            
            <input type="submit" value="Thống kê" class="main-filter-btn" style="margin-top: 0;"/>
            <!--<input type="submit" value="Thống kê"/>-->
        </form>
        
        <hr/>

        <h3 style="text-align: center; font-size: 1.1em;">Danh sách nhà cung cấp trong khoảng thời gian trên</h3>
        
        <c:set var="list" value="${requestScope.STAT_LIST}"/>
        <c:if test="${empty list}">
            <p style="text-align: center; color: red;">Không tìm thấy dữ liệu thống kê nào.</p>
        </c:if>

        <c:if test="${not empty list}">
    <table class="data-table" border="1" style="width:100%;">
        <thead>
            <tr>
                <th style="width: 5%;">STT</th>
                <th style="width: 15%;">Mã NCC</th>
                <th style="width: 30%;">Tên NCC</th>
                <th style="width: 15%;">Số lần nhập hàng</th>
                <th style="width: 15%;">Tổng số lượng hàng</th>
                <th style="width: 15%;">Tổng tiền (VNĐ)</th>
                <th style="width: 5%;"> </th> 
            </tr>
        </thead>
        <tbody>
        <c:forEach var="stat" items="${list}" varStatus="status">
            <%-- TÍNH TỔNG CỘNG TRONG VÒNG LẶP --%>
            <c:set var="grandTotal" value="${grandTotal + stat.totalMoney}"/>
            
            <tr>
                <td>${status.count}</td>
                <td>NCC${stat.id}</td>
                <td style="text-align: left;">${stat.name}</td>
                <td>${stat.importCount}</td>
                <td>${stat.totalQuantity}</td>
                <td style="text-align: right;">
                    <fmt:formatNumber value="${stat.totalMoney}" type="number" maxFractionDigits="0"/>
                </td>
                <td>
                    <a href="ImportSlipServlet?supplierId=${stat.id}&supplierName=${stat.name}&fromDate=${requestScope.FROM_DATE}&endDate=${requestScope.END_DATE}">Xem</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        
        <%-- DÒNG TỔNG CỘNG MỚI --%>
        <tfoot>
            <tr style="background-color: #f0f0f0; border-top: 1px solid #007bff;">
                <td colspan="5" style="text-align: right; font-weight: bold; padding: 10px;">
                    TỔNG CỘNG:
                </td>
                <td style="text-align: right; font-weight: bold; padding: 10px;">
                    <fmt:formatNumber value="${grandTotal}" type="number" maxFractionDigits="0"/> VNĐ
                </td>
                <td></td>
                </tr>
                </tfoot>
            </table>
        </c:if>

        <br/>
        <div style="text-align: right;">
            <a href="SelectReportMenuView.jsp" class="btn-quaylai">Quay lại</a>
        </div>
    </div>
</body>
</html>