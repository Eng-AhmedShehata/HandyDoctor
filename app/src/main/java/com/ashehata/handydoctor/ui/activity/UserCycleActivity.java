package com.ashehata.handydoctor.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.ashehata.handydoctor.R;
import com.ashehata.handydoctor.ui.fragment.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ashehata.handydoctor.helper.HelperMethod.ReplaceFragment;

public class UserCycleActivity extends BaseActivity {

    @BindView(R.id.user_activity_fl_display)
    FrameLayout userActivityFlDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);
        ButterKnife.bind(this);

        // Show Splash Fragment
        ReplaceFragment(getSupportFragmentManager()
                , new LoginFragment()
                , R.id.user_activity_fl_display
                , false);

    }


}
