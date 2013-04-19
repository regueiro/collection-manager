package es.regueiro.collectionManager.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.regueiro.collectionManager.dao.AbstractDao;
 
@Component
public abstract class AbstractDaoImpl<E, N extends Serializable> implements AbstractDao<E,N> {
 
    private Class<E> entityClass;
 
    protected AbstractDaoImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }
 
    @Autowired
    private SessionFactory sessionFactory;
 
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
 
    @SuppressWarnings("unchecked")
	@Override
    public E findById(N id) {
        return (E) getCurrentSession().get(entityClass, id);
    }
 
    @Override
    public void saveOrUpdate(E e) {
        getCurrentSession().saveOrUpdate(e);
    }
 
    @Override
    public void delete(E e) {
        getCurrentSession().delete(e);
    }
 
    @SuppressWarnings("unchecked")
    private Criteria find(Criterion criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        return criteria;
    }
    
	@SuppressWarnings("unchecked")
	@Override
    public List<E> findByCriteria(Criterion criterion) {
        return find(criterion).list();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public E findOneByCriteria(Criterion criterion) {
    	return (E) find(criterion).uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<E> listAll() {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        return criteria.list();
    }
}