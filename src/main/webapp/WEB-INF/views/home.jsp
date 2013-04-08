<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>


<link rel="stylesheet" href="${pageScope.bootstrapStylesheetUrl}" />
<link rel="stylesheet"
	href="${pageScope.bootstrapResponsiveStylesheetUrl}" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="${pageScope.bootstrapJavascriptUrl}"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2">Sidebar</div>
			<img src="resources/lynch.png" />
			<div class="span10">

				<h1>Collection Manager</h1>
				Manage your collection


				<p>The time on the server is ${serverTime}.</p>

				<h2>Testing Connection</h2>

				<p>${test}</p>

				<h2>Testing Settings</h2>

				<p>${settings}</p>

				<h2>Testing API</h2>

				<p>${testapi}</p>
				
				<a href="testAPI">Test Thymeleaf views</a>

			</div>
		</div>
	</div>
</body>
</html>