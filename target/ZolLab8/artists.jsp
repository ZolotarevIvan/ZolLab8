<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Artist" %>
<%@ page import="CRUD.CRUDArtist" %>
<%@ page import="Servlets.ArtistServlet" %>

<html>
<head>
  <title>Список Исполнителей</title>
</head>
<body>
<h1>Список Исполнителей</h1>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Имя</th>
    <th>Действия</th>
  </tr>
  <%
    CRUDArtist crudArtist = new CRUDArtist();
    List<Artist> artists = crudArtist.getAllArtists();
    for (Artist artist : artists) {
  %>
  <tr>
    <td><%= artist.getId() %></td>
    <td><%= artist.getName() %></td>
    <td>
      <form action="ArtistServlet" method="post" style="display:inline;">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="id" value="<%= artist.getId() %>">
        <input type="text" name="name" value="<%= artist.getName() %>" placeholder="Новое имя" required>
        <input type="submit" value="Редактировать">
      </form>
      <form action="ArtistServlet" method="post" style="display:inline;">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="id" value="<%= artist.getId() %>">
        <input type="submit" value="Удалить" onclick="return confirm('Вы уверены, что хотите удалить этого артиста?');">
      </form>
    </td>
  </tr>
  <%
    }
  %>
</table>
<h2>Добавить Исполнителя</h2>
<form action="ArtistServlet" method="post">
  <input type="hidden" name="action" value="add">
  <input type="text" name="name" placeholder="Имя исполнителя" required>
  <input type="submit" value="Добавить">
</form>
<a href="index.jsp">Назад на главную</a>
</body>
</html>