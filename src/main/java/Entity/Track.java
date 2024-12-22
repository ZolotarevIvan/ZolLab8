package Entity;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "track_id_seq")
    @SequenceGenerator(name = "track_id_seq", sequenceName = "track_id_seq", allocationSize = 1)
    private int id;
    @Column(nullable = false, unique = true) // Добавьте уникальность и обязательность
    private String title;

    private Time duration;

    @ManyToOne // Связь с сущностью Album
    @JoinColumn(name = "album_id", nullable = false, foreignKey = @ForeignKey(name = "fk_album")) // Связь с album_id
    private Album album;

    public Track() {}

    public Track(String title, Time duration, Album album) { // Измените параметр на Album
        this.title = title;
        this.duration = duration;
        this.album = album;
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

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString(){
        return "\n" + "{" + "id=" + id + ", "+ "название: "+title+", "+"длительность: "+duration+" сек, "+"альбом: "+ album.getTitle()+"}";
    }
}
