package com.ashehata.handydoctor.data.local.shared;

import android.app.Activity;
import android.content.SharedPreferences;

public class SharedPreferencesManger {

    private static SharedPreferences sharedPreferences = null;
    public static String Skip_BUTTON = "Skip_BUTTON";


    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "Sofra", activity.MODE_PRIVATE);
        }
    }

    public static void SaveData(Activity activity, String data_Key, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.apply();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void SaveData(Activity activity, String data_Key, boolean data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static String LoadData(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.apply();
        } else {
            setSharedPreferences(activity);
        }

        return sharedPreferences.getString(data_Key, null);
    }

    public static boolean LoadBoolean(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferences(activity);
        }
        return sharedPreferences.getBoolean(data_Key, false);
    }

    /*
    public static void SaveData(Activity activity, String data_Key, Object data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String StringData = gson.toJson(data_Value);
            editor.putString(data_Key, StringData);
            editor.commit();
        }
    }


    public static void saveClientData(Activity activity, User clientCycle) {
        SaveData(activity, CLIENT_USER_DATA, clientCycle);
    }

    public static User loadClientData(Activity activity) {
        User userData = null;

        Gson gson = new Gson();
        userData = gson.fromJson(LoadData(activity, CLIENT_USER_DATA), User.class);

        return userData;
    }

     */

    public static void clean(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }

    /*
    public static void SaveUserType(Activity activity, String user_type) {
        SaveData(activity, USER_TYPE, user_type);
    }

    public static String LoadUserType(Activity activity) {
        return LoadData(activity, USER_TYPE);
    }

     */
}