<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Album" %>
<%@ page import="CRUD.CRUDAlbum" %>
<%@ page import="Entity.Artist" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <title>Список Альбомов</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
                $('#newAlbumTitle').val('');
                $('#newAlbumGenre').val('');
                $('#newAlbumArtistId').val('');
                $('#newAlbumArtistName').val('');
            }, 'json');
        }

        function autocompleteArtist(name) {
            if (name.length < 1) {
                $('#autocomplete-list').hide();
                return;
            }

            $.get('AlbumServlet', { action: 'autocompleteArtist', term: name }, function(data) {
                $('#autocomplete-list').empty();
                if (data.length > 0) {
                    data.forEach(function(artist) {
                        $('#autocomplete-list').append(`<div onclick="selectArtist(${artist.id}, '${artist.name}')">${artist.name}</div>`);
                    });
                    $('#autocomplete-list').show();
                } else {
                    $('#autocomplete-list').hide();
                }
            }, 'json');
        }

        function autocompleteGenre(name) {
            if (name.length < 1) {
                $('#autocomplete-genre-list').hide();
                return;
            }

            $.get('AlbumServlet', { action: 'autocompleteGenre', term: name }, function(data) {
                $('#autocomplete-genre-list').empty();
                if (data.length > 0) {
                    data.forEach(function(genre) {
                        $('#autocomplete-genre-list').append(`<div onclick="selectGenre('${genre}')">${genre}</div>`);
                    });
                    $('#autocomplete-genre-list').show();
                } else {
                    $('#autocomplete-genre-list').hide();
                }
            }, 'json');
        }

        function selectArtist(id, name) {
            $('#newAlbumArtistId').val(id);
            $('#newAlbumArtistName').val(name);
            $('#autocomplete-list').hide();
        }

        function selectGenre(genre) {
            $('#newAlbumGenre').val(genre);
            $('#autocomplete-genre-list').hide();
        }

        function editAlbum(id) {
            const title = $(`#albumRow${id} input[name='title']`).val();
            const genre = $(`#albumRow${id} input[name='genre']`).val();
            const artistId = $(`#albumRow${id} input[name='artistId']`).val();

            $.post('AlbumServlet', { action: 'edit', id: id, title: title, genre: genre, artistId: artistId }, function(data) {
                $(`#albumRow${id} td:nth-child(2)`).text(data.title);
                $(`#albumRow${id} td:nth-child(3)`).text(data.genre);
                $(`#albumRow${id} td:nth-child(4)`).text(data.artist.name);
            }, 'json');
        }

        function deleteAlbum(id) {
            if (confirm('Вы уверены, что хотите удалить этот альбом?')) {
                $.post('AlbumServlet', { action: 'delete', id: id }, function() {
                    $(`#albumRow${id}`).remove();
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
<input type="text" id="newAlbumGenre" placeholder="Жанр" onkeyup="autocompleteGenre(this.value)">
<input type="text" id="newAlbumArtistName" placeholder="Имя исполнителя" required onkeyup="autocompleteArtist(this.value)">
<input type="hidden" id="newAlbumArtistId" placeholder="ID исполнителя" required>
<div id="autocomplete-list" style="border: 1px solid #ccc; display: none;"></div>
<div id="autocomplete-genre-list" style="border: 1px solid #ccc; display: none;"></div>
<button onclick="addAlbum()">Добавить</button>
<a href="index.jsp">Назад на главную</a>
</body>
</html>
