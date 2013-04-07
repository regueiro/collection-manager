<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url scope="page" var="bootstrapStylesheetUrl"
	value="/resources/assets/css/bootstrap.css" />
<spring:url scope="page" var="bootstrapResponsiveStylesheetUrl"
	value="/resources/assets/css/bootstrap-responsive.css" />
<spring:url scope="page" var="bootstrapJavascriptUrl"
	value="/resources/assets/js/bootstrap.js" />