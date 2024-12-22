<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Track" %>
<%@ page import="CRUD.CRUDTrack" %>
<%@ page import="Entity.Album" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
  <title>Список Треков</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- Подключаем jQuery -->
  <script>
    function addTrack() {
      const title = $('#newTrackTitle').val();
      const duration = $('#newTrackDuration').val();
      const albumId = $('#newTrackAlbumId').val();
      $.post('TrackServlet', { action: 'add', title: title, duration: duration, albumId: albumId }, function(data) {
        $('#tracksTable').append(
                `<tr id="trackRow${data.id}">
                        <td>${data.id}</td>
                        <td>${data.title}</td>
                        <td>${data.album.title}</td>
                        <td>
                            <input type="text" name="title" value="${data.title}" placeholder="Новое название" required>
                            <input type="text" name="duration" value="${data.duration}" placeholder="Длительность" required>
                            <input type="text" name="albumId" value="${data.album.id}" placeholder="ID альбома" required>
                            <button onclick="editTrack(${data.id})">Редактировать</button>
                            <button onclick="deleteTrack(${data.id})">Удалить</button>
                        </td>
                    </tr>`
        );
        $('#newTrackTitle').val(''); // Очищаем поля ввода
        $('#newTrackDuration').val('');
        $('#newTrackAlbumId').val('');
      }, 'json');
    }

    function editTrack(id) {
      const title = $(`#trackRow${id} input[name='title']`).val();
      const duration = $(`#trackRow${id} input[name='duration']`).val();
      const albumId = $(`#trackRow${id} input[name='albumId']`).val();

      $.post('TrackServlet', { action: 'edit', id: id, title: title, duration: duration, albumId: albumId }, function(data) {
        // Обновляем строку в таблице с новым значением
        $(`#trackRow${id} td:nth-child(2)`).text(data.title);
        $(`#trackRow${id} td:nth-child(3)`).text(data.album.title);
      }, 'json');
    }

    function deleteTrack(id) {
      if (confirm('Вы уверены, что хотите удалить этот трек?')) {
        $.post('TrackServlet', { action: 'delete', id: id }, function() {
          $(`#trackRow${id}`).remove(); // Удаляем строку из таблицы
        }, 'json');
      }
    }
  </script>
</head>
<body>
<h1>Список Треков</h1>
<table border="1" id="tracksTable">
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
  <tr id="trackRow<%= track.getId() %>">
    <td><%= track.getId() %></td>
    <td><%= track.getTitle() %></td>
    <td><%= track.getAlbum().getTitle() %></td>
    <td>
      <input type="text" name="title" value="<%= track.getTitle() %>" placeholder="Новое название" required>
      <input type="text" name="duration" value="<%= track.getDuration() %>" placeholder="Длительность" required>
      <input type="text" name="albumId" value="<%= track.getAlbum().getId() %>" placeholder="ID альбома" required>
      <button onclick="editTrack(<%= track.getId() %>)">Редактировать</button>
      <button onclick="deleteTrack(<%= track.getId() %>)">Удалить</button>
    </td>
  </tr>
  <%
    }
  %>
</table>
<h2>Добавить Трек</h2>
<input type="text" id="newTrackTitle" placeholder="Название трека" required>
<input type="text" id="newTrackDuration" placeholder="Длительность" required>
<input type="text" id="newTrackAlbumId" placeholder="ID альбома" required>
<button onclick="addTrack()">Добавить</button>
<a href="index.jsp">Назад на главную</a>
</body>
</html>
