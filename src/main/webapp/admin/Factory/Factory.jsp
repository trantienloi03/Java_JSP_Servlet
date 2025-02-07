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
				            <li class="breadcrumb-item active" aria-current="page">Quản lý loại hàng</li>
				        </ol>
				    </nav>
				
				    <!-- Title and Add Button -->
				    <div class="d-flex justify-content-between align-items-center mb-3">
				        <h1>Quản lý loại hàng</h1>
				        <a class="btn btn-primary" href="ActionController?action=add">
				            <i class="bi bi-plus"></i> Thêm mới
				        </a>
				    </div>
				
				    <!-- Factory Table -->
				    <div class="table-responsive">
				        <table class="table table-bordered table-hover">
				            <thead class="table-dark">
				                <tr>
				                    <th>Tên loại hàng</th>
				                    <th>Xử lý</th>
				                </tr>
				            </thead>
				            <tbody>
				            <c:forEach var="factory" items="${lstFactory }">
				                <tr>
				                    <td>${factory.getFactoryName() }</td>
				                    <td>
				                        <a class="btn btn-warning btn-sm" href="ActionController?action=edit&id=${factory.getFactoryID() }">Sửa</a>
				                        <a class="btn btn-danger btn-sm" href="ActionController?action=delete&id=${factory.getFactoryID() }" onclick="return confirm('Bạn có chắc chắn muốn xóa loại hàng này?')">Xóa</a>
				                    </td>
				                </tr>
				           </c:forEach>
				            </tbody>
				        </table>
				    </div>
				
				    <!-- Pagination -->
				    <nav aria-label="Page navigation">
		         <ul class="pagination justify-content-center">
		            <c:forEach var="i" begin="1" end="${Page}">
		               <li class="page-item ${index == i ? 'active' : ''}">
		                  <a class="page-link" href="Admin_FactoryController?index=${i}">${i}</a>
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
