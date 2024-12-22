<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Artist" %>

<html>
<head>
    <title>Исполнители и Количество Альбомов</title>
</head>
<body>
<h1>Исполнители и Количество Альбомов</h1>
<table border="1">
    <tr>
        <th>Исполнитель</th>
        <th>Количество Альбомов</th>
    </tr>
    <%
        List<Object[]> artistsWithCounts = (List<Object[]>) request.getAttribute("artistsWithCounts");
        for (Object[] row : artistsWithCounts) {
            String artistName = ((Artist) row[0]).getName();
            Long albumCount = (Long) row[1];
    %>
    <tr>
        <td><%= artistName %></td>
        <td><%= albumCount %></td>
    </tr>
    <%
        }
    %>
</table>
<a href="index.jsp">Назад на главную</a>
</body>
</html>