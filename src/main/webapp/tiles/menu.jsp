<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="col-md-2 d-none d-md-block bg-light sidebar">
	<div class="sidebar-sticky">
		<ul class="nav flex-column">
			<li class="nav-item">
				<a class="nav-link active" href="<c:out value='/productos/' />">
					<span data-feather="product"></span> Productos <span class="sr-only">(current)</span>
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<c:out value='/descuentos/' />">
					<span data-feather="sells"></span> Descuentos Diarios
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<c:out value='/analisis/' />">
					<span data-feather="file"></span> Estadístico de Ventas Online
				</a>
			</li>
		</ul>
	</div>
</nav>