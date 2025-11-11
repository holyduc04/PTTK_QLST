<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%! 
    // HÀM MINH HỌA CHUYỂN TIỀN SANG CHỮ
    private String formatNumberToText(long number) {
        if (number == 0) return "Không đồng";
        String[] units = {"", "nghìn", "triệu", "tỷ"};
        String result = "";
        int unitIndex = 0;
        
        while (number > 0) {
            long segment = number % 1000;
            if (segment > 0) {
                result = String.format(" %d %s ", segment, units[unitIndex]) + result;
            }
            number /= 1000;
            unitIndex++;
        }
        return result.trim() + " đồng";
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>HÓA ĐƠN NHẬP HÀNG</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <style>
        /* CSS BỔ SUNG: ÁP DỤNG HỘP CHỨA MỚI ĐỂ CĂN GIỮA TOÀN BỘ NỘI DUNG */
        .invoice-box-wrapper {
            background-color: #f4f4f9;
            padding: 30px; 
            box-sizing: border-box;
            width: 100%;
            display: flex;
            flex-direction: column; 
            align-items: center; 
        }
        
        .invoice-box {
            padding: 30px;
            border: 1px solid #eee;
            box-shadow: 0 0 10px rgba(0, 0, 0, .15);
            background: white; 
            margin-bottom: 20px; 
            max-width: 1000px; /* Kích thước khung to hơn */
            width: 95%;      
        }

        /* CẤU TRÚC BẢNG BỊ THIẾU/MẤT ĐỊNH DẠNG */
        .invoice-table { width: 100%; border-collapse: collapse; margin-top: 20px; margin-bottom: 20px; font-size: 14px; }
        .invoice-table th, .invoice-table td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        .invoice-table th { background-color: #f2f2f2; text-align: center; }
        .total-row td { font-weight: bold; text-align: right !important; background-color: #f9f9f9; } 
        
        /* CĂN CHỈNH NGÀY/CHỮ KÝ */
        .signature-area { display: flex; justify-content: space-between; margin-top: 20px; text-align: center; position: relative; }
        .signature-item { width: 45%; }
        .date-container { width: 100%; text-align: right; margin-top: 5px; margin-bottom: 5px; }
        .date-text-wrapper { width: 45%; margin-left: 55%; text-align: center; font-style: italic; font-weight: bold; display: block; }
    </style>
</head>
<body>
    <c:set var="detailList" value="${requestScope.DETAIL_LIST}"/>
    <c:set var="info" value="${requestScope.SLIP_INFO}"/>
    
    <c:set var="totalMoney" value="${requestScope.CALCULATED_TOTAL}" scope="page"/> <%-- SỬ DỤNG GIÁ TRỊ ĐÃ TÍNH TOÁN --%>
    
    <div class="invoice-box-wrapper"> 
        
        <div class="invoice-box">
            <h2 style="color: #007bff; border-bottom: 2px solid #007bff; padding-bottom: 10px; margin-bottom: 30px; text-align: center;">HÓA ĐƠN NHẬP HÀNG</h2>
            
            <div class="invoice-header">
                <p class="info-row">Tên siêu thị: <span>SIÊU THỊ ĐIỆN MÁY QLST</span></p>
                <p class="info-row">Địa chỉ: <span>96A Đ. Trần Phú, P. Mộ Lao, Hà Đông</span></p>
                <p class="info-row">Tên nhà cung cấp: <span>${info.supplierName}</span></p>
                
                <p class="info-row">Tên NV kho: <span>${info.warehouseStaffName}</span></p>
                <p class="info-row">Mã NV kho: <span>${info.warehouseStaffCode}</span></p>
            </div>
            
            <table class="invoice-table">
                <thead>
                    <tr>
                        <th style="width: 5%;">STT</th>
                        <th style="width: 15%;">Mã hàng</th> 
                        <th style="width: 35%;">Tên hàng</th>
                        <th style="width: 10%;">Số lượng</th>
                        <th style="width: 15%;">Đơn giá nhập</th>
                        <th style="width: 20%;">Thành tiền</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="detail" items="${detailList}" varStatus="loop">
                        <tr>
                            <td style="text-align: center;">${loop.index + 1}</td>
                            <td style="text-align: center;">${detail.productCode}</td> 
                            <td>${detail.productName} (${detail.productUnit})</td> 
                            <td style="text-align: right;">
                                <fmt:formatNumber value="${detail.quantity}" pattern="#,##0"/>
                            </td>
                            <td style="text-align: right;">
                                <fmt:formatNumber value="${detail.importPrice}" pattern="#,##0"/> VNĐ
                            </td>
                            <td style="text-align: right;">
                                <fmt:formatNumber value="${detail.quantity * detail.importPrice}" pattern="#,##0"/> VNĐ
                            </td>
                        </tr>
                    </c:forEach>
                    
                    <tr class="total-row">
                        <td colspan="4" style="text-align: right; border: none;"></td>
                        <td style="text-align: right; border: none;">TỔNG CỘNG:</td>
                        <td style="text-align: right;">
                            <fmt:formatNumber value="${requestScope.CALCULATED_TOTAL}" pattern="#,##0"/> VNĐ
                        </td>
                    </tr>
                </tbody>
            </table>

            <div class="invoice-footer">
                
                <p style="text-align: left; font-weight: normal;">Tổng tiền (Bằng chữ): <span>
                    <%
                        java.lang.Number totalValue = (java.lang.Number) pageContext.getAttribute("totalMoney");
                        long totalLong = totalValue.longValue();
                    %>
                    <%= formatNumberToText(totalLong) %>
                </span></p>
                
                <div class="date-container">
                    <span class="date-text-wrapper">
                        Ngày <fmt:formatDate value="${info.date}" pattern="dd"/> tháng <fmt:formatDate value="${info.date}" pattern="MM"/> năm <fmt:formatDate value="${info.date}" pattern="yyyy"/>
                    </span>
                </div>
            </div>
            
            <div class="signature-area">
                <div class="signature-item">
                    <p style="font-weight: bold; margin-bottom: 0;">Nhà cung cấp</p>
                    <p>(Kí tên, đóng dấu)</p>
                </div>
                <div class="signature-item">
                    <p style="font-weight: bold; margin-bottom: 0;">Nhân viên kho</p>
                    <p>(Kí tên)</p>
                </div>
            </div>
        </div>

        <br/>
        <div style="text-align: center;">
            <a href="ImportSlipServlet?supplierId=${requestScope.SUPPLIER_ID_PARAM}&fromDate=${requestScope.FROM_DATE_PARAM}&endDate=${requestScope.END_DATE_PARAM}" class="btn-quaylai">Quay lại</a>
        </div>
    </div>
</body>
</html>