package pe.edu.upc.tottus.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.common.api.CommonStatusCodes;

import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.tottus.R;
import pe.edu.upc.tottus.activities.ScanActivity;
import pe.edu.upc.tottus.models.Product;
import pe.edu.upc.tottus.network.ApiService;
import pe.edu.upc.tottus.utils.DeviceUtil;
import pe.edu.upc.tottus.utils.ImageUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {

    public static final int REQUEST_CODE = 100;

    FloatingActionButton fab;
    TextView productQuantity;
    TextView productPrice;
    TextView productDiscount;
    TextView productValidDate;
    TextView productName;
    ImageView productImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        fab = (FloatingActionButton) view.findViewById(R.id.fabScan);
        productName = (TextView) view.findViewById(R.id.product_name);
        productQuantity = (TextView) view.findViewById(R.id.product_quantity);
        productPrice = (TextView) view.findViewById(R.id.product_price);
        productDiscount = (TextView) view.findViewById(R.id.product_discount);
        productValidDate = (TextView) view.findViewById(R.id.product_valid_date);
        productImage = (ImageView) view.findViewById(R.id.product_image);

        fab.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
      // startActivityForResult(new Intent(getContext(), ScanActivity.class), REQUEST_CODE);
        getProduct("DESC001");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == CommonStatusCodes.SUCCESS) {
            String value = data.getStringExtra("BARCODE_VALUE");
            getProduct(value);
        }
    }

    private void getProduct(String code) {
        String deviceId = DeviceUtil.getAndroidId(getContext());
        AndroidNetworking.post(ApiService.PRODUCT_URL + code)
                .addBodyParameter("idmovil", deviceId)
                .setPriority(Priority.LOW)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (1 != Integer.parseInt(response.getString("code"))) {
                                AlertDialog.Builder builder;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                                } else {
                                    builder = new AlertDialog.Builder(getContext());
                                }
                                builder.setTitle("Error")
                                        .setMessage(response.getString("message"))
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                                return;
                            }

                            Product product = Product.from(response.getJSONObject("data"));

                            if (product != null){
                                productName.setText(product.getDescription() + " - " + product.getName());
                                productQuantity.setText("Cantidad máxima: " + product.getQuantityRestriction());
                                productPrice.setText("Precio normal: " + product.getPrice() + " (-" + product.getDiscount()+"%)");
                                productDiscount.setText("Ahora: " + product.getUnitPriceDis());
                                productValidDate.setText("Promoción válida hasta: " + product.getValidDays());

                                Bitmap bm = ImageUtil.decodeBase64(ApiService.IMAGE);
                                productImage.setImageBitmap(bm);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(getString(R.string.app_name), anError.getLocalizedMessage());
                    }
                });
    }
}
