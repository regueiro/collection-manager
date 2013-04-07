package es.regueiro.collectionManager;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
//	@Autowired
//	private TraktManager manager;
//	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		logger.info("Requested index");
		return "index.html";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		//manager.setAuthentication("burn", "e8d9eb1ad4422997f374336a4f440f0a58870431");
//		manager.setAuthentication("springtest", "f151180b4819548af1170ccd9d97de52dc82e303");
		//model.addAttribute("testapi", manager.activityService().community());
//		model.addAttribute("test", manager.accountService().test());
//		model.addAttribute("settings", manager.accountService().settings());
//		
		return "home.html";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		//manager.setAuthentication("burn", "e8d9eb1ad4422997f374336a4f440f0a58870431");
//				manager.setAuthentication("springtest", "f151180b4819548af1170ccd9d97de52dc82e303");
		//model.addAttribute("testapi", manager.activityService().community());
//		model.addAttribute("test", manager.accountService().test());
//		model.addAttribute("settings", manager.accountService().settings());
//		
		return "home";
	}
	
}
