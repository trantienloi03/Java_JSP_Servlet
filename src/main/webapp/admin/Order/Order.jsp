<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="layout/admin.css" rel="stylesheet">
    <style type="text/css">
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
    <!-- Header -->
	<jsp:include page="../../layout/Admin_Header.jsp"></jsp:include>

    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <jsp:include page="../../layout/Admin_Slidebar.jsp"></jsp:include>

            <!-- Content -->
            <main id="content" class="col-md-10 ms-sm-auto col-lg-10 content">
               <div class="container">
			    <!-- Breadcrumb -->
			    <nav aria-label="breadcrumb">
			        <ol class="breadcrumb">
			            <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
			            <li class="breadcrumb-item active" aria-current="page">Quản lý Đơn Hàng</li>
			        </ol>
			    </nav>
			
			    <!-- Title and Add Button -->
			    <div class="d-flex justify-content-between align-items-center mb-3">
			        <h4>Quản lý Đơn Hàng</h4>
			    </div>
			
			    <!-- Order Table -->
			    <div class="table-responsive">
			        <table class="table table-bordered table-hover">
			            <thead class="table-dark">
			                <tr>
			                    <th>Mã Đơn Hàng</th>
			                    <th>Khách Hàng</th>
			                    <th>Ngày Đặt</th>
			                    <th>Tổng tiền</th>
			                    <th>Tình Trạng</th>
			                    <th>Hành động</th>
			                </tr>
			            </thead>
			            <tbody>
			            	<c:forEach var="order" items="${lstOrder}">
			            	<tr>
			                    <td>${order.getOrderID() }</td>
			                    <td>${order.getUserName() }</td>
			                    <td>${order.getDateTime()}</td>
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
                                <a href="Admin_OrderController?action=view&orderID=${order.getOrderID() }&userID=${order.getUserID()}" class="btn btn-sm btn-primary">
                                    <i class="fas fa-eye"></i> Xem
                                </a>
                                <c:if test="${order.getStatusID() == 1}">
                                    <a href="Admin_OrderController?action=cancel&orderID=${order.getOrderID() }" class="btn btn-sm btn-danger">
                                        <i class="fas fa-times"></i> Hủy
                                    </a>
                                    <a href="Admin_OrderController?action=access&orderID=${order.getOrderID() }" class="btn btn-sm btn-success">
									    <i class="fas fa-check"></i> Xác nhận
									</a>
                                </c:if>
                            </td>
			                </tr>
			            	</c:forEach>
			            </tbody>
			        </table>
			    </div>
			
			</div>

            </main>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="../../layout/Admin_Footer.jsp"></jsp:include>
</body>
</html>
