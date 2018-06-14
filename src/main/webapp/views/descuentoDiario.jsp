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
		<c:forEach var="descuento" items="${descuentos}">
			<div class="card-deck mb-4 text-center">
				<div class="col-4 col-md">
					<div class="card mb-4 box-shadow">
						<div class="card-header">
							<img src="data:image/jpeg;base64,${descuento.imagen}" 
								class="img-thumbnail" height="139" width="139" />
							<h4 class="my-0 font-weight-normal">${descuento.descripcion}</h4>
						</div>
						<div class="card-body">
							<h1 class="card-title pricing-card-title">
								<fmt:formatNumber type="number" minFractionDigits="2"
 									maxFractionDigits="2" value="${descuento.porcentajeDescuento}" /> %
 									de descuento
							</h1>
							<h3>V&aacute;lido hasta:
								<fmt:formatDate pattern = "dd/MM/yyyy" value="${descuento.fechaCaducidad}" /> 
							</h3>
							<h3>C&oacute;digo de descuento: <span>${descuento.codDescuento}</span></h3>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>