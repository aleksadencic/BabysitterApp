package com.example.aleksadencic.bebysitterapplication.activities.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.aleksadencic.bebysitterapplication.R;
import com.example.aleksadencic.bebysitterapplication.activities.NewPostActivity;
import com.example.aleksadencic.bebysitterapplication.adapters.PostAdapter;
import com.example.aleksadencic.bebysitterapplication.domain.Post;
import com.example.aleksadencic.bebysitterapplication.domain.User;
import com.example.aleksadencic.bebysitterapplication.logic.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View view;
    User user;

    List<Post> postList;
    boolean isSelectedParameter;
    Button btnInsertPost;

    RecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public HomeFragment(boolean isSelectedParameter) {
        this.isSelectedParameter = isSelectedParameter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        user = (User) getArguments().getParcelable("user");

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnInsertPost = view.findViewById(R.id.btnInsertPost);

        List<Post> allPostsList = new ArrayList<>();
        postList = new ArrayList<>();
        if(!isSelectedParameter) {
            allPostsList = Controller.getInstance().getAllPosts();

        } else {
            //new params
            String settlement = getArguments().getString("settlement");
            String date = getArguments().getString("date");
//            toastMessage(date);
//
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

//            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
//            Date d;
//            try {
//                d = format.parse(date);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }


//            toastMessage(settlement + "    " + date);
            allPostsList = Controller.getInstance().getPosts(settlement, date);
        }

        for(Post p : allPostsList){
            if(p.getUser().getId() != user.getId()){
                postList.add(p);
            }
        }
        PostAdapter postAdapter = new PostAdapter(getActivity(), postList);
        recyclerView.setAdapter(postAdapter);

        btnInsertPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newPostIntent = new Intent(getActivity(), NewPostActivity.class);
                startActivity(newPostIntent);
            }
        });


        return view;
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(),message, Toast.LENGTH_LONG).show();
    }
}
