package pe.edu.upc.tottus.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import pe.edu.upc.tottus.network.ApiService;
import pe.edu.upc.tottus.utils.DeviceUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {

    public static final int REQUEST_CODE = 100;

    FloatingActionButton fab;
    TextView txtSku;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        fab = (FloatingActionButton) view.findViewById(R.id.fabScan);
        txtSku = (TextView) view.findViewById(R.id.product_name);

        fab.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        startActivityForResult(new Intent(getContext(), ScanActivity.class), REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == CommonStatusCodes.SUCCESS) {
            String value = data.getStringExtra("BARCODE_VALUE");
            txtSku.setText(value);
            getProduct(value);
        }
    }

    private void getProduct(String sku) {
        String deviceId = DeviceUtil.getAndroidId(getContext());
        AndroidNetworking.get(ApiService.PRODUCT_URL + sku + "/movil/" +  deviceId)
                .addQueryParameter("language", "en")
                .setPriority(Priority.LOW)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (100 == Integer.parseInt(response.getString("coed"))) {
                                Log.e(getString(R.string.app_name), response.getString("message"));
                                return;
                            }

                            /*sourceList = Source.from(response.getJSONArray("data"));
                            sourcesAdapter.setSourceList(sourceList);
                            sourcesAdapter.notifyDataSetChanged();*/
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
