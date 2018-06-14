<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<form method="post" action="<c:url value='/analisis/consultar' />">
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

		<div class="input-group mb-3">

			<label for="fechaIni">Fecha Inicial:</label> <input name="fechaIni"
				type="date" class="form-control" pattern="dd/MM/yyyy"
				placeholder="dd/mm/yyyy" /> <label for="fechaFin">Fecha
				Final:</label> <input name="fechaFin" type="date" class="form-control"
				pattern="dd/MM/yyyy" placeholder="dd/mm/yyyy" /> <input
				class="btn btn-outline-secondary" type="submit" value="Ver Reporte" />
		</div>
	</div>
</form>

<div class="container">
	<c:if test="${CproductosParaDescuento=='MODO_VACIO'}">
		<h1>No hay productos con niveles de venta inferiores al esperado</h1>
	</c:if>
	<c:if test="${CproductosParaDescuento=='MODO_CONSULTA'}">
		<div class="row">
			<table class="table table-bordered">
				<tr class="table-success">
					<th class="table-hover">Producto</th>
					<th>Porcentaje de Venta</th>
					<th>Stock Actual</th>
					<th>Seleccione</th>
				</tr>
				<c:forEach var="producto" items="${productosParaDescuento}">
					<tr>
						<td>${producto.descripcionProducto}</td>
						<td><fmt:formatNumber type="percent" maxFractionDigits="2"
								value="${producto.porcentajeVenta}" /></td>
						<td>${producto.stockActual}</td>
						<td>
							<button type="button" data-toggle="modal"
								data-id="${producto.idProducto}" data-target="#agregarDescuento"
								class="agregar-descuento btn btn-primary">agregar</button>

							<div class="modal fade" id="agregarDescuento" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalCenterTitle"
								aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalCenterTitle">Agregar
												Descuento</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<form method="post"
												action="<c:url value='/analisis/agregar-descuento'/>">
												<div class="form-group">
													<label for="idProducto">Id Producto</label> <input
														type="text" class="form-control" id="idProductoAgregar"
														name="idProducto" readonly="readonly">
												</div>
												<div class="form-group">
													<label for="porcentajeDescuento">Descuento Aplicado</label> 
													<input type="number" class="form-control"
														id="porcentajeDescuento" name="porcentajeDescuento"
														placeholder="Descuento Aplicado">
												</div>
												<div class="form-group">
													<label for="cantidadDisponible">Stock</label> <input
														type="number" class="form-control" id="cantidadDisponible"
														name="cantidadDisponible" placeholder="Stock"
														required="required">
												</div>

												<div class="form-group">
													<label for="restriccionCantidad">Dias Vigencia</label> <input
														type="number" class="form-control" id="diasVigencia"
														name="diasVigencia" required="required"
														placeholder="Dias de Vigencia">
												</div>

												<div class="modal-footer">
													<button type="submit" class="btn btn-primary">Agregar</button>
												</div>

											</form>

										</div>

									</div>
								</div>
							</div>

						</td>

					</tr>
				</c:forEach>
			</table>

		</div>
	</c:if>

	<c:if test="${cproductosEnDescuento=='MODO_VACIO_PRODUCTOSDIARIO'}">
		<h1>No hay productos en Descuento Diario!</h1>
	</c:if>
	<c:if test="${cproductosEnDescuento=='MODO_LISTA'}">

		<div class="row">
			<table class="table table-bordered">
				<tr class="table-success">
					<th class="table-hover">Producto</th>
					<th>Porcentaje Descuento</th>
					<th>Cantidad</th>
					<th>Seleccione</th>
				</tr>
				<c:forEach var="producto" items="${productosDescuentoDiario}">
					<tr>
						<td>${producto.idProducto}</td>
						<td>${producto.pctDescuento}</td>
						<td>${producto.diasVigencia}</td>
						<td>
							<button type="button" data-toggle="modal"
								data-id="${producto.idProducto}"
								data-target="#eliminarDescuento"
								class="eliminar-descuento btn btn-primary">eliminar</button>

							<div class="modal fade" id="eliminarDescuento" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalCenterTitle"
								aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalCenterTitle">Eliminar
												Descuento</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<form method="get"
												action="<c:url value='/analisis/eliminar-descuento'/>">
												<div class="form-group">
													<input
														type="text" class="form-control" id="idProductoEliminar"
														name="idProducto" readonly="readonly"
														style="visibility: hidden;">

													<p>¿Está seguro de retirar el producto seleccionado de
														la lista de descuentos diarios?</p>
												</div>

												<div class="modal-footer">
													<button type="submit" class="btn btn-primary">Eliminar</button>
												</div>

											</form>

										</div>

									</div>
								</div>
							</div>

						</td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</c:if>
</div>
<script>
	$(document).on("click", ".agregar-descuento", function() {
		var idProducto = $(this).data('id');
		$(".modal-body #idProductoAgregar").val(idProducto);
	});

	$(document).on("click", ".eliminar-descuento", function() {
		var idProducto = $(this).data('id');
		$(".modal-body #idProductoEliminar").val(idProducto);
	});
</script>