package com.auburnhacks.auburnhacksmobile.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionAdapter
        extends RecyclerView.Adapter<QuestionAdapter.QuestionAdapterViewHolder> {

    public Context mContext;
    private ArrayList<String> questionList;
    private ArrayList<String> answerList;

    public static final String PARAM_INFO_CARDS = "info_cards";
    public static final String PARAM_QUESTION = "question";
    public static final String PARAM_ANSWER = "answer";

    public class QuestionAdapterViewHolder extends RecyclerView.ViewHolder {
        public QuestionAdapterViewHolder(LinearLayout view) {
            super(view);
        }
    }

    public QuestionAdapter(Context context, String jsonString) {
        mContext = context;
        extractData(jsonString);
    }

    @NonNull
    @Override
    public QuestionAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void extractData(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray questionResults = jsonObject.getJSONArray(PARAM_INFO_CARDS);
            for (int i = 0; i < questionResults.length(); i++) {
                String question = questionResults.getJSONObject(i).optString(PARAM_QUESTION);
                String answer = questionResults.getJSONObject(i).optString(PARAM_ANSWER);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
