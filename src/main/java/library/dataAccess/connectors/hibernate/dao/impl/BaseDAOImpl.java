package library.dataAccess.connectors.hibernate.dao.impl;

import library.dataAccess.connectors.hibernate.dao.BaseDAO;
import library.dataAccess.connectors.hibernate.entities.EntityBase;
import library.dataAccess.connectors.hibernate.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
            session = HibernateUtil.getSession();
            entities = session.createQuery("FROM " + type.getSimpleName()).list();
        } finally {HibernateUtil.close(session);}
        return entities;
    }
    @Override
    public T getEntityById(Integer id) {
        Session session = null;
        T entity = null;
        try {
            session = HibernateUtil.getSession();
            entity = session.byId(type).load(id);
        } finally {HibernateUtil.close(session);}
        return entity;
    }
    @Override
    public void update(EntityBase entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {HibernateUtil.close(session);}
    }
    @Override
    public void delete(EntityBase entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {HibernateUtil.close(session);}
    }
    @Override
    public int create(EntityBase entity){
        Session session = null;
        Transaction transaction = null;
        Integer idSaved = 0;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            idSaved = (Integer) session.save(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {HibernateUtil.close(session);}
        return idSaved;
    }
    @Override
    public List<EntityBase> searchEntityByName(EntityBase entity) {
        return null;
    }
}
