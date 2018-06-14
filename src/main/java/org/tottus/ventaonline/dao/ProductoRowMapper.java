package org.tottus.ventaonline.dao;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.jdbc.core.RowMapper;
import org.tottus.ventaonline.model.Producto;
import org.tottus.ventaonline.utils.Util;

public class ProductoRowMapper implements RowMapper<Producto> {

	@Override
	public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
		Producto producto = new Producto();
		producto.setIdProducto(rs.getInt("idProducto"));
		producto.setCodigoProducto(rs.getString("codigoProducto"));
		producto.setDescripcion(rs.getString("descripcion"));
		producto.setPrecioUnitario(rs.getDouble("precioUnitario"));
		producto.setMarca(rs.getString("marca"));
		producto.setFechaVencimiento(rs.getDate("fechaVencimiento"));
		try {
			byte[] bytes = null;
			if(null==rs.getBytes("imagen")){
				File imgPath = new File(getClass().getClassLoader()
						.getResource("default.jpg").getFile());
				bytes = Util.extractBytesFromFile(imgPath);
			} else {
				bytes = rs.getBytes("imagen");
			}
			byte[] encodeBase64 = Base64.encodeBase64(bytes);
			String base64Encoded = new String(encodeBase64, "UTF-8");
			producto.setImagen(base64Encoded);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return producto;
	}

}
