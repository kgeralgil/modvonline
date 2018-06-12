package pe.edu.upc.tottus.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by Fernando on 12/06/2018.
 */

public class ImageUtil {

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0,      decodedByte.length);
    }
}
