<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Detail</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
    	.product-detail-img {
		    width: 500px;
		    height: 500px;
		    object-fit: cover; /* Đảm bảo giữ tỷ lệ ảnh */
}
    </style>
    
</head>
<body>
<jsp:include page="../layout/Header.jsp"></jsp:include>
    <div class="container my-5">
        <!-- Breadcrumb -->
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="Home">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Detail Product</li>
            </ol>
        </nav>

        <!-- Product Detail Section -->
        <div class="row">
            <div class="col-md-6">
                <img src="${product.getImage() }" class="img-fluid rounded product-detail-img" alt="Product Image">
            </div>
            <div class="col-md-6">
                <h1>${product.getProductName() }</h1>
                <p class="text-muted">Category: <span>${product.factory.getFactoryName() }</span></p>
                <p class="lead">${product.formatPrice(product.getPrice())} đ <span class="text-muted text-decoration-line-through">${product.formatPrice(product.getDiscountedPrice())} đ</span></p>
                <p>${product.getShortDesc()}. </p>
                <form action="AddToCart" method="get">
				    <input type="hidden" name="productID" value="${ product.getiD()}" />
				    <div class="d-flex align-items-center mb-3">
				        <label for="quantity" class="me-3">Số lượng:</label>
				        <input id="quantity" type="number" name="quantity" class="form-control text-center" min="1" value="1" style="width: 70px;">
				    </div>
				    <button type="submit" class="btn btn-primary btn-lg w-100">Thêm vào giỏ hàng</button>
				</form>
            </div>
        </div>

        <!-- Product Tabs Section -->
        <div class="mt-5">
            <ul class="nav nav-tabs" id="productTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="description-tab" data-bs-toggle="tab" data-bs-target="#description" type="button" role="tab" aria-controls="description" aria-selected="true">Description</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="specifications-tab" data-bs-toggle="tab" data-bs-target="#specifications" type="button" role="tab" aria-controls="specifications" aria-selected="false">Specifications</button>
                </li>
            </ul>
            <div class="tab-content" id="productTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel" aria-labelledby="description-tab">
                    <div class="p-3">
                        <h4>Product Description</h4>
                        <p>${product.getDetailDesc() }</p>
                    </div>
                </div>
                <div class="tab-pane fade" id="specifications" role="tabpanel" aria-labelledby="specifications-tab">
                    <div class="p-3">
                        <h4>Product Specifications</h4>
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <th>Processor</th>
                                    <td>Intel Core i7</td>
                                </tr>
                                <tr>
                                    <th>RAM</th>
                                    <td>16GB DDR4</td>
                                </tr>
                                <tr>
                                    <th>Storage</th>
                                    <td>512GB SSD</td>
                                </tr>
                                <tr>
                                    <th>Graphics</th>
                                    <td>NVIDIA GeForce RTX 3050</td>
                                </tr>
                                <tr>
                                    <th>Display</th>
                                    <td>15.6" Full HD (1920x1080)</td>
                                </tr>
                                <tr>
                                    <th>Operating System</th>
                                    <td>Windows 11</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
      <!-- Similar Products Section -->
      <div class="mt-5 tex-center">
    <h3 class="text-center">Similar Products</h3>
    <div id="similarProductsCarousel" class="carousel slide d-inline-block" data-bs-ride="carousel">
        <div class="carousel-inner">
            <c:if test="${empty lstSimilar}">
                <p>No similar products found.</p>
            </c:if>
            <c:forEach var="product" items="${lstSimilar}" varStatus="status">
                <!-- Mở một slide mới khi bắt đầu hoặc sau mỗi 5 sản phẩm -->
                 <button class="carousel-control-prev btn btn-dark" type="button" data-bs-target="#similarProductsCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
                <c:if test="${status.index % 5 == 0}">
                    <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                        <div class="row">
                </c:if>

                <!-- Sản phẩm -->
                <div class="col-md-2">
                    <div class="card">
                        <div class="position-relative">
                            <span class="purpose position-absolute top-0 start-0 bg-primary text-white px-2 py-1 small">${product.target.getTargetName()}</span>
                            <span class="promo position-absolute top-0 end-0 bg-danger text-white px-2 py-1 small">${product.promotion.getDiscountPercent() * 100}% Off</span>
                            <img src="${product.getImage()}" class="card-img-top" alt="Laptop">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <a href="Detail?ID=${product.iD}" class="text-decoration-none">${product.getProductName()}</a>
                                </h5>
                                <p class="card-text">${product.getShortDesc()}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <span class="old-price text-decoration-line-through text-muted">${product.formatPrice(product.getPrice())} đ</span>
                                    <span class="text-primary fw-bold">${product.formatPrice(product.getDiscountedPrice())} đ</span>
                                    <p class="text-center">
                                    <a href="AddToCart?productID=${product.getiD()}&quantity=1" class="btn btn-outline-primary">Add to Cart</a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Đóng slide sau mỗi 5 sản phẩm hoặc khi là sản phẩm cuối -->
                <c:if test="${(status.index + 1) % 5 == 0 || status.last}">
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            <!-- Carousel Controls -->
        <button class="carousel-control-next btn btn-dark" type="button" data-bs-target="#similarProductsCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
        </div>




	<jsp:include page="../layout/Footer.jsp"/>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
                                    
    