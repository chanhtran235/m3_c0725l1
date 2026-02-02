<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="../layout/library.jsp"/>
</head>
<body>
<c:import url="../layout/my-navbar.jsp"/>
<form action="/student?action=add" method="post">
    Tên
    <input name="name" placeholder="Nhập tên"><br>
    Giới tính
    <input type="radio" name="gender" value="true">Nam
    <input type="radio" name="gender" value="false"> Nữ <br>
    Đểm
    <input name="score"><br>
    <button>Thêm mới</button>
</form>
</body>
</html>
