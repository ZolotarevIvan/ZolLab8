package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_id_seq")
    @SequenceGenerator(name = "artist_id_seq", sequenceName = "artist_id_seq", allocationSize = 1)
    private int id;
    @Column(nullable = false, unique = true)
    private String name;

    public Artist(){}

    public Artist(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "\n" + "{" + "id=" + id + ", "+ "имя: "+name+"}";
    }
}
