<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Laptop Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card:hover {
            transform: scale(1.05);
            transition: 0.3s;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .card .purpose {
            position: absolute;
            top: 10px;
            left: 10px;
            background-color: rgba(0, 0, 0, 0.7);
            color: white;
            padding: 5px;
            border-radius: 5px;
            font-size: 0.9rem;
        }
        .card .promo {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: #ffc107;
            color: black;
            padding: 5px;
            border-radius: 5px;
            font-size: 0.9rem;
        }
        .old-price {
            text-decoration: line-through;
            color: grey;
            margin-right: 10px;
        }
        .alert {
    position: relative; 
    background-color: white;
    color: red;
    padding: 5px 0;
    text-align: center;
    width: 100%;
    overflow: hidden; 
}

.scrolling-message {
    white-space: nowrap; /* Đảm bảo văn bản không xuống dòng */
    animation: scrolling 20s linear infinite; /* Áp dụng hiệu ứng chạy */
    font-size: 16px;
    display: inline-block; /* Đảm bảo văn bản chạy ngang */
    padding-left: 100%; /* Bắt đầu từ bên phải */
    animation-delay: -15s; /* Thời gian chờ trước khi bắt đầu chạy */
}

/* Keyframes cho hiệu ứng chạy */
@keyframes scrolling {
    0% {
        transform: translateX(100%); /* Bắt đầu từ bên phải */
    }

    50% {
        transform: translateX(0); /* Dừng lại ở giữa để tạo hiệu ứng chạy chậm */
    }

    100% {
        transform: translateX(-100%); /* Chạy hết sang bên trái */
    }
}




#bannerCarousel .carousel-inner img {
    object-fit: cover;
    width: 100%; 
    height: 400px;
}

.carousel-control-prev, .carousel-control-next {
    background-color: rgba(0, 0, 0, 0);
    color: white; 
    font-size: 24px; 
    padding: 10px; 
}

.carousel-control-prev-icon, .carousel-control-next-icon {
    display: block; 
}

.carousel-control-prev, .carousel-control-next {
    font-weight: bold; 
    font-size: 30px; 
}
    </style>
</head>
<body>
    <!-- Navbar -->
    <jsp:include page="../layout/Header.jsp" />

    
 <!-- Slider Section -->
    <div id="productCarousel" class="carousel slide mt-2" data-bs-ride="carousel">
    <div class="alert alert-default mt-0">
    
</div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="banner/1.png" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Latest Gaming Laptops</h5>
                    <p>Experience the ultimate gaming performance.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="banner/3.png" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Work with Efficiency</h5>
                    <p>Discover laptops designed for productivity.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="banner/2.png" class="d-block w-100" alt="...">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Portable and Powerful</h5>
                    <p>Lightweight laptops for people on the go.</p>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#productCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#productCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
    <!-- Why Choose Us -->
    <div class="container mt-5">
        <h2 class="text-center mb-4">Why Choose Us?</h2>
        <div class="row text-center">
            <div class="col-md-4">
                <img src="whychooseus/2.png" class="mb-3" alt="Quality">
                <h5>Fast delivery</h5>
                <p>We provide the best laptops with high durability and performance.</p>
            </div>
            <div class="col-md-4">
                <img src="whychooseus/1.png" class="mb-3" alt="Support">
                <h5>24/7 Support</h5>
                <p>Our customer service is available round the clock to assist you.</p>
            </div>
            <div class="col-md-4">
                <img src="whychooseus/3.png" class="mb-3" alt="Warranty">
                <h5>Warranty</h5>
                <p>All products come with a comprehensive warranty for peace of mind.</p>
            </div>
            <hr>
        </div>
    </div>

 <!-- Search and Filter Section -->
    <div class="container mt-4">
        <div class="row mt-3">
        <form action="Home" method="get">
		    <div class="row g-3 align-items-end">
		        <div class="col-md-3">
		            <label class="form-label" for="factory">Factory</label>
		            <select class="form-select" id="factory" name="factory">
		                <option value="0">-- select factory --</option>
		                <c:forEach var="factory" items="${lstFactory}">
		                    <option value="${factory.getFactoryID()}">${factory.getFactoryName()}</option>
		                </c:forEach>
		            </select>
		        </div>
		        <div class="col-md-3">
		            <label class="form-label" for="price">Search</label>
		            <input type="text" name="ProductName" class="form-control" placeholder="Search for laptops...">
		        </div>
		        <div class="col-md-3">
		            <label class="form-label" for="target">Target</label>
		            <select class="form-select" id="target" name="target">
		                <option value="0">-- select target --</option>
		                <c:forEach var="target" items="${lstTarget}">
		                    <option value="${target.getTargetID()}">${target.getTargetName()}</option>
		                </c:forEach>
		            </select>
		        </div>
		        <div class="col-md-3">
		            <button class="btn btn-secondary w-100">Apply Filters</button>
		        </div>
		    </div>
		</form>

        </div>
        
    </div>
    <!-- Featured Products -->
    <div class="container mt-5">
        <h2 class="text-center mb-4">Featured Laptops</h2>
        <div class="row">
        <c:if test="${ empty lstProduct}">
        	<p class="text-center">Không có sản phẩm</p>
        </c:if>
        <c:forEach var="product" items="${lstProduct}">
	        <div class="col-md-4 mb-3">
	                <div class="card position-relative">
	                    <span class="purpose">${product.target.getTargetName() }</span>
	                    <span class="promo">${product.promotion.getDiscountPercent()*100 }% Off</span>
	                    <img src="${product.getImage() }" class="card-img-top" alt="Laptop 1">
	                    <div class="card-body">
	                        <h5 class="card-title">
	                        	<a href="Detail?ID=${product.iD }"> ${product.getProductName() }</a>
	                        </h5>
	                        <p class="card-text">${product.getShortDesc() }</p>
	                        <div class="d-flex justify-content-between align-items-center">
	                            <span class="old-price">${product.formatPrice(product.getPrice())} đ</span>
	                            <span class="text-primary fw-bold">${product.formatPrice(product.getDiscountedPrice())} đ</span>

	                            <a href="AddToCart?productID=${product.getiD()}&quantity=1" class="btn btn-outline-primary">Add to Cart</a>
	                        </div>
	                    </div>
	                </div>
	            </div>
        </c:forEach>
           
        <!-- Pagination -->
        <nav aria-label="Page navigation" class="mt-4">
            <ul class="pagination justify-content-center">
                <c:forEach var="i" begin="1" end="${Page}">
		               <li class="page-item ${index == i ? 'active' : ''}">
		                  <a class="page-link" href="Home?index=${i}&factory=${factory}&target=${target}">${i}</a>
		               </li>
		            </c:forEach>
            </ul>
        </nav>
    </div>
    </div>
    <!-- Footer -->
    <jsp:include page="../layout/Footer.jsp"></jsp:include>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
