<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detail</title>
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
				            <li class="breadcrumb-item" ><a href="Admin_FactoryController">Quản lý sản phẩm</a></li>
				             <li class="breadcrumb-item active" aria-current="page">Chi tiết</li>
				        </ol>
				    </nav>
				
				    <!-- Title and Add Button -->
				    <div class="d-flex justify-content-between align-items-center mb-3">
				        <h3>Chi tiết sản phẩm</h3>
				    </div>
				    <div class="row">
                                <div class="col-md-6 col-12 mx-auto">
                                        <div class="mb-3 col-12 col-md-6">
                                          <b  class="form-label">Tên sản phẩm:</b>
                                         ${product.getProductName() }
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                          <b  class="form-label">Gía bán:</b>
                                           ${product.getPrice() }
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                          <b for="role" class="form-label">Loại hàng:</b>
                                           ${product.getFactory().getFactoryName() }
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                          <b for="role" class="form-label">Mục đích:</b>
                                           ${product.getTarget().getTargetName() }
                                        </div>
                                        <div class="mb-3 col-12">
                                          <b class="form-label">Mô tả ngắn gọn:</b>
                                           ${product.getShortDesc() }
                                        </div>
                                        <div class="mb-3 col-12">
                                          <b class="form-label">Mô tả chi tiết:</b>
                                           ${product.getDetailDesc() }
                                        </div>
                                        <div class="mb-3 col-12">
                                           <img style="max-height: 250px;" src="${product.getImage() }" alt="avatar preview"
                                                id="avatarPreview">
                                        </div>
                       
                                        <div class="col-12 mb-5">
                                          <a href="Admin_ProductController" class="btn btn-secondary">
					                        <i class="fas fa-arrow-left"></i> Quay lại
					                     </a>
                                        </div>
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
