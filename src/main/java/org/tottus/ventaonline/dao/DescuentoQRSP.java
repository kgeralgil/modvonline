package org.tottus.ventaonline.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;

public class DescuentoQRSP extends StoredProcedure {

	/**
	 * Constructor for this StoredProcedure class.
	 */
	 @SuppressWarnings("rawtypes")
	 public DescuentoQRSP(JdbcTemplate jdbcTemplate) {
		 super(jdbcTemplate, "OBTENER_DESCUENTO_QR");
		 RowMapper rowMapper = new DescuentoRowMapper();
		 declareParameter(new SqlReturnResultSet("descuento", rowMapper));
		 declareParameter(new SqlParameter("codDescuento", Types.VARCHAR));
		 declareParameter(new SqlParameter("idDispositivo", Types.VARCHAR));
		 declareParameter(new SqlOutParameter("codResultado", Types.INTEGER));
		 declareParameter(new SqlOutParameter("msgResultado", Types.VARCHAR));
		 compile();
	 }

	 /**
	 * Execute stored procedure.
	 * @return Results of running stored procedure.
	 */
	 public Map<String, Object> generateQRDiscount(String codDiscount, String deviceId) {
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 parameters.put("codDescuento", codDiscount);
		 parameters.put("idDispositivo", deviceId);
		 Map<String, Object> out = execute(parameters);
		 return out;
	}
	
}
