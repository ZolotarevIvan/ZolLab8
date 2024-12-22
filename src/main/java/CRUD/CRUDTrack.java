package CRUD;

import Entity.Track;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class CRUDTrack {

    private SessionFactory sessionFactory;

    public CRUDTrack(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Метод для добавления трека
    public void addTrack(Track track) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(track);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Метод для редактирования трека
    public void updateTrack(Track track) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(track);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTrack(String title) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Используем HQL для поиска трека по title
            Query<Track> query = session.createQuery("FROM Track WHERE title = :title", Track.class);
            query.setParameter("title", title);
            Track track = query.uniqueResult(); // Получаем уникальный результат

            if (track != null) {
                session.delete(track); // Удаляем трек, если он найден
            }

            transaction.commit(); // Подтверждаем транзакцию
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Откат транзакции в случае ошибки
            }
            e.printStackTrace(); // Выводим стек вызовов
        }
    }

    public void deleteTrack(int trackId){
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Track track = session.get(Track.class, trackId);
            if (track != null) {
                session.delete(track);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Track getTrack(int trackId){
        try (Session session = sessionFactory.openSession()) {
            return session.get(Track.class, trackId); // Получаем трек по его идентификатору
        } catch (Exception e) {
            e.printStackTrace(); // Выводим стек вызовов в случае ошибки
            return null; // Возвращаем null, если произошла ошибка
        }
    }

    // Метод для получения всех треков
    public List<Track> getAllTracks() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Track", Track.class).list();
        }
    }

    public List<Object[]> getTracksWithArtists() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT t.title, a.name FROM Track t JOIN t.album al JOIN al.artist a";
            Query<Object[]> query = session.createQuery(hql, Object[].class);
            return query.list();
        }
    }

}
