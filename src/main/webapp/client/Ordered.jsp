<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lịch sử mua hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        h2 {
            color: #0d6efd;
        }
        .table th {
            text-align: center;
        }
        .table td {
            vertical-align: middle;
            text-align: center;
        }
        .no-data {
            text-align: center;
            color: #dc3545;
            font-weight: bold;
        }
        .status-pending {
            color: #ffc107;
            font-weight: bold;
        }
        .status-shipping {
            color: #28a745;
            font-weight: bold;
        }
        .status-cancelled {
            color: #dc3545;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <jsp:include page="../layout/Header.jsp"/>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Lịch sử mua hàng</h2>
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover">
                <thead class="table-primary">
                    <tr>
                        <th>Mã đơn hàng</th>
                        <th>Ngày mua</th>
                        <th>Số lượng</th>
                        <th>Tổng tiền</th>
                        <th>Trạng thái</th>
                        <th>Xử lý</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${empty lstOrder }">
                        <tr>
                            <td colspan="6" class="no-data">Không có dữ liệu</td>
                        </tr>
                    </c:if>
                    <c:forEach var="order" items="${lstOrder}">
                        <tr>
                            <td>${order.getOrderID()}</td>
                            <td>${order.getDateTime()}</td>
                            <td>${order.getSum()}</td>
                            <td>
                                <fmt:formatNumber type="number" value="${order.getTotalPrice()}" /> VND
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${order.getStatusID() == 1}">
                                        <span class="status-pending">Chờ xác nhận</span>
                                    </c:when>
                                    <c:when test="${order.getStatusID() == 2}">
                                        <span class="status-shipping">Đang giao</span>
                                    </c:when>
                                    <c:when test="${order.getStatusID() == 3}">
                                        <span class="status-cancelled">Đã hủy</span>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <a href="OrderController?action=view&orderID=${order.getOrderID() }" class="btn btn-sm btn-primary">
                                    <i class="fas fa-eye"></i> Xem
                                </a>
                                <c:if test="${order.getStatusID() == 1}">
                                    <a href="OrderController?action=cancel&orderID=${order.getOrderID() }" class="btn btn-sm btn-danger">
                                        <i class="fas fa-times"></i> Hủy
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <jsp:include page="../layout/Footer.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
