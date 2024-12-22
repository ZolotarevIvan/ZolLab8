package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson; // Не забудьте добавить библиотеку Gson в зависимости вашего проекта

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import Entity.Artist;
import CRUD.CRUDArtist;

@WebServlet("/ArtistServlet")
public class ArtistServlet extends HttpServlet {

    private CRUDArtist crudArtist;

    @Override
    public void init() throws ServletException {
        crudArtist = new CRUDArtist(); // Инициализация вашего класса для работы с данными
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        switch (action) {
            case "add":
                addArtist(request, response, out, gson);
                break;
            case "edit":
                editArtist(request, response, out, gson);
                break;
            case "delete":
                deleteArtist(request, response, out, gson);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }

    private void addArtist(HttpServletRequest request, HttpServletResponse response, PrintWriter out, Gson gson) {
        String name = request.getParameter("name");
        Artist artist = new Artist(name);
        crudArtist.addArtist(artist);
        out.write(gson.toJson(artist)); // Возвращаем добавленного исполнителя в формате JSON
    }

    private void editArtist(HttpServletRequest request, HttpServletResponse response, PrintWriter out, Gson gson) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        Artist artist = new Artist();
        artist.setId(id);
        artist.setName(name);

        crudArtist.updateArtist(artist);
        out.write(gson.toJson(artist)); // Возвращаем обновленного исполнителя в формате JSON
    }

    private void deleteArtist(HttpServletRequest request, HttpServletResponse response, PrintWriter out, Gson gson) {
        int id = Integer.parseInt(request.getParameter("id"));
        crudArtist.deleteArtist(id);
        out.write(gson.toJson("Artist deleted successfully")); // Возвращаем сообщение об успешном удалении
    }
}