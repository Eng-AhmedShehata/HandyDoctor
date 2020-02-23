package com.ashehata.handydoctor.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashehata.handydoctor.R;

import butterknife.ButterKnife;

public class RegisterFragment extends BaseFragment {



    public String userType ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_register, container, false) ;
        ButterKnife.bind(this,view);



        return view;
    }
}