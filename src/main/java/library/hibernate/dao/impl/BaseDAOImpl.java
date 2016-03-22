package library.hibernate.dao.impl;

import library.hibernate.dao.BaseDAO;
import library.hibernate.entities.EntityBase;
import library.hibernate.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDAOImpl<T extends EntityBase> implements BaseDAO<EntityBase, Integer> {

    Class<T> type;

    public BaseDAOImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public List<EntityBase> getAll() {
        Session session = null;
        List<EntityBase> entities = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            entities = session.createQuery("FROM " + type.getSimpleName()).list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entities;
    }

    @Override
    public T getEntityById(Integer id) {
        Session session = null;
        Transaction transaction = null;
        List<EntityBase> entities = new ArrayList();
        T entity = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            entity = session.byId(type).load(id);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entity;
    }

    @Override
    public void update(EntityBase entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.update(entity);
            //session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(EntityBase entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public int create(EntityBase entity){
        Session session = null;
        Transaction transaction = null;
        Integer idSaved = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            idSaved = (Integer) session.save(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return idSaved;
    }

    @Override
    public List<EntityBase> searchEntityByName(EntityBase entity) {
        return null;
    }


//    @Override
//    public List<EntityBase> searchEntityByName(EntityBase entity) throws SQLException, NamingException {
//        return null;
//    }

//    @Autowired
//    private SessionFactory sessionFactory;
//
//    private Session getCurrentSession() {
//        return this.sessionFactory.getCurrentSession();
//    }


}
