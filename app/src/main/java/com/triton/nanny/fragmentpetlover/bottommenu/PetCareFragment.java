package com.triton.nanny.fragmentpetlover.bottommenu;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.triton.nanny.R;
import com.triton.nanny.sessionmanager.SessionManager;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PetCareFragment extends Fragment implements View.OnClickListener {


    private String TAG = "PetCareFragment";

    private Handler handler = new Handler();


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    Dialog dialog;
    private String userid;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;


    private SharedPreferences preferences;
    private Context mContext;


    private String searchString = "";
    private int communication_type = 0;
    private int reviewcount;
    private String fromactivity,specialization;
    private String doctorid;

    View view;

    public PetCareFragment() {
        // Required empty public constructor
    }


   @SuppressLint("LogNotTimber")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(TAG,"onCreate-->");



    }

    @SuppressLint({"SetTextI18n", "LogNotTimber"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.w(TAG,"onCreateView-->");

        view = inflater.inflate(R.layout.fragment_pet_care, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ButterKnife.bind(this, view);
        mContext = getActivity();

        avi_indicator.setVisibility(View.GONE);

        if(getArguments() != null){
            fromactivity = getArguments().getString("fromactivity");
            reviewcount = getArguments().getInt("reviewcount");
            specialization = getArguments().getString("specialization");
            Log.w(TAG,"fromactivity : "+fromactivity+" reviewcount : "+reviewcount+" specialization : "+specialization);

            searchString = getArguments().getString("searchString");
            doctorid = getArguments().getString("doctorid");
            communication_type = getArguments().getInt("communication_type");
            Log.w(TAG," communication_type : "+communication_type+" searchString : "+searchString);


        }


        SessionManager sessionManager = new SessionManager(mContext);
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        Log.w(TAG,"customerid-->"+userid);


        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
