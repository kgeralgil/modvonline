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
import org.tottus.ventaonline.model.ProductoDescuento;
import org.tottus.ventaonline.model.ProductosParaDescuentoDiario;

@Repository
public class AnalisisRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// Consultar Productos en Descuento Diario actual
	public List<ProductoDescuento> ConsultarProductosEnDescuento(int estado) {

		String sql = "select idProducto,cantDisponible,tipoDescuento,pctDescuento,diasVigencia"
				+ " from productodescuento where estado like ?";

		RowMapper<ProductoDescuento> mapper = new RowMapper<ProductoDescuento>() {
			public ProductoDescuento mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductoDescuento prDescuento = new ProductoDescuento();
				prDescuento.setIdProducto(rs.getInt("idProducto"));
				prDescuento.setPctDescuento(rs.getDouble("pctDescuento"));
				prDescuento.setDiasVigencia(rs.getInt("diasVigencia"));
				return prDescuento;
			}
		};

		return jdbcTemplate.query(sql, new Object[] { estado }, mapper);

	}

	// Consultar Productos en Descuento Diario actual
	public List<ProductosParaDescuentoDiario> ConsultarProductosParaDescuentoDiario(Date fechaIni, Date fechaFin) {

		String sql = "SELECT sp.idProducto,pr.descripcion as 'descripcionProducto', sum(vd.cantidad) as VentaRealizada,"
				+ "( sp.stock * DATEDIFF(now(),sp.fechaCreacion))/sp.vigenciaStockSolicitado as 'Venta Esperada',"
				+ "(sp.stock-sum(vd.cantidad)) as 'stockActual',"
				+ " sum(vd.cantidad) /(( sp.stock * DATEDIFF(now(),sp.fechaCreacion))/sp.vigenciaStockSolicitado) as 'porcentajeVenta' "
				+ " FROM tottus.stockproducto sp join ventadetalle vd on sp.idProducto=vd.idProducto"
				+ " join venta v on vd.idVenta=v.idVenta" + " join producto pr on sp.idProducto=pr.idProducto where "
				+ " sp.fechaCreacion between ? and ?";

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

	public void agregarDescuentoDiario(ProductoDescuento productoDescuento) {
		Date date = new Date();
		jdbcTemplate.update(
				"INSERT INTO productodescuento (idProducto, cantDisponible,pctDescuento,diasVigencia,tipoDescuento,estado,fechacreacion) VALUES (?, ?, ?, ?, ?, ?,?)",
				productoDescuento.getIdProducto(), productoDescuento.getCantDisponible(),
				productoDescuento.getPctDescuento(), productoDescuento.getDiasVigencia(),"D",1,date);
		
	}
	
	public void eliminarDescuentoDiario(int idProducto) {

		jdbcTemplate.update("DELETE from productodescuento where tipoDescuento = 'D' and idProducto = ? ",
				idProducto);
		
	}

}
