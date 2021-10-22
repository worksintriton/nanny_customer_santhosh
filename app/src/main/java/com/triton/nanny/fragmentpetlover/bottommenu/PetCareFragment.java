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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.triton.nanny.R;
import com.triton.nanny.fragmentpetlover.myappointments.FragmentPetCompletedAppointment;
import com.triton.nanny.fragmentpetlover.myappointments.FragmentPetMissedAppointment;
import com.triton.nanny.fragmentpetlover.myappointments.FragmentPetNewAppointment;
import com.triton.nanny.sessionmanager.SessionManager;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;




public class PetCareFragment extends Fragment implements View.OnClickListener {


    private String TAG = "PetCareFragment";


    Dialog dialog;
    private String userid;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tablayout)
    TabLayout tabLayout;


    private SharedPreferences preferences;
    private Context mContext;


    private String searchString = "";
    private int communication_type = 0;
    private int reviewcount;
    private String fromactivity,specialization;
    private String doctorid;

    View view;
    private String appintments;
    private int someIndex = 0;

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

        view = inflater.inflate(R.layout.fragment_pet_care1, container, false);
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

            appintments = getArguments().getString("appintments");
            Log.w(TAG,"appintments : "+appintments);


        }


        SessionManager sessionManager = new SessionManager(mContext);
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        Log.w(TAG,"customerid-->"+userid);

        setupViewPager(viewPager);

        if(appintments != null && appintments.equalsIgnoreCase("New")){
            someIndex = 0;
        }
        else if(appintments != null && appintments.equalsIgnoreCase("Completed")){
            someIndex = 1;
        }
        else if(appintments != null && appintments.equalsIgnoreCase("Missed")){
            someIndex = 2;
        }

        tabLayout.setupWithViewPager(viewPager);
        TabLayout.Tab tab = tabLayout.getTabAt(someIndex);
        tab.select();


        return view;
    }

    @Override
    public void onClick(View v) {

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentPetNewAppointment(), "New");
        adapter.addFragment(new FragmentPetCompletedAppointment(), "Completed");
        adapter.addFragment(new FragmentPetMissedAppointment(), "Missed");
        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
