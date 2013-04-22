package es.regueiro.collectionManager.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.regueiro.collectionManager.musicbrainz.MusicBrainzManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private MusicBrainzManager manager;

	/**
	 * The main page.
	 * 
	 * @return the view name
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("Requested index");
		return "index.html";
	}

	/**
	 * The about page.
	 * 
	 * @return the view name
	 */
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() {
		logger.info("Requested about");
		return "about.html";
	}

	/**
	 * The contact page.
	 * 
	 * @return the view name
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact() {
		logger.info("Requested contact");
		return "contact.html";
	}

	/**
	 * TEMP: Temp page for API testing
	 * 
	 * @param locale
	 *            the locale
	 * @param model
	 *            the model
	 * @return the view name
	 */
	@RequestMapping(value = "/testAPI", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public String testAPI(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		// manager.setAuthentication("burn",
		// "e8d9eb1ad4422997f374336a4f440f0a58870431");
		// manager.setAuthentication("springtest",
		// "f151180b4819548af1170ccd9d97de52dc82e303");
		// model.addAttribute("testapi", manager.activityService().community());

		// model.addAttribute("settings", manager.accountService().settings());

		model.addAttribute("test", manager.artistService().audioslave()
				.getName());

		return "home.html";
	}

	/**
	 * Test Temp page for API testing (jsp views)
	 * 
	 * @param locale
	 *            the locale
	 * @param model
	 *            the model
	 * @return the view name
	 */
	@RequestMapping(value = "/testAPIJSP", method = RequestMethod.GET)
	public String testJSP(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		// manager.setAuthentication("burn",
		// "e8d9eb1ad4422997f374336a4f440f0a58870431");
		// manager.setAuthentication("springtest",
		// "f151180b4819548af1170ccd9d97de52dc82e303");
		// model.addAttribute("testapi", manager.activityService().community());
		// model.addAttribute("test", manager.accountService().test());
		// model.addAttribute("settings", manager.accountService().settings());
		//
		return "home";
	}

	/**
	 * TEMP: Default template.
	 * 
	 * @return the view name
	 */
	@RequestMapping(value = "/defaultTemplate", method = RequestMethod.GET)
	public String defaultTemplate() {
		logger.info("Requested default template");

		return "defaultTemplate.html";
	}

}
