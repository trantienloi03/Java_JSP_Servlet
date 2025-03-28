<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add product</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="layout/admin.css" rel="stylesheet">
     <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURL);
                            $("#avatarPreview").css({ "display": "block" });
                        });
                    });
                </script>
     <style type="text/css">
        	span{
        		color: red;
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
				            <li class="breadcrumb-item"><a href="Admin_HomeController">Trang chủ</a></li>
				            <li class="breadcrumb-item" ><a href="Admin_ProductController">Quản lý sản phẩm</a></li>
				             <li class="breadcrumb-item active" aria-current="page">Thêm mới</li>
				        </ol>
				    </nav>
				
				    <!-- Title and Add Button -->
				                <div class="mt-5">
                            <div class="row">
                                <div class="col-md-6 col-12 mx-auto">
                                    <h2>Thêm mới</h2> <hr>
                                    <form class="row" action="SaveProductController" method="Post" enctype="multipart/form-data">
                                        <div class="mb-3 col-12 col-md-6">
                                          <label  class="form-label">Tên sản phẩm</label>
                                          <input type="text" name="productName" value="${product.getProductName() }" class="form-control""/>
                                          <span>${errors.productName }</span>
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                          <label  class="form-label">Gía bán</label>
                                          <input type="text" name="price" value="${product.getPrice() }" class="form-control""/>
                                          <span>${errors.price }</span>
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                          <label for="role" class="form-label">Loại hàng</label>
                                          <select id="role" class="form-select" name="factory">
	                                          	<option value="0">-- chọn loại hàng --</option>                                       
	                                          <c:forEach var="factory" items="${lstFactory }">	         
	                                          	<option value="${factory.getFactoryID()}">${factory.getFactoryName() }</option>	                                      	
											</c:forEach>
                                          </select>
                                          <span>${errors.factory }</span>
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                          <label for="role" class="form-label">Mục đích</label>
                                          <select id="role" class="form-select" name="target">
	                                          	<option value="0">-- chọn mục tiêu --</option>                                       
	                                          <c:forEach var="target" items="${lstTarget }">
	                                          	<option selected="selected" value="${target.getTargetID() }">${target.getTargetName()}</option>                                       
	                                           </c:forEach>
                                          </select>
                                          <span>${errors.target }</span>
                                        </div>
                                        <div class="mb-3 col-12">
                                          <label class="form-label">Mô tả ngắn gọn</label>
                                          <textarea type="text" name="shortDesc" value="" class="form-control"></textarea>
                                          <span style="color: red;"></span>
                                        </div>
                                        <div class="mb-3 col-12">
                                          <label class="form-label">Mô tả chi tiết</label>
                                          <textarea type="text" name="detailDesc" value="" class="form-control"></textarea>
                                          <span style="color: red;"></span>
                                        </div>
                                        
                                        <div class="mb-3 col-12 col-md-6">
                                            <label for="avatarFile" class="form-label">Ảnh</label>
                                            <input type="file" id="avatarFile" name="txtfile"/>
                                            <span>${errors.uploadfile}</span>
                                        </div>
                                        <div class="mb-3 col-12">
                                           <img style="max-height: 250px; display: none;" alt="avatar preview"
                                                id="avatarPreview">
                                        </div>
                       
                                        <div class="col-12 mb-5">
                                          <button type="submit" class="btn btn-primary">Create</button>
                                          <a href="Admin_ProductController" class="btn btn-secondary">
					                        <i class="fas fa-arrow-left"></i> Quay lại
					                     </a>
                                        </div>
                                      </form>
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
