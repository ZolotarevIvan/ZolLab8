<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Треки и Исполнители</title>
</head>
<body>
<h1>Список Треков и Исполнителей</h1>
<table border="1">
    <tr>
        <th>Название Трека</th>
        <th>Исполнитель</th>
    </tr>
    <%
        List<Object[]> tracksWithArtists = (List<Object[]>) request.getAttribute("tracksWithArtists");
        if (tracksWithArtists != null) {
            for (Object[] track : tracksWithArtists) {
                String trackTitle = (String) track[0];
                String artistName = (String) track[1];
    %>
    <tr>
        <td><%= trackTitle %></td>
        <td><%= artistName %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="2">Нет доступных треков.</td>
    </tr>
    <%
        }
    %>
</table>
<a href="index.jsp">Назад на главную</a>
</body>
</html>