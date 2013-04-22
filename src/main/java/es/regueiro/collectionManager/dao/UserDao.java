package es.regueiro.collectionManager.dao;

import java.util.List;

import es.regueiro.collectionManager.model.user.User;

/**
 * The User DAO.
 */
public interface UserDao extends AbstractDao<User, Long> {

	/**
	 * Save an user.
	 * 
	 * @param user
	 *            the user to save
	 */
	void saveUser(User user);

	/**
	 * Find users by username.
	 * 
	 * @param username
	 *            the username to search
	 * @return the list of users found
	 */
	List<User> findUsersByUsername(String username);

	/**
	 * Get an user by username.
	 * 
	 * @param username
	 *            the username of the user
	 * @return the user if found
	 */
	User getByUsername(String username);
}