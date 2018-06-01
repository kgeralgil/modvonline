package org.tottus.ventaonline.model.sorter;

import java.util.Comparator;
import java.util.Date;

import org.tottus.ventaonline.model.Producto;

public class ProdFVencimientoSorterStrategy implements Comparator<Producto> {

	@Override
	public int compare(Producto o1, Producto o2) {
		Date producto1 = o1.getFechaVencimiento();
		Date producto2 = o2.getFechaVencimiento();
		return producto1.before(producto2)?-1:1;
	}

}
