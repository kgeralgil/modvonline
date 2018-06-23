package org.tottus.ventaonline.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tottus.ventaonline.dao.DescuentoRepository;
import org.tottus.ventaonline.dao.ProductoRepository;
import org.tottus.ventaonline.model.Producto;

@Service
@Transactional
public class MobileService {

	@Autowired
	private DescuentoRepository descuentoRepository;

	@Autowired
	private ProductoRepository productoRepository;

	public Map<String, Object> searchQRDiscount(String codDescuento, String deviceId) {
		return descuentoRepository.obtainQRDiscount(codDescuento, deviceId);
	}

	public List<Producto> searchProductsByVoice(String... textPhrase){
		return productoRepository.searchProductsByVoice(textPhrase);
	}

}
