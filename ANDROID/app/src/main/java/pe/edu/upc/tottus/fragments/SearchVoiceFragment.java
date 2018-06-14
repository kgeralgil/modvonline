package pe.edu.upc.tottus.fragments;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pe.edu.upc.tottus.R;
import pe.edu.upc.tottus.adapters.ProductAdapter;
import pe.edu.upc.tottus.models.Product;
import pe.edu.upc.tottus.network.ApiService;
import pe.edu.upc.tottus.utils.DeviceUtil;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchVoiceFragment extends Fragment {

    private RecyclerView recyclerProducts;
    private RecyclerView.LayoutManager layoutManagerProducts;
    private ProductAdapter productAdapter;
    private List<Product> productList = new ArrayList<Product>();
    private FloatingActionButton speech;

    public SearchVoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        speech = (FloatingActionButton) view.findViewById(R.id.speech);
        recyclerProducts = (RecyclerView) view.findViewById(R.id.recyclerProducts);

        layoutManagerProducts = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        productAdapter = new ProductAdapter(productList, getContext());
        recyclerProducts.setLayoutManager(layoutManagerProducts);
        recyclerProducts.setAdapter(productAdapter);

        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES");
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                        "¿Qué producto buscas?");

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
                    boolean maxWords = false;
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    for (String word : result){
                        String [] count = word.split(" ");

                        if (count.length > 5){
                            maxWords = true;
                        }
                    }

                    if (maxWords){
                        showAlert("La cantidad de palabras supera lo permitido");
                    }else{
                        getProduct(result);
                    }

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
        JSONArray jsonArray = new JSONArray();
        try {
            for (String word : result){
                jsonArray.put(word);
            }

            jsonObject.put("textPhrase", jsonArray);
            Log.d("TAG", jsonObject.toString());
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

                            productList = new ArrayList<Product>();

                            if (1 != Integer.parseInt(response.getString("code"))) {
                                productAdapter.setProductList(productList);
                                productAdapter.notifyDataSetChanged();
                                showAlert(response.getString("message"));
                                return;
                            }

                            if (response.isNull("productos")) {
                                productAdapter.setProductList(productList);
                                productAdapter.notifyDataSetChanged();
                                showAlert("No se encontraron datos");
                                return;
                            }

                            productList = Product.from(response.getJSONArray("productos"));
                            productAdapter.setProductList(productList);
                            productAdapter.notifyDataSetChanged();

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
