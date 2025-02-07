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
    	.form-inline {
	    display: flex;
	    align-items: center; /* Căn giữa theo chiều dọc */
	    gap: 10px; /* Khoảng cách giữa các phần tử */
		}
		
		.form-inline .form-control {
		    width: auto; /* Để input không chiếm toàn bộ chiều ngang */
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
            <div class="d-flex justify-content-between align-items-center mb-3">
			        <h4>Quản lý Đơn Hàng</h4>
			    </div>
            <div class="container my-5">
    			<nav aria-label="breadcrumb">
				        <ol class="breadcrumb">
				            <li class="breadcrumb-item" ><a href="Admin_OrderController">Order</a></li>
				            <li class="breadcrumb-item active" aria-current="page">Detail</li>
				        </ol>
				    </nav>
        <!-- Thông tin khách hàng -->
        <div class="card mb-4">
            <div class="card-header bg-primary text-white">
                <h4>Customer Information</h4>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>Full Name:</strong> ${user.getFullName() }</p>
                        <p><strong>Phone:</strong> ${user.getPhone() }</p>
                        <p><strong>Address:</strong> ${user.getAddress() }</p>
                    </div>
                    <div class="col-md-6">
                        <p><strong>Order ID:</strong> ${orderID }</p>
                        <p><strong>Total Amount:</strong> <fmt:formatNumber type="number" value="${Total }" /> VND</p>
                        <p><strong>Status:</strong> 
						    <c:choose>
						        <c:when test="${statusID == 1}">
						            <span class="text-warning">Chờ xác nhận</span>
						        </c:when>
						        <c:when test="${statusID == 2}">
						            <span class="text-info">Đang giao</span>
						        </c:when>
						        <c:when test="${statusID == 3}">
						            <span class="text-danger">Bị hủy</span>
						        </c:when>
						        <c:otherwise>
						            <span class="text-secondary">Không xác định</span>
						        </c:otherwise>
						    </c:choose>
						</p>
					</div>
                </div>
            </div>
        </div>

        <!-- Bảng thông tin sản phẩm -->
        <div class="table-responsive">
            <h4 class="mb-3">Product Details</h4>
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>Product Name</th>
                        <th>Product Image</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                        <c:if test="${statusID == 1 }">
	                        <th>Actions</th>
                        </c:if>
                        
                        
                    </tr>
                </thead>
                <tbody>
                   <c:forEach var="detail" items="${lst }">
                   	<tr>
                        <td>${detail.getProductName() }</td>
                        <td>
                            <img src="${detail.getImage() }" alt="MacBook" class="img-thumbnail" style="width: 100px;">
                        </td>
                        <c:if test="${statusID == 2 || statusID ==3 }"><td>${detail.getQuantity() }</td></c:if>
                        <c:if test="${statusID == 1 }">
                        	<td class="text-center">
	                            <div class="input-group">
	                            <form action="Admin_EditOrderDetailController" method="get" class="form-inline">	
								    <input type="hidden" name="orderDetailID" value="${detail.getOrderDetailID() }" /> 
								    <input type="hidden" name="productID" value="${detail.getProductID() }" />
								    <input type="hidden" name="orderID" value="${detail.getOrderID() }" />
								    <input type="hidden" name="action" value="edit" />                               
								    <input type="number" name="quantity" class="form-control text-center quantity" value="${detail.getQuantity() }" min="1">
								    <button type="submit" class="btn btn-primary">edit</button>
								</form>
	                            </div>
	                        </td>
                        </c:if>
                        <td><fmt:formatNumber type="number" value="${detail.getPrice() }" /> VND</td>
                        <td><fmt:formatNumber type="number" value="${detail.getTotal() }" /> VND</td>
                        <c:if test="${statusID == 1 }">
	                        <td class="text-center">
		                            <a href="Admin_EditOrderDetailController?orderDetailID=${detail.getOrderDetailID()}&action=delete&orderID=${detail.getOrderID()}" class="btn btn-sm btn-danger">Remove</a>
		                     </td>
                        </c:if>
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
