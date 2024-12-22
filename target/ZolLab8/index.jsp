<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ZolLab8 - Главная страница</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Подключите ваш CSS файл -->
</head>
<body>
<h1>Добро пожаловать в ZolLab8!</h1>
<nav>
    <h2>Выполнить CRUD операции</h2>
    <ul>
        <li><a href="albums.jsp">Альбомы</a></li>
        <li><a href="artists.jsp">Исполнители</a></li>
        <li><a href="tracks.jsp">Треки</a></li>
    </ul>
    <h2>Выполнить Запросы</h2>
    <ul>
        <li><a href="QueryServlet?action=allAlbumsWithArtists">Показать все альбомы с исполнителями</a></li>
        <li><a href="QueryServlet?action=artistsWithAlbumCount">Показать исполнителей и количество альбомов</a></li>
        <li><a href="QueryServlet?action=tracksWithAuthors">Показать исполнителей и названия песен</a></li>
        <li><a href="QueryServlet?action=artistsWithTrackCount">Показать исполнителей и количество песен</a></li>
    </ul>
</nav>
</body>
</html>