package org.tottus.ventaonline.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.tottus.ventaonline.model.Descuento;

@Repository
public class DescuentoRepository {

	private DescuentoSP descuentoSP;
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void postConstruct() {
		descuentoSP = new DescuentoSP(jdbcTemplate);
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public synchronized Integer validarDescuentoDNI(String dni) {
		String sql = 
				"select count(1) as descuentosGenerados from productodescuentodiariocliente pddc "
				+ "inner join descuentodiariocliente ddc on "
				+ "pddc.idDescuentoDiarioCliente = ddc.idDescuentoDiarioCliente " 
				+ "where ddc.dni = ? and date(ddc.fecha) = date(now())";
		return jdbcTemplate.queryForObject(sql, new Object[] { dni }, Integer.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Descuento> generarDescuentosDiarios(String dni) {
		List<Descuento> result = null;
		Map<String, Object> data = descuentoSP.generarDescuentosDiarios(dni);
		result = (List<Descuento>) data.get("result_list");
		return result;
	}
	
}
