package com.bagherifaez.app.scrollviewadjustviewpager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;


public class ProfileDetailsUserFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    public static final String TAG = "ProfileDetailsUserFragment";

    private ProgressDialog progressDialog;

    Spinner _spGender, _spMaritalStatus, _spIdType ,_spPhoneNumberCode;
    Button _btnProfileDetailsUserNext;
    EditText _etFullName, _etIdNumber, _etMobileNumber;

    boolean uiInitialisedSpId = false;
    boolean uiInitialisedSpMaritalStatus = false;
    boolean uiInitialisedSpPhoneCode = false;

    public ProfileDetailsUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_profile_details_user, container, false);

        AndroidBug5497Workaround.assistActivity(getActivity(), v, R.id.llNestedScrollerContainer);
//        AndroidBug5497Workaround.assistActivity(getActivity());

        initialisation(v);


        _btnProfileDetailsUserNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }

    private void setSpinnerAdapterGender() {
        // Spinner Drop down elements
        List<String> genderList = new ArrayList<String>() {{
            add("Male");
            add("Female");
        }};
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,genderList);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        _spGender.setAdapter(dataAdapter);
    }

    private void setSpinnerAdapterMaritalStatus() {
        // Spinner Drop down elements
        List<String> maritalStatusList = new ArrayList<String>() {{
            add("Single");
            add("Married");
        }};
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,maritalStatusList);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        _spMaritalStatus.setAdapter(dataAdapter);
    }


    private void setSpinnerAdapterIdType() {
        // Spinner Drop down elements
        List<String> idTypeList = new ArrayList<String>() {{
            add("IC");
            add("License");
        }};
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,idTypeList);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        _spIdType.setAdapter(dataAdapter);
    }

    private void setSpinnerAdapterPhoneNumberCode() {
        // Spinner Drop down elements
        List<String> idTypeList = new ArrayList<String>() {{
            add("+1");
            add("+11");
            add("+50");
            add("+60");
        }};

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,idTypeList);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        _spPhoneNumberCode.setAdapter(dataAdapter);
    }


    private void initialisation(View v){
        _spGender = (Spinner) v.findViewById(R.id.spProfileDetailsUser_gender);
        _spMaritalStatus = (Spinner) v.findViewById(R.id.spProfileDetailsUser_maritalStatus);
        _spIdType = (Spinner) v.findViewById(R.id.spProfileDetailsUser_idType);
        _spPhoneNumberCode = (Spinner) v.findViewById(R.id.spProfileDetailsUser_phoneNumberCode);
        _btnProfileDetailsUserNext = (Button) v.findViewById(R.id.btnProfileDetailsUserNext);
        _etFullName = (EditText) v.findViewById(R.id.etProfileDetailsUser_fullName);
        _etIdNumber = (EditText) v.findViewById(R.id.etProfileDetailsUser_1);
        _etMobileNumber = (EditText) v.findViewById(R.id.etProfileDetailsUser_phoneNumber);

        setSpinnerAdapterGender();
        setSpinnerAdapterMaritalStatus();
        setSpinnerAdapterIdType();
        setSpinnerAdapterPhoneNumberCode();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
