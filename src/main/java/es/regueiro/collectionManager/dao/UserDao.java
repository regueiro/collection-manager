package es.regueiro.collectionManager.dao;

import java.util.List;

import es.regueiro.collectionManager.model.user.User;

public interface UserDao extends AbstractDao<User, Long> {
	void saveUser(User user);

	List<User> findUsers(String username);
	User findByUsername(String username);
}