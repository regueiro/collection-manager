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

/**
 * The Hibernate implementation of the Abstract DAO
 * 
 * @param <E>
 *            the element type that the implementation will support
 * @param <N>
 *            the type of the element id
 */
@Component
public abstract class HibernateAbstractDao<E, N extends Serializable>
		implements AbstractDao<E, N> {

	/**
	 * The class of the entity, needed for the methods that query the database
	 * and stored because of type erasure
	 */
	private Class<E> entityClass;

	/**
	 * The base constructor for Hibernate DAOs
	 * 
	 * @param entityClass
	 *            the class of the entity type
	 */
	protected HibernateAbstractDao(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	/** The session factory for Hibernate. */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Gets the current session.
	 * 
	 * If there is no current session, the session factory will automatically
	 * create one.
	 * 
	 * @return the current session
	 */
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.regueiro.collectionManager.dao.AbstractDao#getById(java.io.Serializable
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E getById(N id) {
		return (E) getCurrentSession().get(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.regueiro.collectionManager.dao.AbstractDao#saveOrUpdate(java.lang.
	 * Object)
	 */
	@Override
	public void saveOrUpdate(E e) {
		getCurrentSession().saveOrUpdate(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.regueiro.collectionManager.dao.AbstractDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(E e) {
		getCurrentSession().delete(e);
	}

	/**
	 * Creates a criteria to find elements on the database.
	 * 
	 * @param criterion
	 *            the criterion that the elements must meet
	 * @return the criteria
	 */
	private Criteria find(Criterion criterion) {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(criterion);
		return criteria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.regueiro.collectionManager.dao.AbstractDao#findByCriteria(org.hibernate
	 * .criterion.Criterion)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<E> findByCriteria(Criterion criterion) {
		return find(criterion).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.regueiro.collectionManager.dao.AbstractDao#getByCriteria(org.hibernate
	 * .criterion.Criterion)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E getByCriteria(Criterion criterion) {
		return (E) find(criterion).uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.regueiro.collectionManager.dao.AbstractDao#listAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<E> listAll() {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		return criteria.list();
	}
}