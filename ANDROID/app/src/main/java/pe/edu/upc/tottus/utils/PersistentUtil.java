package pe.edu.upc.tottus.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Fernando on 23/06/2018.
 */

public class PersistentUtil {

    public static void saveAddress(Context context, String address){
        SharedPreferences.Editor editor = context.getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
        editor.putString("address", address);
        editor.commit();
    }

    public static String getAddress(Context context){
        SharedPreferences prefs = context.getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        String address = prefs.getString("address", "192.168.1.2");
        return address;
    }
}
