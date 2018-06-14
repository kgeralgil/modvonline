package pe.edu.upc.tottus.fragments;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import pe.edu.upc.tottus.R;
import pe.edu.upc.tottus.network.ApiService;
import pe.edu.upc.tottus.utils.DeviceUtil;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {


    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        Button speech = (Button) view.findViewById(R.id.speech);
        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                        "Habla");

                try {
                    startActivityForResult(intent, 1);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getContext(), "Opps! Your device doesn’t support Speech to Text", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Toast.makeText(getContext(), result.get(0), Toast.LENGTH_SHORT).show();
                }
                break;
            }

        }
    }

    private void showAlert(String message) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void getProduct(ArrayList<String> result) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("textPhrase", result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(ApiService.VOICE_URL)
                .addJSONObjectBody(jsonObject)
                .addHeaders("Content-Type", "application/json")
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (1 != Integer.parseInt(response.getString("code"))) {
                                showAlert(response.getString("message"));
                                return;
                            }

                            if (response.isNull("data")) {
                                showAlert("No se encontraron datos");
                                return;
                            }

                            /*Product product = Product.from(response.getJSONObject("data"));

                            if (product != null){
                                productName.setText(product.getDescription() + " - " + product.getName());
                                productQuantity.setText("Cantidad máxima: " + product.getQuantityRestriction());
                                productPrice.setText("Precio normal: " + product.getPrice() + " (-" + product.getDiscount()+"%)");
                                productDiscount.setText("Ahora: " + product.getUnitPriceDis());

                                Calendar cal = Calendar.getInstance();
                                cal.setTimeInMillis(Long.parseLong(product.getDueDate()));
                                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

                                productValidDate.setText("Promoción válida hasta: " + format1.format(cal.getTime()));

                                Bitmap bm = ImageUtil.decodeBase64(product.getImage());
                                productImage.setImageBitmap(bm);
                            }*/

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
