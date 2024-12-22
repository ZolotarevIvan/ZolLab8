package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_id_seq")
    @SequenceGenerator(name = "album_id_seq", sequenceName = "album_id_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false, unique = true)
    private String title;

    private  String genre;

    @ManyToOne // Связь с сущностью Artist
    @JoinColumn(name = "artist_id", nullable = false, foreignKey = @ForeignKey(name = "fk_artist")) // Связь с artist_id
    private Artist artist;

    public Album(){}

    public Album(String title, String genre, Artist artist) { // Измените параметр на Artist
        this.title = title;
        this.genre = genre;
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString(){
        return "\n" + "{" + "id=" + id + ", "+ "название: "+ title +", "+ "жанр: "+ genre + ", "+ "артист: "+ artist.getName() + "}";
    }
}