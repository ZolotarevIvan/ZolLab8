package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Импортируйте необходимые классы для работы с вашей моделью данных
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

        switch (action) {
            case "add":
                addArtist(request, response);
                break;
            case "edit":
                editArtist(request, response);
                break;
            case "delete":
                deleteArtist(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }

    private void addArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        System.out.println(name);
        Artist artist = new Artist(name);
        crudArtist.addArtist(artist); // Метод добавления артиста
        response.sendRedirect("artists.jsp"); // Перенаправление на страницу успеха
    }

    private void editArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        Artist artist = new Artist();
        artist.setId(id);
        artist.setName(name);

        crudArtist.updateArtist(artist); // Метод редактирования артиста
        response.sendRedirect("artists.jsp"); // Перенаправление на страницу успеха
    }

    private void deleteArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        crudArtist.deleteArtist(id); // Метод удаления артиста
        response.sendRedirect("artists.jsp"); // Перенаправление на страницу успеха
    }
}