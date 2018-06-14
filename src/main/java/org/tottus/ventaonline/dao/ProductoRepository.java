package org.tottus.ventaonline.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.tottus.ventaonline.model.Producto;

@Repository
public class ProductoRepository {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Producto> filtrarProductosXNombre(String codigoProducto) {
		String sql = "select * from producto where descripcion like ?";
		return jdbcTemplate.query(sql, new Object[] { '%' + codigoProducto + '%' }, new ProductoRowMapper());

	}

	public Producto buscarProductoXId(int idProducto) {
		String sql = "select * from producto where idProducto = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { idProducto }, new ProductoRowMapper());

	}
	
	public List<Producto> buscarProductosRecomendadosXIdProducto(int idProducto) {
		String sql = "SELECT  pr.* "
				+ " FROM productopalabraclave pcc join producto pr on pcc.idProducto=pr.idProducto "
				+ " where pcc.palabraClave = ( SELECT pc.palabraClave FROM productopalabraclave pc "
				+ " join producto p on p.idProducto=pc.idProducto where p.idProducto=? and pc.estado=1) "
				+ "and pr.idProducto != ? ";
		return jdbcTemplate.query(sql, new Object[] { idProducto,idProducto }, new ProductoRowMapper());
	}

	public List<Producto> searchProductsByVoice(String...textPhrase) {
		String[] jdbcParams = new String[textPhrase.length];
		jdbcParams[0] =  "%" + textPhrase[0] + "%";
		String sql = "select * from producto where concat(descripcion,marca) like ? ";
		for(int i = 1; i<textPhrase.length; i++){
			sql += "or concat(descripcion,marca) like ? ";
			jdbcParams[i] =  "%" + textPhrase[i] + "%";
		}
		return jdbcTemplate.query(sql, jdbcParams, new ProductoRowMapper());

	}
	
}
