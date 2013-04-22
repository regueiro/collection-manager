package es.regueiro.collectionManager.dao.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import es.regueiro.collectionManager.dao.UserDao;
import es.regueiro.collectionManager.model.user.User;

/**
 * The Hibernate implementation of the User DAO
 */
@Repository
public class HibernateUserDao extends HibernateAbstractDao<User, Long>
		implements UserDao {

	/**
	 * Instantiates a new hibernate user dao.
	 */
	protected HibernateUserDao() {
		super(User.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.regueiro.collectionManager.dao.UserDao#saveUser(es.regueiro.
	 * collectionManager.model.user.User)
	 */
	@Override
	public void saveUser(User user) {
		saveOrUpdate(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.regueiro.collectionManager.dao.UserDao#findUsersByUsername(java.lang
	 * .String)
	 */
	@Override
	public List<User> findUsersByUsername(String userName) {
		return findByCriteria(Restrictions.like("username", userName,
				MatchMode.START));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.regueiro.collectionManager.dao.UserDao#getByUsername(java.lang.String)
	 */
	@Override
	public User getByUsername(String username) {
		return getByCriteria(Restrictions.eq("username", username));
	}

}