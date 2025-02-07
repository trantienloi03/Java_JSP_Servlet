<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thay đổi mật khẩu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form-container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        span{
        color: red;
        }
    </style>
</head>
<body>
<jsp:include page="../layout/Header.jsp" />
    <div class="container mt-5">
        <div class="form-container">
            <h2 class="mb-4 text-center">Thay đổi mật khẩu</h2>
            <form action="ChangePassController" method="POST">
            <input type="hidden" class="form-control" name="userID" value="${sessionScope.user.userID}">
                <div class="mb-3">
                    <label for="currentPassword" class="form-label">Mật khẩu hiện tại</label>
                    <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                    <span>${errorOldPass}</span>
                </div>
                <div class="mb-3">
                    <label for="newPassword" class="form-label">Mật khẩu mới</label>
                    <input type="text" class="form-control" id="newPassword" name="newPassword" required>
                </div>
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label">Xác nhận mật khẩu mới</label>
                    <input type="text" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    <span>${error}</span>
                </div>
                <div class="d-flex justify-content-between">
                    <button type="reset" class="btn btn-secondary">Làm lại</button>
                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                </div>
            </form>
        </div>
    </div>
	<jsp:include page="../layout/Footer.jsp" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

