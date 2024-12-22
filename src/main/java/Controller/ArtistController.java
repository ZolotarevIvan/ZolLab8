package Controller;

import Entity.Artist;
import CRUD.CRUDArtist;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final CRUDArtist crudArtist;

    public ArtistController(CRUDArtist crudArtist) {
        this.crudArtist = crudArtist;
    }

    @GetMapping("/autocomplete")
    public List<Artist> autocomplete(@RequestParam("query") String query) {
        return crudArtist.findArtistsByNameContaining(query);
    }
}