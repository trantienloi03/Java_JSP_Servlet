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
				            <li class="breadcrumb-item"><a href="Admin_HomeController">Trang chủ</a></li>
				            <li class="breadcrumb-item" ><a href="Admin_FactoryController">Quản lý loại hàng</a></li>
				             <li class="breadcrumb-item active" aria-current="page">Thêm mới</li>
				        </ol>
				    </nav>
				
				    <!-- Title and Add Button -->
				    <div class="d-flex justify-content-between align-items-center mb-3">
				        <h3>Thêm mới</h3>
				    </div>
				    <div class="mx-auto">
				    <form action="SaveFactoryController" method="Get">
				    <span style="color: red;">${error}</span>
				             <div class="modal-body">
				                    <div class="mb-3">
				                        <label for="factoryName" class="form-label">Tên loại hàng</label>
				                        <input type="text" class="form-control" id="factoryName" name="factoryName" required>
				                    </div>
				                </div>
				                <div class="modal-footer">
				                    <a type="button" class="btn btn-secondary" href="Admin_FactoryController">Hủy</a>
				                    <button type="submit" class="btn btn-primary">Thêm</button>
				                </div>
				            </form>   
				    </div>

				</div>
				
            </main>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="../../layout/Admin_Footer.jsp"></jsp:include>
</body>
</html>
