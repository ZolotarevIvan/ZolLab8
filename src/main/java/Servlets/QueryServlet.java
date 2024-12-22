package Servlets;

import CRUD.CRUDAlbum;
import CRUD.CRUDArtist;
import CRUD.CRUDTrack;
import Entity.Album;
import Entity.Artist;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
    private CRUDAlbum crudAlbum = new CRUDAlbum();
    private CRUDArtist crudArtist = new CRUDArtist();
    private CRUDTrack crudTrack = new CRUDTrack();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "allAlbumsWithArtists":
                getAllAlbumsWithArtists(request, response);
                break;
            case "artistsWithAlbumCount":
                getArtistsWithAlbumCount(request, response);
                break;
            case "tracksWithAuthors":
                getTracksWithAuthors(request, response);
                break;
            case "artistsWithTrackCount": // Новый кейс для получения исполнителей с количеством треков
                getArtistsWithTrackCount(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }

    private void getAllAlbumsWithArtists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Album> albums = crudAlbum.getAllAlbums();
        request.setAttribute("albums", albums);
        request.getRequestDispatcher("viewAlbumsWithArtists.jsp").forward(request, response);
    }

    private void getArtistsWithAlbumCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Object[]> artistsWithCounts = crudArtist.getArtistsWithAlbumCount();
        request.setAttribute("artistsWithCounts", artistsWithCounts);
        request.getRequestDispatcher("viewArtistsWithCounts.jsp").forward(request, response);
    }

    private void getTracksWithAuthors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Object[]> tracksWithArtists = crudTrack.getTracksWithArtists();
        request.setAttribute("tracksWithArtists", tracksWithArtists);
        request.getRequestDispatcher("viewTracksWithArtists.jsp").forward(request, response);
    }

    private void getArtistsWithTrackCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Object[]> artistsWithTrackCounts = crudArtist.getArtistsWithTrackCount();
        request.setAttribute("artistsWithTrackCounts", artistsWithTrackCounts);
        request.getRequestDispatcher("viewArtistsWithTrackCounts.jsp").forward(request, response); // Укажите правильное имя JSP-файла
    }
}
