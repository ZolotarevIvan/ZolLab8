package Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.sql.Time;

@StaticMetamodel(Track.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Track_ {

	public static final String DURATION = "duration";
	public static final String ALBUM = "album";
	public static final String ID = "id";
	public static final String TITLE = "title";

	
	/**
	 * @see Entity.Track#duration
	 **/
	public static volatile SingularAttribute<Track, Time> duration;
	
	/**
	 * @see Entity.Track#album
	 **/
	public static volatile SingularAttribute<Track, Album> album;
	
	/**
	 * @see Entity.Track#id
	 **/
	public static volatile SingularAttribute<Track, Integer> id;
	
	/**
	 * @see Entity.Track#title
	 **/
	public static volatile SingularAttribute<Track, String> title;
	
	/**
	 * @see Entity.Track
	 **/
	public static volatile EntityType<Track> class_;

}

