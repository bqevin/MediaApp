package com.projects.kevinbarassa.mediaapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubscriptionsFragment extends Fragment {

    private ExpandableHeightListView listview;
    private ArrayList<SubscriptionItem> Bean;
    private SubscriptionAdapter baseAdapter;


    private int[] IMAGE1 = {R.drawable.newsname1, R.drawable.newsname1, R.drawable.newsname1};
    private int[] IMAGE2 = {R.drawable.img1, R.drawable.img1, R.drawable.img1};
    private int[] IMAGE3 = {R.drawable.more, R.drawable.more, R.drawable.more};
    private String[] NEWSNAME = {"Fox News .", "Fox News .", "Fox News ."};
    private String[] TITLE = {"1 day ago", "1 day ago", "1 day ago"};
    private String[] NEWS = {"Trump’s Plan for AmericanMade iPhonew Wold Be Disastrous. Trump’s Plan for AmericanMade iPhonew Wold Be Disastrous",
            "Trump’s Plan for AmericanMade iPhonew Wold Be Disastrous. Trump’s Plan for AmericanMade iPhonew Wold Be Disastrous",
            "Trump’s Plan for AmericanMade iPhonew Wold Be Disastrous. Trump’s Plan for AmericanMade iPhonew Wold Be Disastrous"};
    private String[] NEWSSUB = {"Why even a President Trump couldn’t make Apple manufacture iPhone in the state.","Why even a President Trump couldn’t make Apple manufacture iPhone in the state.",
            "Why even a President Trump couldn’t make Apple manufacture iPhone in the state."};
    private String[] INTREST = {"You've shown interest in iPhone","You've shown interest in iPhone","You've shown interest in iPhone"};


    public SubscriptionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_subscriptions, container, false);
        listview = (ExpandableHeightListView)rootView.findViewById(R.id.listview);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity.getSupportActionBar() != null){
                activity.getSupportActionBar().setTitle("My Subscriptions");
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Bean = new ArrayList<SubscriptionItem>();

        for (int i= 0; i< IMAGE1.length; i++){

            SubscriptionItem bean = new SubscriptionItem(IMAGE1[i], IMAGE2[i], IMAGE3[i], NEWSNAME[i], TITLE[i], NEWS[i], NEWSSUB[i], INTREST[i]);
            Bean.add(bean);

        }

        baseAdapter = new SubscriptionAdapter(getActivity(), Bean) {
        };

        listview.setAdapter(baseAdapter);

    }
}
