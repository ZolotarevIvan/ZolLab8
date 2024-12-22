<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Album" %>
<%@ page import="CRUD.CRUDAlbum" %>
<%@ page import="Entity.Artist" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <title>Список Альбомов</title>
</head>
<body>
<h1>Список Альбомов</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Жанр</th>
        <th>Исполнитель</th>
        <th>Действия</th>
    </tr>
    <%
        CRUDAlbum crudAlbum = new CRUDAlbum();
        List<Album> albums = crudAlbum.getAllAlbums();
        for (Album album : albums) {
    %>
    <tr>
        <td><%= album.getId() %></td>
        <td><%= album.getTitle() %></td>
        <td><%= album.getGenre() %></td>
        <td><%= album.getArtist().getName() %></td>
        <td>
            <form action="AlbumServlet" method="post" style="display:inline;">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="id" value="<%= album.getId() %>">
                <input type="text" name="title" value="<%= album.getTitle() %>" placeholder="Новое название" required>
                <input type="text" name="genre" value="<%= album.getGenre() %>" placeholder="Новый жанр" required>
                <input type="text" name="artistId" value="<%= album.getArtist().getId() %>" placeholder="ID исполнителя" required>
                <input type="submit" value="Редактировать">
            </form>
            <form action="AlbumServlet" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%= album.getId() %>">
                <input type="submit" value="Удалить" onclick="return confirm('Вы уверены, что хотите удалить этот альбом?');">
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<h2>Добавить Альбом</h2>
<form action="AlbumServlet" method="post">
    <input type="hidden" name="action" value="add">
    <input type="text" name="title" placeholder="Название альбома" required>
    <input type="text" name="genre" placeholder="Жанр">
    <input type="text" name="artistId" placeholder="ID исполнителя" required>
    <input type="submit" value="Добавить">
</form>
<a href="index.jsp">Назад на главную</a>
</body>
</html>