package org.tottus.ventaonline.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tottus.ventaonline.dao.DescuentoRepository;

@Service
@Transactional
public class MobileService {

	@Autowired
	private DescuentoRepository descuentoRepository;
	
	
	public Map<String, Object> searchQRDiscount(String codDescuento, String deviceId) {
		// Buscar producto con QR
		return descuentoRepository.obtainQRDiscount(codDescuento, deviceId);
	}
	
}
