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
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/AlbumServlet")
public class AlbumServlet extends HttpServlet {
    private CRUDAlbum crudAlbum = new CRUDAlbum();
    private CRUDArtist crudArtist = new CRUDArtist();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        switch (action) {
            case "add":
                addAlbum(request, response, out);
                break;
            case "edit":
                editAlbum(request, response, out);
                break;
            case "delete":
                deleteAlbum(request, response, out);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if ("autocompleteArtist".equals(action)) {
            autocompleteArtist(request, response, out);
        } else if ("autocompleteGenre".equals(action)) {
            autocompleteGenre(request, response, out);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }

    private void addAlbum(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        int artistId = Integer.parseInt(request.getParameter("artistId"));

        Artist artist = crudArtist.getArtist(artistId);
        Album album = new Album(title, genre, artist);
        crudAlbum.addAlbum(album);

        out.write(gson.toJson(album)); // Возвращаем добавленный альбом в формате JSON
    }

    private void editAlbum(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        int artistId = Integer.parseInt(request.getParameter("artistId"));

        Album album = crudAlbum.getAlbum(id);
        if (album != null) {
            album.setTitle(title);
            album.setGenre(genre);
            Artist artist = crudArtist.getArtist(artistId);
            album.setArtist(artist);
            crudAlbum.updateAlbum(album);
            out.write(gson.toJson(album)); // Возвращаем обновленный альбом в формате JSON
        } else {
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Album not found");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void deleteAlbum(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        int id = Integer.parseInt(request.getParameter("id"));
        crudAlbum.deleteAlbum(id);
        out.write(gson.toJson("Album deleted successfully")); // Возвращаем сообщение об успешном удалении
    }

    private void autocompleteArtist(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        String searchTerm = request.getParameter("term");
        List<Artist> artists = crudArtist.searchArtistsByName(searchTerm); // Метод для поиска исполнителей по имени
        out.write(gson.toJson(artists)); // Возвращаем список исполнителей в формате JSON
    }

    private void autocompleteGenre(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        String searchTerm = request.getParameter("term");
        List<String> genres = crudAlbum.searchGenresByTerm(searchTerm); // Метод для поиска жанров по терму
        out.write(gson.toJson(genres)); // Возвращаем список жанров в формате JSON
    }
}

