package com.ashehata.handydoctor.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.ashehata.handydoctor.ui.fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity {

    public BaseFragment baseFragment;

    public void superBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }
}
