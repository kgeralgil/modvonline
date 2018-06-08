package pe.edu.upc.tottus;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

/**
 * Created by Fernando on 07/06/2018.
 */

public class TottusApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidNetworking.initialize(getApplicationContext());
    }
}
