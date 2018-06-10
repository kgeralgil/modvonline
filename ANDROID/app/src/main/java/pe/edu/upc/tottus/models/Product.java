package pe.edu.upc.tottus.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Product {

    private String id;
    private String code;
    private String name;
    private String description;
    private String url;
    private String category;
    private String price;
    private String country;
    private String dueDate;
    private String discountType;
    private String quantityRestriction;
    private String discount;
    private String unitPriceDis;

    private List<String> sortByAvailable;

    public String getDueDate() {
        return dueDate;
    }

    public Product setDueDate(String dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public String getDiscountType() {
        return discountType;
    }

    public Product setDiscountType(String discountType) {
        this.discountType = discountType;
        return this;
    }

    public String getQuantityRestriction() {
        return quantityRestriction;
    }

    public Product setQuantityRestriction(String quantityRestriction) {
        this.quantityRestriction = quantityRestriction;
        return this;
    }

    public String getDiscount() {
        return discount;
    }

    public Product setDiscount(String discount) {
        this.discount = discount;
        return this;
    }

    public String getUnitPriceDis() {
        return unitPriceDis;
    }

    public Product setUnitPriceDis(String unitPriceDis) {
        this.unitPriceDis = unitPriceDis;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Product setCode(String code) {
        this.code = code;
        return this;
    }

    public String getId() {
        return id;
    }

    public Product setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Product setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Product setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public Product setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Product setCountry(String country) {
        this.country = country;
        return this;
    }

    public List<String> getSortByAvailable() {
        return sortByAvailable;
    }

    public Product setSortByAvailable(List<String> sortByAvailable) {
        this.sortByAvailable = sortByAvailable;
        return this;
    }

    public Product(String id, String name, String description, String url, String category, String price, String country, List<String> sortByAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.price = price;
        this.country = country;
        this.sortByAvailable = sortByAvailable;
    }

    public Product() {
    }

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("name", name);
        bundle.putString("description", description);
        bundle.putString("url", url);
        bundle.putString("category", category);
        bundle.putString("price", price);
        bundle.putString("country", country);
        bundle.putStringArrayList("sortBysAvailable", (ArrayList<String>) sortByAvailable);
        return bundle;
    }

    public static Product from(Bundle bundle){
        Product product = new Product();
        product.setId(bundle.getString("id"))
                .setName(bundle.getString("name"))
                .setDescription(bundle.getString("desription"))
                .setUrl(bundle.getString("url"))
                .setCategory(bundle.getString("category"))
                .setPrice(bundle.getString("price"))
                .setCountry(bundle.getString("country"))
                .setSortByAvailable(bundle.getStringArrayList("sortBysAvailable"));
        return product;
    }

    public static Product from(JSONObject jsonSource){
        Product product = new Product();
        try {
            List<String> sortByAvailable = new ArrayList<>();
            JSONArray jsonArray = jsonSource.getJSONArray("sortBysAvailable");
            for(int i = 0; i < jsonArray.length(); i++)
                sortByAvailable.add(jsonArray.getString(i));

            product.setId(jsonSource.getString("idProducto"))
                    .setCode(jsonSource.getString("codigoProducto"))
                    .setName(jsonSource.getString("name"))
                    .setDescription(jsonSource.getString("descripcion"))
                    .setUrl(jsonSource.getString("imagen"))
                    .setCategory(jsonSource.getString("category"))
                    .setPrice(jsonSource.getString("precioUnitario"))
                    .setCountry(jsonSource.getString("country"))
                    .setDueDate(jsonSource.getString("fechaVencimiento"))
                    .setDiscountType(jsonSource.getString("tipoDescuento"))
                    .setQuantityRestriction(jsonSource.getString("restriccionCantidad"))
                    .setDiscount(jsonSource.getString("porcentajeDescuento"))
                    .setUnitPriceDis(jsonSource.getString("precioUnitarioDescuento"))
                    .setSortByAvailable(sortByAvailable) ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static List<Product> from (JSONArray jsonSources){
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < jsonSources.length(); i++)
            try {
                products.add(Product.from(jsonSources.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return products;
    }
}
