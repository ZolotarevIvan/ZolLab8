package CRUD;

import Entity.Artist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CRUDArtist {

    private SessionFactory sessionFactory;

    public CRUDArtist(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addArtist(Artist artist) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(artist);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public void updateArtist(Artist artist) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(artist);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteArtist(String name) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Используем HQL для поиска трека по title
            Query<Artist> query = session.createQuery("FROM Artist WHERE name = :name", Artist.class);
            query.setParameter("name", name);
            Artist artist= query.uniqueResult(); // Получаем уникальный результат

            if (artist != null) {
                session.delete(artist); // Удаляем трек, если он найден
            }

            transaction.commit(); // Подтверждаем транзакцию
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Откат транзакции в случае ошибки
            }
            e.printStackTrace(); // Выводим стек вызовов
        }
    }

    public void deleteArtist(int artistId){
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Artist artist= session.get(Artist.class, artistId);
            if (artist != null) {
                session.delete(artist);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Artist getArtist(int artistId){
        try (Session session = sessionFactory.openSession()) {
            return session.get(Artist.class, artistId); // Получаем трек по его идентификатору
        } catch (Exception e) {
            e.printStackTrace(); // Выводим стек вызовов в случае ошибки
            return null; // Возвращаем null, если произошла ошибка
        }
    }


    public List<Artist> getAllArtists() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Artist", Artist.class).list();
        }
    }

    public List<Object[]> getArtistsWithAlbumCount() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT a.artist, COUNT(a) FROM Album a GROUP BY a.artist";
            Query<Object[]> query = session.createQuery(hql, Object[].class);
            return query.getResultList();
        }
    }

    public List<Object[]> getArtistsWithTrackCount() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT a.name, COUNT(t) " +
                    "FROM Artist a " +
                    "LEFT JOIN Album al ON al.artist.id = a.id " + // Используйте явное связывание
                    "LEFT JOIN Track t ON t.album.id = al.id " + // Используйте явное связывание
                    "GROUP BY a.name";

            Query<Object[]> query = session.createQuery(hql, Object[].class);
            return query.list();
        }
    }

}
