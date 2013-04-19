package es.regueiro.collectionManager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * Handles requests for the application home page.
 */
@Controller
public class CollectionController {
	
	private static final Logger logger = LoggerFactory.getLogger(CollectionController.class);
	

	
	@RequestMapping(value = "/collections", method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public String collections() {
		logger.info("Requested collections");
		return "collections/collections.html";
	}
	
}
