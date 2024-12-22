import CRUD.CRUDArtist;
import CRUD.CRUDAlbum;
import CRUD.CRUDTrack;
import Entity.Album;
import Entity.Artist;
import Entity.Track;

import java.sql.Time;

public class Main {
    public static void main(String[] args) {

        CRUDArtist crudArtist = new CRUDArtist();

        //System.out.println(crudArtist.getAllArtists());

        //Artist newArtist = new Artist("New artist5");
        //crudArtist.addArtist(newArtist);

        //Album updateAlbum = crudAlbum.getAlbum(21);
        //Artist updateArtist = crudArtist.getArtist(1);
        //updateArtist.setName("Morgenshtern");
        //crudArtist.updateArtist(updateArtist);

        //crudArtist.deleteArtist("New artist");



        //System.out.println(crudAlbum.getAllAlbums());
        /*
        int g = 1;
        int counter = 0;
        CRUDTrack crudTrack = new CRUDTrack();
        CRUDAlbum crudAlbum = new CRUDAlbum();
        for (int i = 0; i <= 28; i++) {
            if(counter >1) {
            g++;
            counter = 0;
            }

            Track newTrack = new Track("New song" + i, Time.valueOf("00:05:00"), crudAlbum.getAlbum(g));
            crudTrack.addTrack(newTrack);
            counter++;
        }*/

        //Album updateAlbum = crudAlbum.getAlbum(21);
        //updateAlbum.setGenre("Jazz");
        //updateAlbum.setArtist_id(1);
        // updateAlbum.setTitle("New update album");

        //crudAlbum.updateAlbum(updateAlbum);

        //crudAlbum.deleteAlbum("New update album");

        //CRUDOperations.CRUDTrack crudTrack = new CRUDOperations.CRUDTrack();

        // Добавление трека
        //Track newTrack = new Track("Sample Track", Time.valueOf("00:03:00"), 1);
        //crudTrack.addTrack(newTrack);

        //Track updateTrack = crudTrack.getTrack(2);
        //updateTrack.setTitle("Update Track");
        //crudTrack.updateTrack(updateTrack);
        //System.out.println(updateTrack);

        //crudTrack.addTrack(newTrack);


        //System.out.println(crudTrack.getAllTracks());
        //crudTrack.deleteTrack("Sample Track");


    }
}