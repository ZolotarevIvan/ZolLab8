<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Исполнители и Количество Треков</title>
</head>
<body>
<h1>Список Исполнителей и Количество Треков</h1>
<table border="1">
  <tr>
    <th>Исполнитель</th>
    <th>Количество Треков</th>
  </tr>
  <%
    List<Object[]> artistsWithTrackCounts = (List<Object[]>) request.getAttribute("artistsWithTrackCounts");
    if (artistsWithTrackCounts != null) {
      for (Object[] artist : artistsWithTrackCounts) {
        String artistName = (String) artist[0];
        Long trackCount = (Long) artist[1]; // Учитываем, что COUNT возвращает Long
  %>
  <tr>
    <td><%= artistName %></td>
    <td><%= trackCount %></td>
  </tr>
  <%
    }
  } else {
  %>
  <tr>
    <td colspan="2">Нет доступных исполнителей.</td>
  </tr>
  <%
    }
  %>
</table>
</body>
</html>