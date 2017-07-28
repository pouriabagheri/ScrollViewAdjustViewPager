package com.bagherifaez.app.scrollviewadjustviewpager;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileDetailsFragment extends Fragment {

    public static final String TAG = "ProfileDetailsFragment";

    ImageView _ivProfileDetails1 ,_ivProfileDetails2,_ivProfileDetails3, _ivProfileDetailsSubstepper;
    TextView _tvProfileDetails1,_tvProfileDetails2,_tvProfileDetails3;
    RelativeLayout _rlProfileDetailsSubStepper;

    boolean isBusiness = false;


    public static ProfileDetailsFragment newInstance(int page, boolean isLast) {
        Bundle args = new Bundle();
        args.putInt("page", page);
        if (isLast)
            args.putBoolean("isLast", true);
        final ProfileDetailsFragment fragment = new ProfileDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        View view = inflater.inflate(R.layout.fragment_profile_details, container, false);

        initialisation(view);

        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.containerProfileDetails, new ProfileDetailsUserFragment());
        transaction.commit();
        return view;
    }

    private void initialisation(View view) {
        _tvProfileDetails1 = (TextView) view.findViewById(R.id.tvProfileDetails1);
        _tvProfileDetails2 = (TextView) view.findViewById(R.id.tvProfileDetails2);
        _tvProfileDetails3 = (TextView) view.findViewById(R.id.tvProfileDetails3);

        _rlProfileDetailsSubStepper = (RelativeLayout) view.findViewById(R.id.rlProfileDetailsSubStepper);

    }



}