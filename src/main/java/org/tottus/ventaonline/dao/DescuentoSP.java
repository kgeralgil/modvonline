package org.tottus.ventaonline.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;

public class DescuentoSP extends StoredProcedure {

	/**
	 * Constructor for this StoredProcedure class.
	 */
	 @SuppressWarnings("rawtypes")
	 public DescuentoSP(JdbcTemplate jdbcTemplate) {
		 super(jdbcTemplate, "GENERAR_DESCUENTOS_DIARIOS");
		 RowMapper rowMapper = new DescuentoRowMapper();
		 declareParameter(new SqlReturnResultSet("result_list", rowMapper));
		 declareParameter(new SqlParameter("dniCliente", Types.VARCHAR));
		 compile();
	 }

	 /**
	 * Execute stored procedure.
	 * @return Results of running stored procedure.
	 */
	 public Map<String, Object> generarDescuentosDiarios(String dni) {
		 Map<String, Object> inParameters = new HashMap<String, Object>();
		 inParameters.put("dniCliente", dni);
		 Map<String, Object> out = execute(inParameters);
		 return out;
	}
	
}
