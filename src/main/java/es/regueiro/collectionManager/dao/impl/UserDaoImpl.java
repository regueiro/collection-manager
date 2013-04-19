package es.regueiro.collectionManager.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.regueiro.collectionManager.dao.UserDao;
import es.regueiro.collectionManager.model.user.User;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, Long> implements UserDao {

	protected UserDaoImpl() {
		super(User.class);
	}

	@Override
	public void saveUser(User user) {
		saveOrUpdate(user);
	}

	@Override
	public List findUsers(String userName) {
		return findByCriteria(Restrictions.like("username", userName,
				MatchMode.START));
	}

	@Override
	public User findByUsername(String username) {
		return findOneByCriteria(Restrictions.eq("username", username));
	}

}