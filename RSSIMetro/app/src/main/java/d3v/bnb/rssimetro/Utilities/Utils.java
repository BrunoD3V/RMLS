package d3v.bnb.rssimetro.Utilities;

import android.widget.EditText;

/**
 * Created by E749 on 17/02/2018.
 */

public class Utils {

    public static boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
