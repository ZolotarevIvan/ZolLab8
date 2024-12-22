<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Album" %>

<html>
<head>
    <title>Альбомы с Исполнителями</title>
</head>
<body>
<h1>Альбомы и Исполнители</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Жанр</th>
        <th>Исполнитель</th>
    </tr>
    <%
        List<Album> albums = (List<Album>) request.getAttribute("albums");
        for (Album album : albums) {
    %>
    <tr>
        <td><%= album.getId() %></td>
        <td><%= album.getTitle() %></td>
        <td><%= album.getGenre() %></td>
        <td><%= album.getArtist().getName() %></td>
    </tr>
    <%
        }
    %>
</table>
<a href="index.jsp">Назад на главную</a>
</body>
</html>