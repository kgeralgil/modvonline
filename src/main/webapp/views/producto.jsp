<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<h5>Busqueda de Productos</h5>
<form method="get" action="<c:url value='/productos/consultar' />">

	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

		<div class="col-sm-11">
			<label>Nombre de Producto :</label> 
			<input name="textoBusqueda" type="text" class="form-control"
				placeholder="Escribe aqui el producto a consultar" />
		</div>
		<div class="col-sm-1">
			<input class="btn btn-outline-secondary" type="submit" value="Consultar" />
		</div>
	</div>
</form>

<div class="container">
	<c:choose>
		<c:when test="${modo=='MODO_HOME'}"></c:when>
		<c:when test="${modo=='MODO_VACIO'}">
			<h4>No se han encontrado resultados para su b&uacute;squeda</h4>
		</c:when>
		<c:when test="${modo=='MODO_CONSULTA'}">
			<div class="row">
				<c:forEach var="producto" items="${productos}">
					<div class="card-deck mb-4 text-center">
						<div class="col-4 col-md">
							<div class="card mb-4 box-shadow">
								<div class="card-header">
									<img src="data:image/jpeg;base64,${producto.imagen}"
										class="img-thumbnail" />

									<div style="max-width: 139px;">
										<h4 class="my-0 font-weight-normal">
											${producto.descripcion}</h4>
									</div>
								</div>
								<div class="card-body">
									<h1 class="card-title pricing-card-title">
										S/.
										<fmt:formatNumber type="number" minFractionDigits="2"
											maxFractionDigits="2" value="${producto.precioUnitario}" />
									</h1>
									<p class="card-text">${producto.marca}</p>
								</div>

								<c:url value="/productos/agregar" var="agregarURL">
									<c:param name="idProducto" value="${producto.idProducto}" />
								</c:url>
								<a class="btn btn-lg btn-block btn-outline-primary"
									href="<c:out value='${agregarURL}' />">Agregar</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:when test="${modo=='MODO_RECOMENDACION'}">
			<div class="row">
				<div class="card mb-4 box-shadow">
					<div class="card-header">
						<img src="data:image/jpeg;base64,${productoAgregado.imagen}"
							class="img-thumbnail" height="139" width="139" />
						<h4 class="my-0 font-weight-normal">${productoAgregado.descripcion}</h4>
					</div>
					<div class="card-body">
						<h1 class="card-title pricing-card-title">
							S/.
							<fmt:formatNumber type="number" minFractionDigits="2"
								maxFractionDigits="2" value="${productoAgregado.precioUnitario}" />
						</h1>
						<p class="card-text">${productoAgregado.marca}</p>
					</div>
					<a class="btn btn-lg btn-block btn-outline-primary" href="/">Agregar</a>
				</div>
			</div>

			<c:if test="${recomendacion=='MODO_LISTA'}">
				<h2>Otros productos que te podrian interesar</h2>
				<br />
				<div class="row">
					<c:forEach var="producto" items="${productosRecomendados}">
						<div class="card-deck mb-4 text-center">
							<div class="col-4 col-md">
								<div class="card mb-4 box-shadow">
									<div class="card-header">
										<img src="data:image/jpeg;base64,${producto.imagen}"
											class="img-thumbnail" height="139" width="139" />
										<h4 class="my-0 font-weight-normal">${producto.descripcion}</h4>
									</div>
									<div class="card-body">
										<h1 class="card-title pricing-card-title">
											S/.
											<fmt:formatNumber type="number" minFractionDigits="2"
												maxFractionDigits="2" value="${producto.precioUnitario}" />
										</h1>
										<p class="card-text">${producto.marca}</p>
									</div>
									<a class="btn btn-lg btn-block btn-outline-primary" href="/}">Agregar</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:if>
		</c:when>
	</c:choose>
</div>