package org.tottus.ventaonline.dao;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

		String sql = "select * from producto where codigoProducto like ?";

		RowMapper<Producto> mapper = new RowMapper<Producto>() {
			public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
				Producto producto = new Producto();
				producto.setIdProducto(rs.getInt("idProducto"));
				producto.setCodigoProducto(rs.getString("codigoProducto"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecioUnitario(rs.getDouble("precioUnitario"));

				byte[] bytes = rs.getBytes("imagen");
				byte[] encodeBase64 = Base64.encodeBase64(bytes);
				String base64Encoded;
				try {
					base64Encoded = new String(encodeBase64, "UTF-8");
					producto.setImagen(base64Encoded);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				return producto;
			}
		};

		return jdbcTemplate.query(sql, new Object[] { '%' + codigoProducto + '%' }, mapper);

	}

	public Producto buscarProductoXId(int idProducto) {

		String sql = "select * from producto where idProducto = ?";

		RowMapper<Producto> mapper = new RowMapper<Producto>() {
			public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
				Producto producto = new Producto();
				producto.setIdProducto(rs.getInt("idProducto"));
				producto.setCodigoProducto(rs.getString("codigoProducto"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecioUnitario(rs.getDouble("precioUnitario"));

				byte[] bytes = rs.getBytes("imagen");
				byte[] encodeBase64 = Base64.encodeBase64(bytes);
				String base64Encoded;
				try {
					base64Encoded = new String(encodeBase64, "UTF-8");
					producto.setImagen(base64Encoded);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				return producto;
			}
		};

		return jdbcTemplate.queryForObject(sql, new Object[] { idProducto }, mapper);

	}
	
	public Producto buscarProductoXEtiqueta(String codigoProducto) {
		
		try{
			String sql = "select * from producto where codigoProducto like ?";
			
			Producto producto = jdbcTemplate.queryForObject(sql,new RowMapper<Producto>(){

				@Override
				public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					Producto producto = new Producto();
					producto.setIdProducto(rs.getInt("idProducto"));
					producto.setCodigoProducto(rs.getString("codigoProducto"));
					producto.setDescripcion(rs.getString("descripcion"));
					producto.setPrecioUnitario(rs.getDouble("precioUnitario"));
					producto.setImagen(rs.getString("imagen"));
					
					return producto;
				}
				
			},codigoProducto.trim());
			
			return producto;
			
		}catch (Exception e){
            System.out.println("Error en la conexión/sentencia/sintaxis Base de Datos:" + e);
            throw e;
        }
		
	}
	
	public List<Producto> buscarProductosRecomendadosXIdProducto(int idProducto) {
		String sql = "SELECT  pr.idProducto,pr.codigoProducto, pr.descripcion,"
				+ "pr.precioUnitario,pr.imagen,pr.porctMinVenta,pr.fechaVencimiento "
				+ " FROM productopalabraclave pcc join producto pr on pcc.idProducto=pr.idProducto "
				+ " where pcc.palabraClave = ( SELECT pc.palabraClave FROM productopalabraclave pc "
				+ " join producto p on p.idProducto=pc.idProducto where p.idProducto=?) and pr.idProducto != ? ";

		RowMapper<Producto> mapper = new RowMapper<Producto>() {
			public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
				Producto producto = new Producto();
				producto.setIdProducto(rs.getInt("pr.idProducto"));
				producto.setCodigoProducto(rs.getString("pr.codigoProducto"));
				producto.setDescripcion(rs.getString("pr.descripcion"));
				producto.setPrecioUnitario(rs.getDouble("pr.precioUnitario"));
				producto.setFechaVencimiento(rs.getDate("pr.fechaVencimiento"));

				byte[] bytes = rs.getBytes("pr.imagen");
				byte[] encodeBase64 = Base64.encodeBase64(bytes);
				String base64Encoded;
				try {
					base64Encoded = new String(encodeBase64, "UTF-8");
					producto.setImagen(base64Encoded);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				return producto;
			}
		};

		return jdbcTemplate.query(sql, new Object[] { idProducto,idProducto }, mapper);
	}

}
