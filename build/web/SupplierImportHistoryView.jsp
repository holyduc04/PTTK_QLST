<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>LỊCH SỬ NHẬP HÀNG</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <style>
        .data-table td {
            white-space: nowrap;
        }
    </style>
</head>
<body>
    <div class="main-container">
        <h2 style="text-align: center;">THỐNG KÊ CÁC LẦN NHẬP HÀNG CỦA NHÀ CUNG CẤP</h2>
        
        <p><strong>Nhà cung cấp:</strong> ${requestScope.SUPPLIER_NAME}</p>
        <p><strong>Khoảng thời gian:</strong> ${requestScope.FROM_DATE} đến ${requestScope.END_DATE}</p>

        <c:set var="list" value="${requestScope.SLIP_LIST}"/>
        <c:set var="grandTotal" value="${0}"/> <%-- Khởi tạo biến tổng cộng --%>

        <c:if test="${empty list}">
            <p style="text-align: center; color: red;">Không tìm thấy lần nhập hàng nào trong khoảng thời gian này.</p>
        </c:if>

        <c:if test="${not empty list}">
            <table class="data-table" border="1" style="width:100%;">
                <thead>
                    <tr>
                        <th style="width: 5%;">STT</th>
                        <th style="width: 15%;">Mã NH</th>
                        <th style="width: 20%;">Tên NV Kho</th>
                        <th style="width: 20%;">Ngày nhập</th>
                        <th style="width: 30%;">Tổng tiền (VNĐ)</th>
                        <th style="width: 10%;"> </th> 
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="slip" items="${list}" varStatus="status">
                    <%-- TÍNH TỔNG CỘNG TRONG VÒNG LẶP --%>
                    <c:set var="grandTotal" value="${grandTotal + slip.totalPrice}"/>
                    
                    <tr>
                        <td>${status.count}</td>
                        <td>NH${slip.id}</td>
                        <td>${slip.warehouseStaffName}</td>
                        <td><fmt:formatDate value="${slip.date}" pattern="dd-MM-yyyy"/></td>
                        
                        <%-- ĐỊNH DẠNG TỔNG TIỀN: Sử dụng pattern #,##0 --%>
                        <td style="text-align: right;">
                            <fmt:formatNumber value="${slip.totalPrice}" pattern="#,##0"/>
                        </td>
                        
                        <td>
                            <%-- LIÊN KẾT XEM: Truyền kèm các tham số lọc --%>
                            <a href="ImportSlipDetailServlet?importSlipId=${slip.id}&sId=${requestScope.SUPPLIER_ID}&fDate=${requestScope.FROM_DATE}&eDate=${requestScope.END_DATE}">Xem</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                
                <%-- DÒNG TỔNG CỘNG CUỐI BẢNG --%>
                <tfoot>
                    <tr style="background-color: #f0f0f0; border-top: 1px solid #007bff;">
                        <td colspan="4" style="text-align: right; font-weight: bold; padding: 10px;">
                            TỔNG CỘNG:
                        </td>
                        <td style="text-align: right; font-weight: bold; padding: 10px;">
                            <fmt:formatNumber value="${grandTotal}" pattern="#,##0"/> VNĐ
                        </td>
                        <td></td>
                    </tr>
                </tfoot>
            </table>
        </c:if>

        <br/>
        <div style="text-align: right;">
            <a href="SupplierStatisticServlet?action=filter&txtFromDate=${requestScope.FROM_DATE}&txtEndDate=${requestScope.END_DATE}" class="btn-quaylai">Quay lại</a>
        </div>
    </div>
</body>
</html>