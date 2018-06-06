<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<form method="post" action="<c:url value='/descuentos/generar' />">
	<label>Documento de Identidad</label>
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<div class="input-group mb-3">
			<input name="dni" type="text" class="form-control"
				placeholder="Escribe aqui tu DNI" />
			<div class="input-group-append">
				<input class="btn btn-outline-secondary" type="submit" value="Generar" />
			</div>
		</div>
	</div>
</form>

<div class="container">
	<div class="row">
<%-- 		<c:forEach var="producto" items="${productos}"> --%>
			<div class="card-deck mb-4 text-center">
				<div class="col-4 col-md">
					<div class="card mb-4 box-shadow">
						<div class="card-header">
<%-- 							<img src="data:image/jpeg;base64,${producto.imagen}" class="img-thumbnail" /> --%>
<%-- 							<h4 class="my-0 font-weight-normal">${producto.codigoProducto}</h4> --%>
							<h4 class="my-0 font-weight-normal">TEST</h4>
						</div>
						<div class="card-body">
							<h1 class="card-title pricing-card-title">
								S/.
<%-- 								<fmt:formatNumber type="number" minFractionDigits="2" --%>
<%-- 									maxFractionDigits="2" value="${producto.precioUnitario}" /> --%>
								<fmt:formatNumber type="number" minFractionDigits="2"
									maxFractionDigits="2" value="20" />
							</h1>
<%-- 							<p class="card-text">${producto.descripcion}</p> --%>
								<p class="card-text">PRODUCTO TEST</p>
						</div>
					</div>
				</div>
			</div>
<%-- 		</c:forEach> --%>
	</div>
</div>