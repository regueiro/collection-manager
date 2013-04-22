package es.regueiro.collectionManager.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.regueiro.collectionManager.model.user.User;
import es.regueiro.collectionManager.service.UserService;

/**
 * Handles requests for the user related pages.
 * 
 * - Registration
 * - Login
 * - User Settings
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	/**
	 * The login page.
	 * 
	 * @return the view name
	 */
	@RequestMapping(value = "/account/login", method = RequestMethod.GET)
	public String login() {
		logger.info("Requested login");
		// TODO check if passwords match.
		return "login.html";
	}

	/**
	 * The register page.
	 * 
	 * @return the view name
	 */
	@RequestMapping(value = "/account/register", method = RequestMethod.GET)
	public String register() {
		logger.info("Requested register");

		return "register.html";
	}

	/**
	 * The settings page.
	 * 
	 * @return the view name
	 */
	@RequestMapping(value = "/account/settings", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public String settings() {
		logger.info("Requested settings");
		return "settings.html";
	}

	/**
	 * Receives post requests to register users
	 * 
	 * @param user
	 *            the user to register
	 * @param result
	 *            the result of the validation
	 * @return the view name
	 */
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") User user,
			BindingResult result) {
		if (result.hasErrors()) {
			logger.debug("Error Username: " + user.getUsername());
			logger.debug("Error Email: " + user.getEmail());
			logger.debug("Error Password: " + user.getPassword());
			// TODO return errors.

			return "register.html";
		} else {
			logger.debug("Username: " + user.getUsername());
			logger.debug("Email: " + user.getEmail());
			logger.debug("Password: " + user.getPassword());
			userService.registerUser(user);

			// TODO create welcome page or autologin after register.

			return "redirect:/account/settings";
		}
	}

	/**
	 * TEMP Delete user.
	 * 
	 * TODO - change so the user can only delete his own account
	 * TODO - create a delete user method in the admin controller
	 * 
	 * @param userId
	 *            the user id
	 * @return the view name
	 */
	@RequestMapping("/user/delete/{userId}")
	public String deleteUser(@PathVariable("userId") Long userId) {

		userService.deleteUser(userService.findById(userId));

		return "redirect:/user";
	}
}
