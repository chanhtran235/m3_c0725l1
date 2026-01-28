<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="../layout/library.jsp"/>
</head>
<body>
<c:import url="../layout/my-navbar.jsp"/>
<h1>Danh sách sinh viên</h1>
    <span>${param.mess}</span>
<a href="/student?action=add">Thêm mới</a>
<table class="table table-dark table-striped">
    <tr>
        <th>STT</th>
        <th>Id</th>
        <th>Tên</th>
        <th>Giới tính</th>
        <th>Điểm</th>
        <th> Xếp loại </th>
        <th>Xoá</th>
    </tr>
    <c:forEach items="${studentList}" var="student" varStatus="status">
        <tr>
        <td>${status.count}</td>
        <td>${student.getId()}</td>
        <td>${student.getName()}</td>
        <td>
            <c:if test="${student.isGender()}">
                <span>Nam</span>
            </c:if>
            <c:if test="${!student.isGender()}">
                <span>Nữ</span>
            </c:if>
        </td>
        <td>${student.getScore()}</td>
        <td>
            <c:choose>
                <c:when test="${student.getScore()>=8}">
                    <span>Giỏi</span>
                </c:when>
                <c:when test="${student.getScore()>=7}">
                    <span>Khá</span>
                </c:when>
                <c:when test="${student.getScore()>=5}">
                    <span>Trung bình</span>
                </c:when>
                <c:otherwise>
                    <span>Yêu sắc yếu</span>
                </c:otherwise>
            </c:choose>
        </td>
        <td><button>Xoá</button></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
