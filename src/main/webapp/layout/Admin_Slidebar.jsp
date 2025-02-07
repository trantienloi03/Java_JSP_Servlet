<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <!-- Sidebar -->
            <nav id="sidebar" class="col-md-2 d-md-block sidebar">
                <div class="position-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="Admin_HomeController">Dashboard</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="collapse" data-bs-target="#dataManagement" aria-expanded="false">
                                Quản lý dữ liệu
                            </a>
                            <ul id="dataManagement" class="collapse list-unstyled">
                                <li><a class="nav-link ps-4" href="Admin_ProductController">Quản lý sản phẩm</a></li>
                                <li><a class="nav-link ps-4" href="Admin_PromotionController">Quản lý khuyến mãi</a></li>
                                <li><a class="nav-link ps-4" href="Admin_FactoryController">Quản lý loại hàng</a></li>
                                <li><a class="nav-link ps-4" href="Admin_TargetController">Quản lý mục tiêu</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Admin_UserController">Quản lý khách hàng</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Admin_OrderController">Quản lý đơn hàng</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Admin_OrderController">Quản lý nhân viên</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Reports</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Settings</a>
                        </li>
                    </ul>
                </div>
            </nav>
            
            <script>
        const toggleSidebar = document.getElementById('toggleSidebar');
        const sidebar = document.getElementById('sidebar');
        const content = document.getElementById('content');

        toggleSidebar.addEventListener('click', () => {
            sidebar.classList.toggle('collapsed');
            content.classList.toggle('expanded');
        });
    </script>