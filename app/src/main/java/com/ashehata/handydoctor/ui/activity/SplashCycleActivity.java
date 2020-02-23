package com.ashehata.handydoctor.ui.activity;


import android.os.Bundle;
import com.ashehata.handydoctor.R;
import com.ashehata.handydoctor.ui.fragment.SplashFragment;
import static com.ashehata.handydoctor.helper.HelperMethod.ReplaceFragment;
import static com.ashehata.handydoctor.helper.HelperMethod.hideStatusBar;

public class SplashCycleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_cycle);

        // Hide status bar
        hideStatusBar(this);

        // Show Splash Fragment
        ReplaceFragment(getSupportFragmentManager()
                ,new SplashFragment()
                , R.id.splash_activity_fl_splash
                , false);


    }
}
