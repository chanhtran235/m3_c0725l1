<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="/home?action=add">trang chủ</a>
<form action="/home" method="post">
    <input name="action" value="add">
    <button>lên tranh home</button>
</form>
</body>
</html>