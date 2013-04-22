package es.regueiro.collectionManager.util;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.regueiro.collectionManager.dao.UserDao;
import es.regueiro.collectionManager.model.user.Role;

/**
 * An UserDetailsService implementation to link spring security users with my
 * domain users.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		es.regueiro.collectionManager.model.user.User user = dao
				.getByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("user not found");

		String name = user.getUsername();
		String password = user.getPassword();
		boolean enabled = true; // user.isActive();
		boolean accountNonExpired = true; // user.isActive();
		boolean credentialsNonExpired = true; // user.isActive();
		boolean accountNonLocked = true; // user.isActive();

		logger.debug("User - name: " + name);
		logger.debug("User - password: " + password);
		logger.debug("User - enabled: " + enabled);

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : user.getRoleList()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			logger.debug("User - authority: " + role.getName());
		}

		User securityUser = new User(name, password, enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);
		return securityUser;
	}
}