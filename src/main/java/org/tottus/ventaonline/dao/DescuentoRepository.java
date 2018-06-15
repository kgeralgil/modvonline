package org.tottus.ventaonline.dao;

import java.util.HashMap;
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
	private DescuentoQRSP descuentoQRSP;
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void postConstruct() {
		descuentoSP = new DescuentoSP(jdbcTemplate);
		descuentoQRSP = new DescuentoQRSP(jdbcTemplate);
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Integer validateDiscountAlreadyGenerated(String dni) {
		String sql = 
				"select count(1) as descuentosGenerados from productodescuentogenerado pdg "
				+ "inner join descuentogenerado dg on pdg.idDescuentoGenerado = dg.idDescuentoGenerado "
				+ "where dg.dni = ? and date(dg.fecha) = date(now())";
		return jdbcTemplate.queryForObject(sql, new Object[] { dni }, Integer.class);
	}
	
	public Integer validateExistingDNI(String dni){
		String sql = "select count(1) from persona where "
				+ "idTipoDocumentoIdentidad = 1 and numeroDocumentoIdentidad = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { dni }, Integer.class);
		
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<Descuento> generarDescuentosDiarios(String dni) {
		List<Descuento> result = null;
		Map<String, Object> data = descuentoSP.generarDescuentosDiarios(dni);
		result = (List<Descuento>) data.get("result_list");
		return result;
	}
	
	public synchronized Map<String, Object> obtainQRDiscount(String codDiscount, String deviceId){
		Map<String, Object> result = new HashMap<>();
		try {
			result = descuentoQRSP.generateQRDiscount(codDiscount, deviceId); 
		} catch (Exception e){
			result.put("codResultado", 0);
			result.put("msgResultado", "Error generico");
		}
		return result;
	}
	
}
