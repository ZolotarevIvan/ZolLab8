package CRUD;

import Entity.Album;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CRUDAlbum {

    private SessionFactory sessionFactory;

    public CRUDAlbum(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }


    public void addAlbum(Album album) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(album);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public void updateAlbum(Album album) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(album);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteAlbum(String title) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Используем HQL для поиска трека по title
            Query<Album> query = session.createQuery("FROM Album WHERE title = :title", Album.class);
            query.setParameter("title", title);
            Album album= query.uniqueResult(); // Получаем уникальный результат

            if (album != null) {
                session.delete(album); // Удаляем трек, если он найден
            }

            transaction.commit(); // Подтверждаем транзакцию
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Откат транзакции в случае ошибки
            }
            e.printStackTrace(); // Выводим стек вызовов
        }
    }

    public void deleteAlbum(int albumId){
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Album album = session.get(Album.class, albumId);
            if (album != null) {
                session.delete(album);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Album getAlbum(int albumId){
        try (Session session = sessionFactory.openSession()) {
            return session.get(Album.class, albumId); // Получаем трек по его идентификатору
        } catch (Exception e) {
            e.printStackTrace(); // Выводим стек вызовов в случае ошибки
            return null; // Возвращаем null, если произошла ошибка
        }
    }


    public List<Album> getAllAlbums() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Album", Album.class).list();
        }
    }
}