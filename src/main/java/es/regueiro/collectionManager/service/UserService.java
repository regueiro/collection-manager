package es.regueiro.collectionManager.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.regueiro.collectionManager.model.user.User;

/**
 * The interface for the user services.
 */
public interface UserService {

	/**
	 * Find a single user by username.
	 * 
	 * @param username
	 *            the username
	 * @return the user if found
	 */
	User findByUsername(String username);

	/**
	 * Find a single user by id.
	 * 
	 * @param id
	 *            the id
	 * @return the user if found
	 */
	User findById(long id);

	/**
	 * Save an user.
	 * 
	 * @param user
	 *            the user
	 */
	void saveUser(User user);

	/**
	 * Delete an user.
	 * 
	 * @param user
	 *            the user
	 */
	void deleteUser(User user);

	/**
	 * Find users by username.
	 * 
	 * @param username
	 *            the username
	 * @return the list of found users
	 */
	List<User> findUsers(String username);

	/**
	 * List all users.
	 * 
	 * @return the full list of users
	 */
	@Transactional
	List<User> listAll();

	/**
	 * Register an user.
	 * 
	 * @param user
	 *            the user to register
	 */
	void registerUser(User user);
}