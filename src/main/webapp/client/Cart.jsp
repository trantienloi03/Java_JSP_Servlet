<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart Details</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
	    table img {
	        width: 100px;
	        height: 80px;
	        object-fit: cover;
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
	 <jsp:include page="../layout/Header.jsp"/>
    <div class="container my-5">
        <!-- Page Title -->
        <h1 class="mb-4 text-center">Shopping Cart</h1>

        <!-- Cart Table -->
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead class="table-primary text-center">
                    <tr>
                        <th>Image</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody id="cart-items">
                    <!-- Example Row -->
                    <c:if test="${empty lstCartDetail }">
                    	<tr>
                    		<td colspan="6">Giỏ hàng trống</td>
                    	</tr>
                    </c:if>
                    <c:forEach var="cartDetail" items="${lstCartDetail }">
	                    <tr>
	                        <td>
	                        	<img alt="image" src="${cartDetail.getImage() }">
	                        </td>
	                        <td>${cartDetail.getProductName() }</td>
	                        <td>
			                    <fmt:formatNumber type ="number" value = "${cartDetail.getPrice() }" /> VND
		                     </td>
	                        <td class="text-center">
	                            <div class="input-group">
	                            <form action="EditCart" method="get" class="form-inline">	
								    <input type="hidden" name="cartID" value="${cartDetail.getCartID() }" /> 
								    <input type="hidden" name="productID" value="${cartDetail.getProductID() }" />
								    <input type="hidden" name="action" value="edit" />                               
								    <input type="number" name="quantity" class="form-control text-center quantity" value="${cartDetail.getQuantity() }" min="1">
								    <button type="submit" class="btn btn-primary">edit</button>
								</form>
	                            </div>
	                        </td>
	                        <td>
	                        	<fmt:formatNumber type ="number" value = "${cartDetail.getTotal() }" /> VND
	                        </td>
	                        <td class="text-center">
	                            <a href="EditCart?cartID=${cartDetail.getCartID()}&productID=${cartDetail.getProductID()}&action=delete" class="btn btn-sm btn-danger">Remove</a>
	                        </td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Cart Summary -->
        <c:if test="${not empty lstCartDetail }">
           <div class="row mt-4">
            <div class="col-md-6 offset-md-6">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center">Cart Summary</h4>
                        <hr>
                        <div class="d-flex justify-content-between">
                            <span>Subtotal:</span>
                            <fmt:formatNumber type ="number" value = "${Total}" /> VND
                        </div>
                        <div class="d-flex justify-content-between fw-bold">
                            <span>Total:</span>
                            <fmt:formatNumber type ="number" value = "${Total}" /> VND
                        </div>
                        <hr>
                        <a href="ConfirmOrder" class="btn btn-primary w-100 mt-2">Proceed to Checkout</a>
                    </div>
                </div>
            </div>
          </div>	
        </c:if>
        
    </div>
<jsp:include page="../layout/Footer.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
