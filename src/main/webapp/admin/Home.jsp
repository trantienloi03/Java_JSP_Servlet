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
	<jsp:include page="../layout/Admin_Header.jsp"></jsp:include>

    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <jsp:include page="../layout/Admin_Slidebar.jsp"></jsp:include>

            <!-- Content -->
            <main id="content" class="col-md-10 ms-sm-auto col-lg-10 content">
                <h1>Welcome to Admin Dashboard</h1>
                <p>This is the main content area. You can display charts, tables, and other management tools here.</p>
            </main>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="../layout/Admin_Footer.jsp"></jsp:include>
</body>
</html>
