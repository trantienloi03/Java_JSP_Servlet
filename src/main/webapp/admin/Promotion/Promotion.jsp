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
				            <li class="breadcrumb-item active" aria-current="page">Quản lý Khuyến Mãi</li>
				        </ol>
				    </nav>
				
				    <!-- Title and Add Button -->
				    <div class="d-flex justify-content-between align-items-center mb-3">
				        <h1>Quản lý Khuyến Mãi</h1>
				        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addPromotionModal">
				            <i class="bi bi-plus"></i> Thêm mới
				        </button>
				    </div>
				
				    <!-- Promotion Table -->
				    <div class="table-responsive">
				        <table class="table table-bordered table-hover">
				            <thead class="table-dark">
				                <tr>
				                    <th>#</th>
				                    <th>Tên Khuyến Mãi</th>
				                    <th>Phần trăm giảm</th>
				                    <th>Ngày bắt đầu</th>
				                    <th>Ngày kết thúc</th>
				                    <th>Hành động</th>
				                </tr>
				            </thead>
				            <tbody>
				                <tr>
				                    <td>1</td>
				                    <td>Giảm giá mùa hè</td>
				                    <td>10%</td>
				                    <td>2023-06-01</td>
				                    <td>2023-06-30</td>
				                    <td>
				                        <button class="btn btn-warning btn-sm">Sửa</button>
				                        <button class="btn btn-danger btn-sm">Xóa</button>
				                    </td>
				                </tr>
				            </tbody>
				        </table>
				    </div>
				
				    <!-- Pagination -->
				    <nav aria-label="Page navigation">
				        <ul class="pagination justify-content-center">
				            <li class="page-item disabled">
				                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Trước</a>
				            </li>
				            <li class="page-item"><a class="page-link" href="#">1</a></li>
				            <li class="page-item"><a class="page-link" href="#">2</a></li>
				            <li class="page-item"><a class="page-link" href="#">3</a></li>
				            <li class="page-item">
				                <a class="page-link" href="#">Tiếp</a>
				            </li>
				        </ul>
				    </nav>
				</div>
				
				<!-- Modal for Adding New Promotion -->
				<div class="modal fade" id="addPromotionModal" tabindex="-1" aria-labelledby="addPromotionModalLabel" aria-hidden="true">
				    <div class="modal-dialog">
				        <div class="modal-content">
				            <div class="modal-header">
				                <h5 class="modal-title" id="addPromotionModalLabel">Thêm Khuyến Mãi Mới</h5>
				                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				            </div>
				            <form action="#" method="POST">
				                <div class="modal-body">
				                    <div class="mb-3">
				                        <label for="promotionName" class="form-label">Tên Khuyến Mãi</label>
				                        <input type="text" class="form-control" id="promotionName" name="promotionName" required>
				                    </div>
				                    <div class="mb-3">
				                        <label for="discountPercent" class="form-label">Phần trăm giảm</label>
				                        <input type="number" class="form-control" id="discountPercent" name="discountPercent" required>
				                    </div>
				                    <div class="mb-3">
				                        <label for="startDate" class="form-label">Ngày bắt đầu</label>
				                        <input type="date" class="form-control" id="startDate" name="startDate" required>
				                    </div>
				                    <div class="mb-3">
				                        <label for="endDate" class="form-label">Ngày kết thúc</label>
				                        <input type="date" class="form-control" id="endDate" name="endDate" required>
				                    </div>
				                </div>
				                <div class="modal-footer">
				                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
				                    <button type="submit" class="btn btn-primary">Thêm</button>
				                </div>
				            </form>
				        </div>
				    </div>
				</div>


               
            </main>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="../../layout/Admin_Footer.jsp"></jsp:include>
</body>
</html>
