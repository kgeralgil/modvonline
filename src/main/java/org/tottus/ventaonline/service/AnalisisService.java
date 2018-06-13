package org.tottus.ventaonline.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tottus.ventaonline.dao.AnalisisRepository;
import org.tottus.ventaonline.model.ProductoDescuentoDiario;
import org.tottus.ventaonline.model.ProductosParaDescuentoDiario;

@Service
@Transactional
public class AnalisisService {

	@Autowired
	AnalisisRepository analisisRepository;
	
	public List<ProductoDescuentoDiario> ConsultarProductosEnDescuento(int estado) {
		return analisisRepository.ConsultarProductosEnDescuento(estado);
	}
	
	public List<ProductosParaDescuentoDiario> ConsultarProductosParaDescuentoDiario(Date fechaIni, Date fechaFin) {
		return analisisRepository.ConsultarProductosParaDescuentoDiario(fechaIni, fechaFin);
	}

	public void agregarDescuentoDiario(ProductoDescuentoDiario productoDescuentoDiario) {
		
		analisisRepository.agregarDescuentoDiario(productoDescuentoDiario);
		
	}

}
