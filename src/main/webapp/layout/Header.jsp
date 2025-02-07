<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container d-flex align-items-center">
        <!-- Tiêu đề -->
        <a class="navbar-brand" href="#">Laptop Store</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <!-- Nội dung thanh điều hướng -->
        <div class="collapse navbar-collapse" id="navbarNav">
            <!-- Nhóm bên trái -->
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link" href="Home">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#">About us</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Service</a></li>
            </ul>
            <!-- Nhóm bên phải -->
            <ul class="navbar-nav ms-auto">
                <c:if test="${ empty sessionScope.user}">
                    <li class="nav-item"><a class="nav-link" href="Login">Login/Signin</a></li>
                </c:if>
                <c:if test="${ not empty sessionScope.user}">
                    <li class="nav-item">
					    <a class="nav-link" href="CartController">
					        Cart <span id="cart-count" class="badge badge-pill badge-primary">${sessionScope.sum }</span>
					    </a>
					</li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Xin chào, ${sessionScope.user.getFullName() }
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                            <li><a class="dropdown-item" href="ChangePassController" >Thay đổi mật khẩu</a></li>
                            <li><a class="dropdown-item" href="EditProfileController" >Thay đổi thông tin</a></li>
                            <li><a class="dropdown-item" href="OrderController">Lịch sử đơn hàng</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="Logout">Đăng xuất</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>



<!-- Modal for Change Password -->


<!-- Modal for Edit Profile -->



