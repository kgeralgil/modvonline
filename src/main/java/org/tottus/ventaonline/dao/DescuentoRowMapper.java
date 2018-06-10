package org.tottus.ventaonline.dao;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.jdbc.core.RowMapper;
import org.tottus.ventaonline.model.Descuento;

public class DescuentoRowMapper implements RowMapper<Descuento> {

	/**
	 * Maps the result set rows to a Claim object
	 */
	public Descuento mapRow(ResultSet rs, int index) throws SQLException {
		Descuento descuento = new Descuento();
		descuento.setIdProducto(rs.getInt("idProducto"));
		descuento.setDescripcion(rs.getString("descripcion"));
		descuento.setMarca(rs.getString("marca"));
		descuento.setPrecioUnitario(rs.getDouble("precioUnitario"));
		descuento.setPorcentajeDescuento(rs.getDouble("pctDescuento"));
		descuento.setRestriccionCantidad(rs.getInt("unidadesProdDescuento"));
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
		descuento.setCodDescuento(rs.getString("codDescuento"));
		descuento.setPrecioDescuento(rs.getDouble("precioDescuento"));
		descuento.setDiasVigencia(rs.getInt("diasVigencia"));
		return descuento;
	}
	
}
