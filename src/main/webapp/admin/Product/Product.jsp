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
    <style>
    table img {
        width: 100px;
        height: 100px;
        object-fit: cover;
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
              
		<div class="container mt-2">
		        <!-- Breadcrumb -->
		        <nav aria-label="breadcrumb">
		            <ol class="breadcrumb">
		                <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
		                <li class="breadcrumb-item active" aria-current="page">Quản lý sản phẩm</li>
		            </ol>
		        </nav>
		
		        <!-- Title and Add Button -->
		        <div class="d-flex justify-content-between align-items-center mb-3">
		            <h1>Quản lý sản phẩm</h1>
		            <a class="btn btn-primary" href="XuLyController?action=add">
		                <i class="bi bi-plus"></i> Thêm mới
		            </a>
		        </div>
		
		        <!-- Product Table -->
		        <div class="table-responsive">
		            <table class="table table-bordered table-hover">
		                <thead class="table-dark">
		                    <tr>
		                        <th>Ảnh</th>
		                        <th>Tên sản phẩm</th>
		                        <th>Giá</th>
		                        <th>Loại hàng</th>
		                        <th>Hành động</th>
		                    </tr>
		                </thead>
		                <tbody>
		                <c:if test="${empty lstProduct }">
		                	<tr>
		                       <td colspan="6"></td> 
		                    </tr>
		                </c:if>
		                <c:forEach var="product" items="${lstProduct }">
		                    <tr>
		                        <td>
		                        <img alt="image" src="${product.getImage() }">
		                        </td>
		                        <td>${product.getProductName() }</td>
		                        <td>
		                        	${product.formatPrice(product.getPrice()) }
		                        </td>
		                        <td>${product.getFactory().getFactoryName() }</td>
		                        <td>
		                        	<a class="btn btn-primary btn-sm" href="XuLyController?action=detail&id=${product.getiD() }">xem</a>
		                            <a class="btn btn-warning btn-sm" href="XuLyController?action=edit&id=${product.getiD() }">Sửa</a>
		                            <a class="btn btn-danger btn-sm" href="XuLyController?action=delete&id=${product.getiD() }" onclick="return confirm('Bạn có chắc chắn muốn xóa loại hàng này?')">Xóa</a>
		                        </td>
		                    </tr>
		                  </c:forEach>
		                    <!-- Add more rows as needed -->
		                </tbody>
		            </table>
		        </div>
		
		        <!-- Pagination -->
				    <nav aria-label="Page navigation">
		         <ul class="pagination justify-content-center">
		            <c:forEach var="i" begin="1" end="${Page}">
		               <li class="page-item ${index == i ? 'active' : ''}">
		                  <a class="page-link" href="Admin_ProductController?index=${i}">${i}</a>
		               </li>
		            </c:forEach>
		         </ul>
		      </nav>
		    </div>
               
            </main>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="../../layout/Admin_Footer.jsp"></jsp:include>
</body>
</html>




