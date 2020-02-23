package com.ashehata.handydoctor.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ashehata.handydoctor.R;
import com.ashehata.handydoctor.data.local.shared.SharedPreferencesManger;
import com.ashehata.handydoctor.ui.activity.UserCycleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ashehata.handydoctor.data.local.shared.SharedPreferencesManger.Skip_BUTTON;
import static com.ashehata.handydoctor.helper.HelperMethod.ReplaceFragment;
import static com.ashehata.handydoctor.helper.HelperMethod.hideStatusBar;
import static com.ashehata.handydoctor.helper.HelperMethod.showStatusBar;

public class SplashFragment extends BaseFragment {

    private static final long SPLASH_DISPLAY_LENGTH = 2200;
    @BindView(R.id.splash_fragment_iv_icon)
    ImageView splashFragmentIvIcon;
    private Handler handler;
    private Runnable task;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this,view);
        handler = new Handler();

        // hide status bar
        hideStatusBar(getActivity());

        //set animation for logo
        setLogoAnimation();

        startNewLayout();

        return view;
    }

    private void startNewLayout() {
        //check from shared preference
        if(SharedPreferencesManger.LoadBoolean(getActivity(),Skip_BUTTON)){
            showUserActivity();
        }else {
            //show slider fragment
            showSlider();
        }
        handler.postDelayed(task, SPLASH_DISPLAY_LENGTH);
    }

    private void showUserActivity() {
        task =new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getContext() , UserCycleActivity.class));
                getActivity().finish();
            }
        };

    }

    private void setLogoAnimation() {
        Animation bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        splashFragmentIvIcon.setAnimation(bounce);
    }

    private void showSlider() {
         task =new Runnable() {
             @Override
             public void run() {
                 // Show Slider Fragment
                 ReplaceFragment(getFragmentManager()
                         ,new SliderFragment()
                         , R.id.splash_activity_fl_splash
                         , false);
             }

         };
    }

    @Override
    public void onBack() {
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        if(handler !=null){
            handler.removeMessages(0);
            handler.removeCallbacksAndMessages(null);

        }
        super.onDestroy();
    }
}