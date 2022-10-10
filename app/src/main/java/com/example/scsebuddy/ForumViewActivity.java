package com.example.scsebuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scsebuddy.dynamicdesign.CourseReview_RecyclerViewAdapter;
import com.example.scsebuddy.dynamicdesign.ForumPost_RecyclerViewAdapter;
import com.example.scsebuddy.requestsresults.ConstantVariables;
import com.example.scsebuddy.requestsresults.CourseReview;
import com.example.scsebuddy.requestsresults.CourseReviewResult;
import com.example.scsebuddy.requestsresults.ForumPost;
import com.example.scsebuddy.requestsresults.ForumPostResult;
import com.example.scsebuddy.requestsresults.RetrofitInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForumViewActivity extends AppCompatActivity {


    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    Context context;
    TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_view);
        titleTextView = findViewById(R.id.titleTextView);


        Context context = this;
        retrofit = new Retrofit.Builder().baseUrl(ConstantVariables.getSERVER_URL()).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);


        //intent passing bundle
        Intent ii = getIntent();
        Bundle b = ii.getExtras();
        if(b!=null){
            titleTextView.setText(b.get("topicTitle")+"");
            Log.e("HELLO", b.get("topicTitle")+"");
            }

            HashMap<String, String> map = new HashMap<>();
            String topicID = titleTextView.getText().toString();
            map.put("topicID", topicID);

        Call<ForumPostResult> executeAllForumPost = retrofitInterface.executeAllForumPost(map);

        executeAllForumPost.enqueue(new Callback<ForumPostResult>() {
            @Override
            public void onResponse(Call<ForumPostResult> call, Response<ForumPostResult> response) {
                if (response.code() == 200) {
//                        TopicsResult topicR = response.body();
//
//                        ArrayList<Topic> topics = new ArrayList<>(Arrays.asList(topicR.getTopics()));
//
//                        RecyclerView topicsRecyclerView = findViewById(R.id.topicsRecycleView);
//
//                        Topics_RecyclerViewAdapter adapter = new Topics_RecyclerViewAdapter(context, topics);
//                        topicsRecyclerView.setAdapter(adapter);
//                        topicsRecyclerView.setLayoutManager(new LinearLayoutManager(context));

                    ForumPostResult forumR = response.body();
                    ArrayList<ForumPost> posts = new ArrayList<>(Arrays.asList(forumR.getForumPost()));
                    //Log.e("TEST", forumR.getCoursesReview()[0].getGrade()+"");
                    RecyclerView forumPostRecyclerView = findViewById(R.id.forumPostRecyclerView);

                    ForumPost_RecyclerViewAdapter adapter = new ForumPost_RecyclerViewAdapter(context,posts);
                    forumPostRecyclerView.setAdapter(adapter);
                    forumPostRecyclerView.setLayoutManager(new LinearLayoutManager(context));





                } else if (response.code() == 404) {
                    Toast.makeText(ForumViewActivity.this, "No Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ForumPostResult> call, Throwable t) {
                Toast.makeText(ForumViewActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



//            Call<ForumPostResult> executeAllForumPost = retrofitInterface.executeAllForumPost(map);
//
//        executeAllForumPost.enqueue(new Callback<ForumPostResult>() {
//                @Override
//                public void onResponse(Call<ForumPostResult> call, Response<ForumPostResult> response) {
//                    if (response.code() == 200) {
////                        TopicsResult topicR = response.body();
////
////                        ArrayList<Topic> topics = new ArrayList<>(Arrays.asList(topicR.getTopics()));
////
////                        RecyclerView topicsRecyclerView = findViewById(R.id.topicsRecycleView);
////
////                        Topics_RecyclerViewAdapter adapter = new Topics_RecyclerViewAdapter(context, topics);
////                        topicsRecyclerView.setAdapter(adapter);
////                        topicsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//
//                        ForumPostResult forumR = response.body();
//                        //Log.e("HELLO", forumR.getForumPost().length + "");
//                        ArrayList<ForumPost> posts = new ArrayList<>(Arrays.asList(forumR.getForumPost()));
//                        RecyclerView forumPostRecyclerView = findViewById(R.id.forumPostRecyclerView);
//
//                        ForumPost_RecyclerViewAdapter adapter = new ForumPost_RecyclerViewAdapter(context,posts);
//                        forumPostRecyclerView.setAdapter(adapter);
//                        forumPostRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//
//
//
//
//
//                    } else if (response.code() == 404) {
//                        Toast.makeText(ForumViewActivity.this, "No Data", Toast.LENGTH_LONG).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ForumPostResult> call, Throwable t) {
//                    Toast.makeText(ForumViewActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            });


        }

    public void addPost(View v){
        Intent intent = new Intent(v.getContext(),ForumPostActivity.class);
        intent.putExtra("forumTopic", titleTextView.getText().toString());

        startActivity(intent);
    }
}