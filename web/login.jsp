<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Trang Đăng Nhập Hệ Thống QLST</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="login-container">
        <h1>Đăng Nhập Hệ Thống QLST</h1>
        
        <p class="error-message">${requestScope.ERROR}</p>
        
        <form action="LoginServlet" method="POST">
            <label for="user">Tên đăng nhập:</label> 
            <input type="text" id="user" name="user" required/>
            
            <label for="password">Mật khẩu:</label> 
            <input type="password" id="password" name="password" required/>
            
            <input type="submit" value="Đăng Nhập"/>
        </form>
    </div>
</body>
</html>