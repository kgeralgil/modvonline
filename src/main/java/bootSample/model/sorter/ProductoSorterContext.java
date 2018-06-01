package bootSample.model.sorter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bootSample.model.Producto;

public class ProductoSorterContext {

	private Comparator<Producto> sortStrategy;
	
	public void setSortStrategy(Comparator<Producto> sortStrategy) {
        this.sortStrategy = sortStrategy;
	}

	public void sortProducto(List<Producto> productos){
		 Collections.sort(productos, sortStrategy);
	}
	 
}
