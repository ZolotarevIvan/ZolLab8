package Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Album.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Album_ {

	public static final String ARTIST = "artist";
	public static final String GENRE = "genre";
	public static final String ID = "id";
	public static final String TITLE = "title";

	
	/**
	 * @see Entity.Album#artist
	 **/
	public static volatile SingularAttribute<Album, Artist> artist;
	
	/**
	 * @see Entity.Album#genre
	 **/
	public static volatile SingularAttribute<Album, String> genre;
	
	/**
	 * @see Entity.Album#id
	 **/
	public static volatile SingularAttribute<Album, Integer> id;
	
	/**
	 * @see Entity.Album#title
	 **/
	public static volatile SingularAttribute<Album, String> title;
	
	/**
	 * @see Entity.Album
	 **/
	public static volatile EntityType<Album> class_;

}

