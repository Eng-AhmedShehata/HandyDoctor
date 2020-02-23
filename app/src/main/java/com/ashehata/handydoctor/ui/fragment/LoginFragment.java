package com.ashehata.handydoctor.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashehata.handydoctor.R;
import com.ashehata.handydoctor.helper.HelperMethod;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ashehata.handydoctor.helper.HelperMethod.ReplaceFragment;
import static com.ashehata.handydoctor.helper.HelperMethod.USER_DOCTOR;
import static com.ashehata.handydoctor.helper.HelperMethod.USER_HOSPITAL;
import static com.ashehata.handydoctor.helper.HelperMethod.USER_PATIENT;
import static com.ashehata.handydoctor.helper.HelperMethod.disappearKeypad;
import static com.ashehata.handydoctor.helper.HelperMethod.setEtTypeFace;

public class LoginFragment extends BaseFragment {


    @BindView(R.id.login_fragment_et_email)
    TextInputEditText loginFragmentEtEmail;
    @BindView(R.id.login_fragment_et_password)
    TextInputEditText loginFragmentEtPassword;
    @BindView(R.id.login_fragment_tv_reset_password)
    TextView loginFragmentTvResetPassword;
    @BindView(R.id.login_fragment_bs_register)
    LinearLayout loginFragmentBsRegister;
    @BindView(R.id.login_fragment_ll_parent)
    LinearLayout loginFragmentLlParent;
    @BindView(R.id.login_fragment_btn_patient)
    Button loginFragmentBtnPatient;
    @BindView(R.id.login_fragment_btn_doctor)
    Button loginFragmentBtnDoctor;
    @BindView(R.id.login_fragment_btn_hospital)
    Button loginFragmentBtnHospital;
    private BottomSheetBehavior<LinearLayout> sheetBehavior;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        sheetBehavior = BottomSheetBehavior.from(loginFragmentBsRegister);
        // change type face of password field
        setEtTypeFace(loginFragmentEtPassword);

        return view;
    }

    @OnClick({R.id.login_fragment_ll_parent, R.id.login_fragment_tv_reset_password,
            R.id.login_fragment_btn_patient,R.id.login_fragment_btn_doctor,R.id.login_fragment_btn_hospital})
    public void onViewClicked(View view) {
        //hide bottom sheet
        hideBottomSheet();
        loginFragmentEtEmail.clearFocus();
        loginFragmentEtPassword.clearFocus();
        disappearKeypad(getActivity(), getView());
        switch (view.getId()) {
            case R.id.ll_view:
                break;
            case R.id.login_fragment_tv_reset_password:
                break;
            case R.id.login_fragment_btn_patient:
                register(USER_PATIENT);
                hideBottomSheet();
                break;
            case R.id.login_fragment_btn_doctor:
                register(USER_DOCTOR);
                hideBottomSheet();
                break;
            case R.id.login_fragment_btn_hospital:
                register(USER_HOSPITAL);
                hideBottomSheet();
                break;
        }
    }

    private void register(String userType) {
        RegisterFragment registerFragment = new RegisterFragment();
        registerFragment.userType = userType;
        ReplaceFragment(getFragmentManager()
                , registerFragment
                , R.id.user_activity_fl_display
                , true);

    }

    @Override
    public void onBack() {
        loginFragmentEtEmail.clearFocus();
        loginFragmentEtPassword.clearFocus();
        disappearKeypad(getActivity(), getView());
        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            getActivity().finish();
        } else {
            hideBottomSheet();
        }
    }


    private void hideBottomSheet() {
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
}