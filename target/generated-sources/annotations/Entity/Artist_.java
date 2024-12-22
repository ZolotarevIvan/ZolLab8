package Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Artist.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Artist_ {

	public static final String NAME = "name";
	public static final String ID = "id";

	
	/**
	 * @see Entity.Artist#name
	 **/
	public static volatile SingularAttribute<Artist, String> name;
	
	/**
	 * @see Entity.Artist#id
	 **/
	public static volatile SingularAttribute<Artist, Integer> id;
	
	/**
	 * @see Entity.Artist
	 **/
	public static volatile EntityType<Artist> class_;

}

