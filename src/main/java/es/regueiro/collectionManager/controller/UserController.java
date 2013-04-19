package es.regueiro.collectionManager.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.regueiro.collectionManager.dao.UserDao;
import es.regueiro.collectionManager.model.user.Role;
import es.regueiro.collectionManager.model.user.User;
import es.regueiro.collectionManager.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@RequestMapping(value = "/account/login", method = RequestMethod.GET)
	public String login() {
		logger.info("Requested login");
		//TODO check if passwords match.
		return "login.html";
	}

	@RequestMapping(value = "/account/register", method = RequestMethod.GET)
	public String register() {
		logger.info("Requested register");
		
		return "register.html";
	}

	@RequestMapping(value = "/account/settings", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public String settings() {
		logger.info("Requested settings");
		return "settings.html";
	}

	//Temp testing mapping
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		model.addAttribute("user", new User());
		model.addAttribute("users", userService.listAll());
		return "user.html";
	}

	//Temp testing mapping
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") User user,
			BindingResult result) {
		if (result.hasErrors()) {
			logger.debug("Error Username: "+user.getUsername());
			logger.debug("Error Email: "+user.getEmail());
			logger.debug("Error Password: "+user.getPassword());
			//TODO return errors.
			
			return "register.html";
		} else {
			logger.debug("Username: "+user.getUsername());
			logger.debug("Email: "+user.getEmail());
			logger.debug("Password: "+user.getPassword());
			userService.registerUser(user);

			//TODO create welcome page or autologin after register.
			
			return "redirect:/account/settings";
		}
	}

	//Temp testing mapping
	@RequestMapping("/user/delete/{userId}")
	public String deleteUser(@PathVariable("userId") Long userId) {

		userService.deleteUser(userService.findById(userId));

		return "redirect:/user";
	}
}
