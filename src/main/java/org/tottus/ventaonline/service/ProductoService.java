package org.tottus.ventaonline.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tottus.ventaonline.dao.ProductoRepository;
import org.tottus.ventaonline.model.Producto;

@Service
@Transactional
public class ProductoService {

	@Autowired
	ProductoRepository productoRepository;

	public List<Producto> filtrarProductosXNombre(String codigoProducto) {

		return productoRepository.filtrarProductosXNombre(codigoProducto);
	}

	public Producto buscarProdcuctoXId(int idProducto) {

		return productoRepository.buscarProductoXId(idProducto);
	}

	public List<Producto> buscarProductosRecomendadosXIdProducto(int idProducto) {

		return productoRepository.buscarProductosRecomendadosXIdProducto(idProducto);
	}

}
