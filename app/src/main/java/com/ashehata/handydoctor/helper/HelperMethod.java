package com.ashehata.handydoctor.helper;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.ashehata.handydoctor.R;
import com.ashehata.handydoctor.data.model.DateModel;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class HelperMethod {

    public static final String USER_PATIENT = "USER_PATIENT";
    public static final String USER_DOCTOR = "USER_DOCTOR";
    public static final String USER_HOSPITAL = "USER_HOSPITAL";

    private static ProgressDialog checkDialog;
    public static AlertDialog alertDialog;
    //public static Snackbar snackbar;
    public static Toast toast ;
    public static AlertDialog.Builder dialog ;

    public static void ReplaceFragment(FragmentManager supportFragmentManager, Fragment fragment, int container_id
            , boolean enableTransaction) {

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();


        if(enableTransaction == true){
            transaction.setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit);
        }
        transaction.replace(container_id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        /*
        if (toolbarTitle != null) {
            toolbarTitle.setText(title);
        }

         */

    }
    public static void changeLang(Context context, String lang) {
        Resources res = context.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(lang)); // API 17+ only.
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
    }


    public static void onLoadImageFromUrl(ImageView imageView, String URl, Context context, int drId) {
        Glide.with(context)
                .load(URl)
                .into(imageView);
    }

    public static void onLoadImage(CircleImageView circleImageView, Context context, int resId) {
        Glide.with(context)
                .load(resId)
                .into(circleImageView);
    }



    /*
    public static void createSnackBar(View view, String message, Context context, View.OnClickListener action, String Title) {
        snackbar = Snackbar.make(view, message, 1500);
        snackbar.setAction(Title, action)
                .setActionTextColor(context.getResources().getColor(android.R.color.holo_red_light))
                .show();
    }

     */

    //Calender

    public static void showCalender(Context context, String title, final TextView text_view_data, final DateModel data1) {

        DatePickerDialog mDatePicker = new DatePickerDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {

                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
                DecimalFormat mFormat = new DecimalFormat("00", symbols);
                String data = selectedYear + "-" + String.format(new Locale("en"), mFormat.format(Double.valueOf((selectedMonth + 1)))) + "-"
                        + mFormat.format(Double.valueOf(selectedDay));
                data1.setDateTxt(data);
                data1.setDay(mFormat.format(Double.valueOf(selectedDay)));
                data1.setMonth(mFormat.format(Double.valueOf(selectedMonth + 1)));
                data1.setYear(String.valueOf(selectedYear));
                if (text_view_data != null) {
                    text_view_data.setText(data);
                }
            }
        }, Integer.parseInt(data1.getYear()), Integer.parseInt(data1.getMonth()) - 1, Integer.parseInt(data1.getDay()));
        mDatePicker.setTitle(title);
        mDatePicker.show();
    }


    public static void showProgressDialog(Activity activity, String title) {
        try {
            checkDialog = new ProgressDialog(activity);
            checkDialog.setMessage(title);
            checkDialog.setIndeterminate(false);
            checkDialog.setCancelable(false);
            checkDialog.show();

        } catch (Exception e) {

        }
    }

    public static void dismissProgressDialog() {
        try {
            if (checkDialog != null && checkDialog.isShowing()) {
                checkDialog.dismiss();
            }
        } catch (Exception e) {

        }
    }

    public static void setViewFocus(View view){
        view.setFocusableInTouchMode(true);
        view.setFocusable(true);

    }
    public static void disappearKeypad(Activity activity, View v) {
        try {
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {

        }
    }
    public static void hideStatusBar(Activity activity){
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    public static void showStatusBar(Activity activity){
        // Show status bar
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void startActivity(Context from , Class<?> to,String action){
        Intent intent = new Intent(from ,to );
        intent.setAction(action);
        from.startActivity(intent);

    }
    public static void createToast(Context context , String title , int duration){
        if (toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(context, title, duration);
        toast.show();

    }
    public static void setSpinner(Activity activity, Spinner spinner, List<String> names) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item, names);

        spinner.setAdapter(adapter);
    }


    public static void showAlertDialog(Context context
            , String title
            , String messageBody
            , boolean cancelable
            , DialogInterface.OnClickListener positiveButtonAction
            , DialogInterface.OnClickListener negativeButtonAction
            , String positiveTitle
            , String negativeTitle){

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(messageBody);
        alertDialog.setCancelable(cancelable);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,positiveTitle,positiveButtonAction);
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,negativeTitle,negativeButtonAction);
        alertDialog.show();
    }

    public static void hideAlertDialog(Context context){

        if(alertDialog.isShowing()){
            alertDialog.hide();
        }


    }
    public static void setEtTypeFace(TextInputEditText textInputEditText) {
        textInputEditText.setTypeface(Typeface.DEFAULT);
        textInputEditText.setTransformationMethod(new PasswordTransformationMethod());
    }
    /*
    public static void dialogCheckBackButton(Context mContext, BaseFragment baseFragment){

        showAlertDialog(mContext
                , mContext.getString(R.string.do_you_want_exit)
                , null
                , false
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //homeCycleActivity.homeFragment();
                        hideAlertDialog(mContext);
                        baseFragment.onBack();


                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        hideAlertDialog(mContext);

                    }
                },mContext.getString(R.string.yes),mContext.getString(R.string.no));
    }

     */

}