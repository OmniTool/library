package library.dataAccess.connectors.hibernate.dao.impl;

import library.dataAccess.connectors.hibernate.dao.BaseDAO;
import library.dataAccess.connectors.hibernate.entities.EntityBaseHiber;
import library.dataAccess.connectors.hibernate.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BaseDAOImpl<T extends EntityBaseHiber> implements BaseDAO<EntityBaseHiber, Integer> {

    Class<T> type;

    public BaseDAOImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public List<EntityBaseHiber> getAll() {
        Session session = null;
        List<EntityBaseHiber> entities = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            entities = session.createQuery("FROM " + type.getSimpleName()).list();
        } finally {HibernateUtil.close(session);}
        return entities;
    }

    @Override
    public T getEntityById(Integer id) {
        Session session = null;
        Transaction transaction = null;
        List<EntityBaseHiber> entities = new ArrayList();
        T entity = null;
        try {
            session = HibernateUtil.getSession();
            entity = session.byId(type).load(id);
        } finally {HibernateUtil.close(session);}
        return entity;
    }

    @Override
    public void update(EntityBaseHiber entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();

            //session.refresh(entity);
            session.update(entity);
            //session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {HibernateUtil.close(session);}
    }

    @Override
    public void delete(EntityBaseHiber entity) {
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
    public int create(EntityBaseHiber entity){
        Session session = null;
        Transaction transaction = null;
        Integer idSaved = null;
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
    public List<EntityBaseHiber> searchEntityByName(EntityBaseHiber entity) {
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
