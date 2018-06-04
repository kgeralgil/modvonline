<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="icon" href="./assets/favicon.ico">

	<title>Sigesu - Tottus</title>

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="/bootstrap.min.css" />

	<!-- Custom styles for this template -->
	<link href="/main.css" rel="stylesheet" />
</head>

<body>
	<tiles:insertAttribute name="header" />
	<div class="container-fluid">
		<div class="row">
			<tiles:insertAttribute name="menu" />			
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<tiles:insertAttribute name="body" />
				<tiles:insertAttribute name="footer" />			
			</main>
		</div>
	</div>
</body>
</html>