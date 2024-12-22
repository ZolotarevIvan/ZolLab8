<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Album" %>
<%@ page import="CRUD.CRUDAlbum" %>
<%@ page import="Entity.Artist" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <title>Список Альбомов</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- Подключаем jQuery -->
    <script>
        function addAlbum() {
            const title = $('#newAlbumTitle').val();
            const genre = $('#newAlbumGenre').val();
            const artistId = $('#newAlbumArtistId').val();

            $.post('AlbumServlet', { action: 'add', title: title, genre: genre, artistId: artistId }, function(data) {
                $('#albumsTable').append(
                    `<tr id="albumRow${data.id}">
                        <td>${data.id}</td>
                        <td>${data.title}</td>
                        <td>${data.genre}</td>
                        <td>${data.artist.name}</td>
                        <td>
                            <input type="text" name="title" value="${data.title}" placeholder="Новое название" required>
                            <input type="text" name="genre" value="${data.genre}" placeholder="Новый жанр" required>
                            <input type="text" name="artistId" value="${data.artist.id}" placeholder="ID исполнителя" required>
                            <button onclick="editAlbum(${data.id})">Редактировать</button>
                            <button onclick="deleteAlbum(${data.id})">Удалить</button>
                        </td>
                    </tr>`
                );
                $('#newAlbumTitle').val(''); // Очищаем поле ввода
                $('#newAlbumGenre').val('');
                $('#newAlbumArtistId').val('');
            }, 'json');
        }

        function editAlbum(id) {
            const title = $(`#albumRow${id} input[name='title']`).val();
            const genre = $(`#albumRow${id} input[name='genre']`).val();
            const artistId = $(`#albumRow${id} input[name='artistId']`).val();

            $.post('AlbumServlet', { action: 'edit', id: id, title: title, genre: genre, artistId: artistId }, function(data) {
                // Обновляем строку в таблице с новым значением
                $(`#albumRow${id} td:nth-child(2)`).text(data.title);
                $(`#albumRow${id} td:nth-child(3)`).text(data.genre);
                $(`#albumRow${id} td:nth-child(4)`).text(data.artist.name);
            }, 'json');
        }

        function deleteAlbum(id) {
            if (confirm('Вы уверены, что хотите удалить этот альбом?')) {
                $.post('AlbumServlet', { action: 'delete', id: id }, function() {
                    $(`#albumRow${id}`).remove(); // Удаляем строку из таблицы
                }, 'json');
            }
        }
    </script>
</head>
<body>
<h1>Список Альбомов</h1>
<table border="1" id="albumsTable">
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
    <tr id="albumRow<%= album.getId() %>">
        <td><%= album.getId() %></td>
        <td><%= album.getTitle() %></td>
        <td><%= album.getGenre() %></td>
        <td><%= album.getArtist().getName() %></td>
        <td>
            <input type="text" name="title" value="<%= album.getTitle() %>" placeholder="Новое название" required>
            <input type="text" name="genre" value="<%= album.getGenre() %>" placeholder="Новый жанр" required>
            <input type="text" name="artistId" value="<%= album.getArtist().getId() %>" placeholder="ID исполнителя" required>
            <button onclick="editAlbum(<%= album.getId() %>)">Редактировать</button>
            <button onclick="deleteAlbum(<%= album.getId() %>)">Удалить</button>
        </td>
    </tr>
    <%
        }
    %>
</table>
<h2>Добавить Альбом</h2>
<input type="text" id="newAlbumTitle" placeholder="Название альбома" required>
<input type="text" id="newAlbumGenre" placeholder="Жанр">
<input type="text" id="newAlbumArtistId" placeholder="ID исполнителя" required>
<button onclick="addAlbum()">Добавить</button>
<a href="index.jsp">Назад на главную</a>
</body>
</html>
