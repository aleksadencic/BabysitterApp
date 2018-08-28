package com.example.aleksadencic.bebysitterapplication.activities.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleksadencic.bebysitterapplication.R;
import com.example.aleksadencic.bebysitterapplication.activities.notes.AllNotesAcitivity;
import com.example.aleksadencic.bebysitterapplication.activities.notes.EditNoteActivity;
import com.example.aleksadencic.bebysitterapplication.domain.Post;
import com.example.aleksadencic.bebysitterapplication.domain.Product;
import com.example.aleksadencic.bebysitterapplication.domain.User;
import com.example.aleksadencic.bebysitterapplication.logic.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    View view;
    User user;

    TextView tvName, tvMail, tvRole, tvGender;
    ListView listViewPosts;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        user = (User) getArguments().getParcelable("user");
        listViewPosts = (ListView) view.findViewById(R.id.listViewPosts);

        tvName = (TextView) view.findViewById(R.id.tvName);
        tvMail = (TextView) view.findViewById(R.id.tvMail);
        tvRole = (TextView) view.findViewById(R.id.tvRole);
        tvGender = (TextView) view.findViewById(R.id.tvGender);

        tvName.setText(user.toString());
        tvMail.setText(user.getMail());
        tvRole.setText(user.getRole());
        tvGender.setText(user.getGender().equals("M") ? "male" : "female");

        populatePostsList();

        return view;
    }

    private void populatePostsList() {
        List<Post> postsArray = Controller.getInstance().getAllPosts();
        ArrayList<String> posts = new ArrayList<>();
        for(Post p : postsArray){
            if(p.getUser().equals(user)){
                String post = "Date: " + p.getDate() + "\nFrom: " + p.getTimeFrom() +
                        "\tTo: " + p.getTimeTo() + "\nLocation: " + p.getLocation() +
                        "\n" + p.getDescription();
                posts.add(post);
            }
        }
        ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, posts);
        listViewPosts.setAdapter(adapter);

    }

    public void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
