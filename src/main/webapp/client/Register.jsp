<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-header bg-primary text-white text-center">
                        <h4>Đăng Ký</h4>
                    </div>
                    <div class="card-body">
                        <form method="POST" action="Register">
                        <span>${error }</span>
                            <div class="mb-3">
                                <label for="fullname" class="form-label">Họ và tên</label>
                                <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Nhập họ và tên" required>
                            </div>
                            <div class="mb-3">
                                <label for="username" class="form-label">Email</label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="Nhập tên đăng nhập" required>
                            </div>
                            <div class="mb-3">
                            
                                <label for="password" class="form-label">Địa chỉ</label>
                                <input type="text" class="form-control" id="password" name="address" placeholder="Nhập địa chỉ" required>
                            </div>
                            <div class="mb-3">
                            
                                <label for="password" class="form-label">Số điện thoại</label>
                                <input type="text" class="form-control" id="password" name="phone" placeholder="Nhập số điện thoại" required>
                            </div>
                            <div class="mb-3">
                            
                                <label for="password" class="form-label">Mật khẩu</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu" required>
                            </div>
                            <input type="hidden" name="roleID" value="2" />
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">Đăng Ký</button>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer text-center">
                        <p>Đã có tài khoản? <a href="Login" class="text-primary">Đăng nhập ngay</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
