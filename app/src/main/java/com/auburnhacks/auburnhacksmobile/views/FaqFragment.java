package com.auburnhacks.auburnhacksmobile.views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.auburnhacks.auburnhacksmobile.R;
import com.auburnhacks.auburnhacksmobile.data.QuestionAdapter;
import com.auburnhacks.auburnhacksmobile.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FaqFragment extends Fragment {

    @BindView(R.id.rv_faq) RecyclerView mFaqRecyclerView;
    private RecyclerView.Adapter mCardAdapter;
    private RecyclerView.LayoutManager mCardLayoutManager;

    public FaqFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =
                inflater.inflate(R.layout.fragment_faq, container, false);
        ButterKnife.bind(this, view);
        mCardLayoutManager = new LinearLayoutManager(getContext());
        mFaqRecyclerView.setLayoutManager(mCardLayoutManager);
        new FetchApiTask().execute();
        return view;
    }


    public class FetchApiTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL apiRequestUrl = NetworkUtils.buildApiUrl();
                return NetworkUtils.getResponseFromHttpUrl(apiRequestUrl);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            mCardAdapter = new QuestionAdapter(getContext(), result);
            mFaqRecyclerView.setAdapter(mCardAdapter);
        }
    }
}
