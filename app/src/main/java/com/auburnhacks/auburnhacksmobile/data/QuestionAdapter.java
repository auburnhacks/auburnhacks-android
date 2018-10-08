package com.auburnhacks.auburnhacksmobile.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.auburnhacks.auburnhacksmobile.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionAdapter
        extends RecyclerView.Adapter<QuestionAdapter.QuestionAdapterViewHolder> {

    public Context mContext;
    private ArrayList<Question> questionList;

    public static final String PARAM_INFO_CARDS = "info_cards";
    public static final String PARAM_QUESTION = "question";
    public static final String PARAM_ANSWER = "answer";

    public class QuestionAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView questionTv;
        public TextView answerTv;
        public QuestionAdapterViewHolder(View v) {
            super(v);
            questionTv = (TextView) v.findViewById(R.id.question);
            answerTv = (TextView) v.findViewById(R.id.answer);
        }
    }

    public QuestionAdapter(Context context, String jsonString) {
        mContext = context;
        questionList = new ArrayList<>();
        extractData(jsonString);
    }

    @NonNull
    @Override
    public QuestionAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_view, parent, false);
        QuestionAdapterViewHolder vh = new QuestionAdapterViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapterViewHolder holder, int position) {
        String question = questionList.get(position).getQuestion();
        String answer = questionList.get(position).getAnswer();
        holder.questionTv.setText(question);
        holder.answerTv.setText(Html.fromHtml(answer));
        holder.answerTv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public void extractData(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray questionResults = jsonObject.getJSONArray(PARAM_INFO_CARDS);
            for (int i = 0; i < questionResults.length(); i++) {
                String question = questionResults.getJSONObject(i).optString(PARAM_QUESTION);
                String answer = questionResults.getJSONObject(i).optString(PARAM_ANSWER);
                questionList.add(new Question(question, answer));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
