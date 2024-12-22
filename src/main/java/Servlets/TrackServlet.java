package Servlets;

import CRUD.CRUDAlbum;
import CRUD.CRUDTrack;
import Entity.Track;
import Entity.Album;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Time;

@WebServlet("/TrackServlet")
public class TrackServlet extends HttpServlet {
    private CRUDTrack crudTrack = new CRUDTrack();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addTrack(request, response);
                break;
            case "edit":
                editTrack(request, response);
                break;
            case "delete":
                deleteTrack(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }

    private void addTrack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String durationStr = request.getParameter("duration");
        int albumId = Integer.parseInt(request.getParameter("albumId"));

        // Здесь вы должны получить объект Album по albumId
        CRUDAlbum crudAlbum = new CRUDAlbum();
        Album album = crudAlbum.getAlbum(albumId);

        Track track = new Track(title, Time.valueOf(durationStr), album);
        crudTrack.addTrack(track);

        response.sendRedirect("tracks.jsp"); // Замените на имя вашей JSP-страницы
    }

    private void editTrack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String durationStr = request.getParameter("duration");
        int albumId = Integer.parseInt(request.getParameter("albumId"));

        Track track = crudTrack.getTrack(id);
        if (track != null) {
            track.setTitle(title);
            track.setDuration(Time.valueOf(durationStr));
            // Здесь вы должны получить объект Album по albumId
            CRUDAlbum crudAlbum = new CRUDAlbum();
            Album album = crudAlbum.getAlbum(albumId);
            track.setAlbum(album);
            crudTrack.updateTrack(track);
        }

        response.sendRedirect("tracks.jsp"); // Замените на имя вашей JSP-страницы
    }

    private void deleteTrack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        crudTrack.deleteTrack(id);

        response.sendRedirect("tracks.jsp"); // Замените на имя вашей JSP-страницы
    }
}