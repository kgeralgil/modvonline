<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<form method="post" action="<c:url value='/analisis/consultar' />">
<h5>Estadística de Ventas Online</h5>
	<div class="d-flex justify-content-between
		flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<div class="col-sm-4" style="margin: 15px;">
			<label for="fechaIni">Fecha Inicio: </label><br/>
			<input name="fechaIni" type="date" class="form-control"
				pattern="dd/MM/yyyy" placeholder="dd/mm/yyyy" />
		</div>
		<div class="col-sm-4" style="margin: 15px;">
			<label for="fechaFin">Fecha Fin: </label><br/>
			<input name="fechaFin" type="date" class="form-control"
				pattern="dd/MM/yyyy" placeholder="dd/mm/yyyy" />
		</div>
		<div class="col-sm-2" style="margin: 15px;">
			<input class="btn btn-outline-secondary" type="submit" value="Ver Detalle" />
		</div>
	</div>
</form>

<div class="container">
	<c:if test="${CproductosParaDescuento=='MODO_VACIO'}">
		<div class="row">
			<b>Productos con nivel bajo en ventas</b>
			<table class="table table-bordered" title="Productos con nivel bajo en ventas">
				<tr class="table-success">
					<th class="table-hover">&nbsp;</th>
				</tr>
				<tr>
					<td>No hay productos con niveles de venta inferiores al esperado</td>
				</tr>
			</table>
		</div>
	</c:if>
	<c:if test="${CproductosParaDescuento=='MODO_CONSULTA'}">
		<div class="row">
			<b>Productos con nivel bajo en ventas</b>
			<table class="table table-bordered"
				title="Productos con nivel bajo en ventas">
				<tr class="table-success">
					<th class="table-hover">Producto</th>
					<th>Porcentaje de Venta</th>
					<th>Stock Disponible</th>
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
								class="agregar-descuento btn btn-primary">Agregar</button>

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
											<form method="get" id="formAddProd"
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
													<label for="cantidadDisponible">Stock Disponible</label> <input
														type="number" class="form-control" id="cantidadDisponible"
														name="cantidadDisponible" placeholder="Stock" min="1"
														required="required">
												</div>

												<div class="form-group">
													<label for="restriccionCantidad">Dias Vigencia</label> <input
														type="number" class="form-control" id="diasVigencia"
														name="diasVigencia" required="required"
														placeholder="Dias de Vigencia">
												</div>

												<div class="modal-footer">
													<button type="button" id="closeModal" class="btn btn-primary">Cancelar</button>
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
			<b>Descuentos Diarios</b>
			<table class="table table-bordered" title="Descuentos Diarios">
				<tr class="table-success">
					<th class="table-hover">Producto</th>
					<th>Fecha Creación</th>
					<th>Porcentaje Descuento</th>
					<th>Stock Disponible</th>
					<th>Dias en Vigencia</th>
					<th>Dias en Descuento</th>
					<th>Seleccione</th>
				</tr>
				<c:forEach var="producto" items="${productosDescuentoDiario}">
					<tr>
						<td>${producto.descProducto}</td>
						<td>${producto.fechaCreacion}</td>
						<td><fmt:formatNumber type="percent" maxFractionDigits="2" pattern="###.##"
								value="${producto.pctDescuento}" />%</td>
						<td>${producto.cantDisponible}</td>
						<td>${producto.diasVigencia}</td>
						<td>${producto.diasEnDescuento}</td>
						<td>
							<button type="button" data-toggle="modal"
								data-id="${producto.idProducto}"
								data-target="#eliminarDescuento"
								class="eliminar-descuento btn btn-primary">Retirar</button>

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
													<input type="text" class="form-control"
														id="idProductoEliminar" name="idProducto"
														readonly="readonly" style="visibility: hidden;">
													<p>¿Está seguro de retirar el producto seleccionado de
														la lista de descuentos diarios?</p>
												</div>

												<div class="modal-footer">
													<button type="reset" class="btn btn-primary"
														data-dismiss="modal" aria-label="Close">
														Cancelar <span aria-hidden="true">&times;</span>
													</button>
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
	$(function(){
		$("#closeModal").click(function(){
			$("#formAddProd")[0].reset();
			$("#agregarDescuento").modal("hide");
		});
	});
</script>