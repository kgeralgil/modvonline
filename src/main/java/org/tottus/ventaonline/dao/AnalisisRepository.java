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

		String sql = "select pd.idProducto,p.descripcion,pd.cantDisponible,pd.pctDescuento,pd.diasVigencia,pd.fechaCreacion "
				+ " from productodescuento pd join producto p on pd.idProducto=p.idProducto where "
				+ " pd.tipoDescuento = 'D' and pd.estado = ?";

		RowMapper<ProductoDescuento> mapper = new RowMapper<ProductoDescuento>() {
			public ProductoDescuento mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductoDescuento prDescuento = new ProductoDescuento();
				prDescuento.setIdProducto(rs.getInt("idProducto"));
				prDescuento.setDescProducto(rs.getString("descripcion"));
				prDescuento.setCantDisponible(rs.getInt("cantDisponible"));
				prDescuento.setPctDescuento(rs.getDouble("pctDescuento"));
				prDescuento.setDiasVigencia(rs.getInt("diasVigencia"));
				prDescuento.setFechaCreacion(rs.getDate("fechaCreacion"));
				Date hoy = new Date();
				prDescuento.setDiasEnDescuento(
						(int) ((hoy.getTime() - prDescuento.getFechaCreacion().getTime()) / 86400000));
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
				+ " join venta v on vd.idVenta=v.idVenta" + " join producto pr on sp.idProducto=pr.idProducto";
		if(null != fechaIni && null != fechaFin){
			sql += " where sp.fechaCreacion between ? and ?";
		}

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
		if(null != fechaIni && null != fechaFin){
			return jdbcTemplate.query(sql, new Object[] { fechaIni, fechaFin }, mapper);
		} else {
			return jdbcTemplate.query(sql, mapper);
		}

	}

	public void agregarDescuentoDiario(ProductoDescuento productoDescuento) {
		Date date = new Date();
		jdbcTemplate.update(
				"INSERT INTO productodescuento (idProducto, cantDisponible,pctDescuento,diasVigencia,tipoDescuento,estado,fechacreacion,fuente) VALUES (?, ?, ?, ?, ?, ?,?,?)",
				productoDescuento.getIdProducto(), productoDescuento.getCantDisponible(),
				productoDescuento.getPctDescuento(), productoDescuento.getDiasVigencia(), "D", 1, date,"V");

	}

	public void eliminarDescuentoDiario(int idProducto) {

		// jdbcTemplate.update("DELETE from productodescuento where
		// tipoDescuento = 'D' and idProducto = ? ",idProducto);
		// Actualizar el estado a Inactivo
		jdbcTemplate.update("UPDATE productodescuento SET estado =0 where tipoDescuento = 'D' and idProducto = ? ",
				idProducto);

	}

	// Consultar Productos en Descuento Diario actual
	public int consultarProductosParaDescuentoDiario(int idProducto) {

		String sql = "SELECT count(*) as 'cantidad' FROM tottus.productodescuento where estado =1 and idProducto = ?";

		int count = jdbcTemplate.queryForObject(sql, new Object[] { idProducto }, Integer.class);

		return count;

	}

}
