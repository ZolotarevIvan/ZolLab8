<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Track" %>
<%@ page import="CRUD.CRUDTrack" %>
<%@ page import="Entity.Album" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
  <title>Список Треков</title>
</head>
<body>
<h1>Список Треков</h1>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Название</th>
    <th>Альбом</th>
    <th>Действия</th>
  </tr>
  <%
    CRUDTrack crudTrack = new CRUDTrack();
    List<Track> tracks = crudTrack.getAllTracks();
    for (Track track : tracks) {
  %>
  <tr>
    <td><%= track.getId() %></td>
    <td><%= track.getTitle() %></td>
    <td><%= track.getAlbum().getTitle() %></td>
    <td>
      <form action="TrackServlet" method="post" style="display:inline;">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="id" value="<%= track.getId() %>">
        <input type="text" name="title" value="<%= track.getTitle() %>" placeholder="Новое название" required>
        <input type="text" name="duration" value="<%= track.getDuration() %>" placeholder="Длительность" required>
        <input type="text" name="albumId" value="<%= track.getAlbum().getId() %>" placeholder="ID альбома" required>
        <input type="submit" value="Редактировать">
      </form>
      <form action="TrackServlet" method="post" style="display:inline;">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="id" value="<%= track.getId() %>">
        <input type="submit" value="Удалить" onclick="return confirm('Вы уверены, что хотите удалить этот трек?');">
      </form>
    </td>
  </tr>
  <%
    }
  %>
</table>
<h2>Добавить Трек</h2>
<form action="TrackServlet" method="post">
  <input type="hidden" name="action" value="add">
  <input type="text" name="title" placeholder="Название трека" required>
  <input type="text" name="duration" placeholder="Длительность" required>
  <input type="text" name="albumId" placeholder="ID альбома" required>
  <input type="submit" value="Добавить">
</form>
<a href="index.jsp">Назад на главную</a>
</body>
</html>