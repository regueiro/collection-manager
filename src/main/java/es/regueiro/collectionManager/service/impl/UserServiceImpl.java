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

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = false)
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteUser(User user) {
		if (user != null) {
			userDao.delete(user);
		}
	}

	@Override
	public List<User> findUsers(String user) {
		return userDao.findUsers(user);
	}

	@Override
	public User findById(long id) {
		return userDao.findById(id);
	}

	@Override
	public List<User> listAll() {
		return userDao.listAll();
	}

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