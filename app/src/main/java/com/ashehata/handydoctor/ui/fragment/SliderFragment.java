package com.ashehata.handydoctor.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.viewpager.widget.ViewPager;
import com.ashehata.handydoctor.R;
import com.ashehata.handydoctor.adapter.SliderAdapter;
import com.ashehata.handydoctor.data.local.shared.SharedPreferencesManger;
import com.ashehata.handydoctor.ui.activity.UserCycleActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import static com.ashehata.handydoctor.data.local.shared.SharedPreferencesManger.Skip_BUTTON;
import static com.ashehata.handydoctor.helper.HelperMethod.showStatusBar;

public class SliderFragment extends BaseFragment {


    @BindView(R.id.slider_fragment_vp_slider)
    ViewPager sliderFragmentVpSlider;
    @BindView(R.id.slider_fragment_btn_skip)
    Button sliderFragmentBtnSkip;
    private SliderAdapter sliderAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        ButterKnife.bind(this, view);

        // Show status bar
        showStatusBar(getActivity());

        // Set view pager adapter
        setAdapter();

        return view;
    }

    private void setAdapter() {
        sliderAdapter = new SliderAdapter(getContext());
        sliderAdapter.addPage(R.drawable.doctor2);
        sliderAdapter.addPage(R.drawable.doctor2);
        sliderAdapter.addPage(R.drawable.doctor2);

        if (sliderAdapter != null) {
            sliderFragmentVpSlider.setAdapter(sliderAdapter);
        }
    }

    @OnClick(R.id.slider_fragment_btn_skip)
    public void onViewClicked() {
        // save to shred preference
        SharedPreferencesManger.SaveData(getActivity(),Skip_BUTTON,true);
        startActivity(new Intent(getContext(), UserCycleActivity.class));
        getActivity().finish();
    }
}