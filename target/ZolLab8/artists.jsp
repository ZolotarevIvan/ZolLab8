<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Artist" %>
<%@ page import="CRUD.CRUDArtist" %>
<%@ page import="Servlets.ArtistServlet" %>

<html>
<head>
  <title>Список Исполнителей</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- Подключаем jQuery -->
  <script>
    function addArtist() {
      const name = $('#newArtistName').val();
      $.post('ArtistServlet', { action: 'add', name: name }, function(data) {
        $('#artistsTable').append(
                `<tr>
                        <td>${data.id}</td>
                        <td>${data.name}</td>
                        <td>
                            <button onclick="editArtist(${data.id}, '${data.name}')">Редактировать</button>
                            <button onclick="deleteArtist(${data.id})">Удалить</button>
                        </td>
                    </tr>`
        );
        $('#newArtistName').val(''); // Очищаем поле ввода
      }, 'json');
    }

    function editArtist(id, currentName) {
      const newName = prompt("Введите новое имя исполнителя:", currentName);
      if (newName) {
        $.post('ArtistServlet', { action: 'edit', id: id, name: newName }, function(data) {
          // Обновляем имя исполнителя в таблице
          $(`#artistRow${id} td:nth-child(2)`).text(data.name);
        }, 'json');
      }
    }

    function deleteArtist(id) {
      if (confirm('Вы уверены, что хотите удалить этого артиста?')) {
        $.post('ArtistServlet', { action: 'delete', id: id }, function() {
          $(`#artistRow${id}`).remove(); // Удаляем строку из таблицы
        }, 'json');
      }
    }
  </script>
</head>
<body>
<h1>Список Исполнителей</h1>
<table border="1" id="artistsTable">
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
  <tr id="artistRow<%= artist.getId() %>">
    <td><%= artist.getId() %></td>
    <td><%= artist.getName() %></td>
    <td>
      <button onclick="editArtist(<%= artist.getId() %>, '<%= artist.getName() %>')">Редактировать</button>
      <button onclick="deleteArtist(<%= artist.getId() %>)">Удалить</button>
    </td>
  </tr>
  <%
    }
  %>
</table>
<h2>Добавить Исполнителя</h2>
<input type="text" id="newArtistName" placeholder="Имя исполнителя" required>
<button onclick="addArtist()">Добавить</button>
<a href="index.jsp">Назад на главную</a>
</body>
</html>