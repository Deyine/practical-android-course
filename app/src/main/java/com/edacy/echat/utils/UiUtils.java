package com.edacy.echat.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by djiddou on 11/4/17.
 */

public class UiUtils {

    /**
     * Return true if there is an internet connection
     * @param context
     * @return
     */
    public static boolean isConnectingToInternet(Context context) {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
