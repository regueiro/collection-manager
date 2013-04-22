package es.regueiro.collectionManager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.regueiro.collectionManager.service.UserService;

/**
 * Handles requests for the administration pages.
 */
@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	@Autowired
	private UserService userService;

	/**
	 * Administration home.
	 * 
	 * @return the view name
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('Admin')")
	public String adminHome() {
		logger.info("Requested admin index");
		return "admin/index.html";
	}

	/**
	 * User administration
	 * 
	 * @param model
	 *            the model
	 * @return the view name
	 */
	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	@PreAuthorize("hasRole('Admin')")
	public String adminUsers(ModelMap model) {
		model.addAttribute("users", userService.listAll());
		logger.info("Requested admin users");
		return "admin/users.html";
	}

}
