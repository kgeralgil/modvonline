package pe.edu.upc.tottus.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import pe.edu.upc.tottus.R;
import pe.edu.upc.tottus.models.Product;
import pe.edu.upc.tottus.utils.ImageUtil;

/**
 * Created by Fernando on 14/06/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private List<Product> productList;
    private Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.setProductList(productList);
        this.context = context;
    }

    public ProductAdapter() {
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductAdapter.ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_products_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        final Product product = productList.get(position);
        holder.productName.setText(product.getDescription() + " - " + product.getName());
        holder.productQuantity.setText("Código: " + product.getId());
        holder.productPrice.setText("Precio: S/." + product.getPrice() );
        holder.productValidDate.setText("Válido hasta: " + product.getDueDate());
        holder.productImage.setImageBitmap(product.getImage());
    }

    @Override
    public int getItemCount() {
        return getProductList().size();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public ProductAdapter setProductList(List<Product> productList) {
        this.productList = productList;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView productQuantity;
        TextView productPrice;
        TextView productDiscount;
        TextView productValidDate;
        TextView productName;
        ImageView productImage;

        public ViewHolder(View itemView) {
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.product_name);
            productQuantity = (TextView) itemView.findViewById(R.id.product_quantity);
            productPrice = (TextView) itemView.findViewById(R.id.product_price);
            productDiscount = (TextView) itemView.findViewById(R.id.product_discount);
            productValidDate = (TextView) itemView.findViewById(R.id.product_valid_date);
            productImage = (ImageView) itemView.findViewById(R.id.product_image);
        }
    }
}
