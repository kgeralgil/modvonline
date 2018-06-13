package org.tottus.ventaonline.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.tottus.ventaonline.model.ProductoDescuentoDiario;
import org.tottus.ventaonline.model.ProductosParaDescuentoDiario;

@Repository
public class AnalisisRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// Consultar Productos en Descuento Diario actual
	public List<ProductoDescuentoDiario> ConsultarProductosEnDescuento(int estado) {

		String sql = "select idProducto,cantidadDisponible,tipoDescuento,porcentajeDescuento,restriccionCantidad"
				+ " from productodescuentodiario where estado like ?";

		RowMapper<ProductoDescuentoDiario> mapper = new RowMapper<ProductoDescuentoDiario>() {
			public ProductoDescuentoDiario mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductoDescuentoDiario prDescuento = new ProductoDescuentoDiario();
				prDescuento.setIdProducto(rs.getInt("idProducto"));
				prDescuento.setPorcentajeDescuento(rs.getDouble("porcentajeDescuento"));
				prDescuento.setRestriccionCantidad(rs.getInt("restriccionCantidad"));
				return prDescuento;
			}
		};

		return jdbcTemplate.query(sql, new Object[] { estado }, mapper);

	}

	// Consultar Productos en Descuento Diario actual
	public List<ProductosParaDescuentoDiario> ConsultarProductosParaDescuentoDiario(Date fechaIni, Date fechaFin) {

		String sql = "SELECT sp.idProducto,pr.descripcion as 'descripcionProducto', sum(vd.cantidad) as VentaRealizada,"
				+ "( sp.stock * DATEDIFF(now(),sp.fechaIngresoStock))/sp.vigenciaStockSolicitado as 'Venta Esperada',"
				+ "(sp.stock-sum(vd.cantidad)) as 'stockActual',"
				+ " sum(vd.cantidad) /(( sp.stock * DATEDIFF(now(),sp.fechaIngresoStock))/sp.vigenciaStockSolicitado) as 'porcentajeVenta' "
				+ " FROM tottus.stockproducto sp join ventadetalle vd on sp.idProducto=vd.idProducto"
				+ " join venta v on vd.idVenta=v.idVenta" + " join producto pr on sp.idProducto=pr.idProducto where "
				+ " sp.fechaIngresoStock between ? and ?";

		RowMapper<ProductosParaDescuentoDiario> mapper = new RowMapper<ProductosParaDescuentoDiario>() {
			public ProductosParaDescuentoDiario mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductosParaDescuentoDiario prDescuento = new ProductosParaDescuentoDiario();
				prDescuento.setIdProducto(rs.getInt("idProducto"));
				prDescuento.setDescripcionProducto(rs.getString("descripcionProducto"));
				prDescuento.setPorcentajeVenta(rs.getDouble("porcentajeVenta"));
				prDescuento.setStockActual(rs.getInt("stockActual"));

				return prDescuento;
			}
		};

		return jdbcTemplate.query(sql, new Object[] { fechaIni, fechaFin }, mapper);

	}

	public void agregarDescuentoDiario(ProductoDescuentoDiario productoDescuentoDiario) {

		jdbcTemplate.update(
				"INSERT INTO productodescuentodiario (idProducto, cantidadDisponible,porcentajeDescuento,restriccionCantidad,fechaIniVigencia,fechaFinVigencia,estado) VALUES (?, ?, ?, ?,?,?,?)",
				productoDescuentoDiario.getIdProducto(), productoDescuentoDiario.getCantidadDisponible(),
				productoDescuentoDiario.getPorcentajeDescuento(), productoDescuentoDiario.getRestriccionCantidad(),
				productoDescuentoDiario.getFechaIniVigencia(),productoDescuentoDiario.getFechaFinVigencia(),1);
		
	}

}
