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
<form action="/student" method="get">
    <input name="action" value="search" hidden="">
    <input name="searchName" placeholder="Nhập tên ........" value="${searchName}">
    <select name="classId">
        <option value="0">--Chọn lớp--</option>
        <c:forEach items="${classList}" var="cls">
            <option value="${cls.getId()}">${cls.getName()}</option>
        </c:forEach>
    </select>
    <button>Tìm kiếm</button>

</form>
<table class="table table-dark table-striped">
    <tr>
        <th>STT</th>
        <th>Id</th>
        <th>Tên</th>
        <th>Giới tính</th>
        <th>Điểm</th>
        <th> Xếp loại </th>
        <th> Lớp </th>
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
            <td>${student.getClassName()}</td>
        <td>
            <!-- Button trigger modal -->
            <button onclick="getInfoDelete('${student.getId()}','${student.getName()}')" type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">
                Xoá
            </button>
        </td>
        </tr>
    </c:forEach>
</table>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/student?action=delete" method="post">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <input hidden="hidden" name="deleteId" id="deleteId">
                <span>Bạn có muốn xoá sinh viên </span><span class="text-danger" id="deleteName"></span> không?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                <button type="submit" class="btn btn-primary">Chấp nhận xoá</button>
            </div>
        </div>
        </form>
    </div>
</div>
<script>
    function getInfoDelete(id, name){
        document.getElementById("deleteId").value = id;
        document.getElementById("deleteName").innerText = name;
    }
</script>
</body>
</html>
