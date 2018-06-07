package org.tottus.ventaonline.dao;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.jdbc.core.RowMapper;
import org.tottus.ventaonline.model.Descuento;

public class DescuentoRowMapper implements RowMapper {

	/**
	 * Maps the result set rows to a Claim object
	 */
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		Descuento descuento = new Descuento();
		descuento.setIdProducto(rs.getInt(1));
		descuento.setDescripcion(rs.getString(2));
		descuento.setMarca(rs.getString(3));
		descuento.setPrecioUnitario(rs.getDouble(4));
		descuento.setPorcentajeDescuento(rs.getDouble(5));
		descuento.setRestriccionCantidad(rs.getInt(6));
		byte[] bytes = rs.getBytes("imagen");
		byte[] encodeBase64 = Base64.encodeBase64(bytes);
		String base64Encoded;
		try {
			base64Encoded = new String(encodeBase64, "UTF-8");
			descuento.setImagen(base64Encoded);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		descuento.calcularFechaCaducidad();
		descuento.setCodDescuento(rs.getString(8));
		return descuento;
	}
	
}
