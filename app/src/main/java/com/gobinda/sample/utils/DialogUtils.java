package com.gobinda.sample.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.gobinda.sample.R;

/**
 * Dialog Utils Class
 */

public class DialogUtils {
    private DialogUtils(){
    }

    /**
     * Shows a popup message
     *
     * @param c
     * @param message
     */
    public static void showMessage(Context c, String message){
        AlertDialog alertDialog = new AlertDialog.Builder(c).create();
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,c.getString(R.string.ok),
                (dialog, __) -> dialog.dismiss());
        alertDialog.show();

    }
}
