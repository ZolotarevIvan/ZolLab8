package Servlets;

import CRUD.CRUDAlbum;
import CRUD.CRUDArtist;
import Entity.Album;
import Entity.Artist;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AlbumServlet")
public class AlbumServlet extends HttpServlet {
    private CRUDAlbum crudAlbum = new CRUDAlbum();
    private CRUDArtist crudArtist = new CRUDArtist(); // Добавляем CRUD для Artist

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addAlbum(request, response);
                break;
            case "edit":
                editAlbum(request, response);
                break;
            case "delete":
                deleteAlbum(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }

    private void addAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        int artistId = Integer.parseInt(request.getParameter("artistId"));

        Artist artist = crudArtist.getArtist(artistId); // Получаем объект Artist
        Album album = new Album(title, genre, artist);
        crudAlbum.addAlbum(album);

        response.sendRedirect("albums.jsp"); // Замените на имя вашей JSP-страницы
    }

    private void editAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        int artistId = Integer.parseInt(request.getParameter("artistId"));

        Album album = crudAlbum.getAlbum(id);
        if (album != null) {
            album.setTitle(title);
            album.setGenre(genre);
            Artist artist = crudArtist.getArtist(artistId); // Получаем объект Artist
            album.setArtist(artist);
            crudAlbum.updateAlbum(album);
        }

        response.sendRedirect("albums.jsp"); // Замените на имя вашей JSP-страницы
    }

    private void deleteAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        crudAlbum.deleteAlbum(id);

        response.sendRedirect("albums.jsp"); // Замените на имя вашей JSP-страницы
    }
}