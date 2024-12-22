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
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;

@WebServlet("/TrackServlet")
public class TrackServlet extends HttpServlet {
    private CRUDTrack crudTrack = new CRUDTrack();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        switch (action) {
            case "add":
                addTrack(request, response, out);
                break;
            case "edit":
                editTrack(request, response, out);
                break;
            case "delete":
                deleteTrack(request, response, out);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }

    private void addTrack(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        String title = request.getParameter("title");
        String durationStr = request.getParameter("duration");
        int albumId = Integer.parseInt(request.getParameter("albumId"));

        CRUDAlbum crudAlbum = new CRUDAlbum();
        Album album = crudAlbum.getAlbum(albumId);

        Track track = new Track(title, Time.valueOf(durationStr), album);
        crudTrack.addTrack(track);

        out.write(gson.toJson(track)); // Возвращаем добавленный трек в формате JSON
    }

    private void editTrack(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String durationStr = request.getParameter("duration");
        int albumId = Integer.parseInt(request.getParameter("albumId"));

        Track track = crudTrack.getTrack(id);
        if (track != null) {
            track.setTitle(title);
            track.setDuration(Time.valueOf(durationStr));
            CRUDAlbum crudAlbum = new CRUDAlbum();
            Album album = crudAlbum.getAlbum(albumId);
            track.setAlbum(album);
            crudTrack.updateTrack(track);
            out.write(gson.toJson(track)); // Возвращаем обновленный трек в формате JSON
        } else {
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Track not found");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void deleteTrack(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        int id = Integer.parseInt(request.getParameter("id"));
        crudTrack.deleteTrack(id);
        out.write(gson.toJson("Track deleted successfully")); // Возвращаем сообщение об успешном удалении
    }
}