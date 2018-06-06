<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="icon" href="./assets/favicon.ico">

	<title>Sigesu - Tottus</title>

	<!-- JS -->
	<script src="/jquery-3.3.1.min.js"></script>
	<script src="/toastr.js"></script>
	
	<!-- CSS -->
	<link rel="stylesheet" href="/bootstrap.min.css" />
	<link rel="stylesheet" href="/toastr.css" />
	<link rel="stylesheet" href="/main.css" />
</head>

<body>
	<tiles:insertAttribute name="header" />
	<div class="container-fluid">
		<div class="row">
			<tiles:insertAttribute name="menu" />			
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4" style="margin-top: 30px;">
				<tiles:insertAttribute name="body" />
				<tiles:insertAttribute name="footer" />			
			</main>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			toastr.options = {
			  "positionClass": "toast-top-right",
			  "showDuration": "300",
			  "hideDuration": "1000",
			  "timeOut": "5000",
			  "extendedTimeOut": "1000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			};
			
			var msg = "${msg}";
			if(msg != null && msg != ""){
				toastr.info(msg)	
			}
			
		});
	</script>
</body>
</html>