package es.regueiro.collectionManager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.regueiro.collectionManager.dao.UserDao;
import es.regueiro.collectionManager.model.user.Role;
import es.regueiro.collectionManager.model.user.User;
import es.regueiro.collectionManager.service.UserService;

import java.util.List;

import javax.validation.Valid;

/**
 * The Hibernate User Service.
 */
@Service("userService")
@Transactional(readOnly = true)
public class HibernateUserService implements UserService {

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.regueiro.collectionManager.service.UserService#findByUsername(java
	 * .lang.String)
	 */
	@Override
	public User findByUsername(String username) {
		return userDao.getByUsername(username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.regueiro.collectionManager.service.UserService#saveUser(es.regueiro
	 * .collectionManager.model.user.User)
	 */
	@Override
	@Transactional(readOnly = false)
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.regueiro.collectionManager.service.UserService#deleteUser(es.regueiro
	 * .collectionManager.model.user.User)
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteUser(User user) {
		if (user != null) {
			userDao.delete(user);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.regueiro.collectionManager.service.UserService#findUsers(java.lang
	 * .String)
	 */
	@Override
	public List<User> findUsers(String user) {
		return userDao.findUsersByUsername(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.regueiro.collectionManager.service.UserService#findById(long)
	 */
	@Override
	public User findById(long id) {
		return userDao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see es.regueiro.collectionManager.service.UserService#listAll()
	 */
	@Override
	public List<User> listAll() {
		return userDao.listAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * es.regueiro.collectionManager.service.UserService#registerUser(es.regueiro
	 * .collectionManager.model.user.User)
	 */
	@Override
	@Transactional(readOnly = false)
	public void registerUser(@Valid User user) {
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		String result = encoder.encode(user.getPassword());
		user.setPassword(result);
		result = null;

		List<Role> roleList = user.getRoleList();
		roleList.add(Role.USER);
		roleList.add(Role.ADMIN);
		user.setRoleList(roleList);

		saveUser(user);
	}
}