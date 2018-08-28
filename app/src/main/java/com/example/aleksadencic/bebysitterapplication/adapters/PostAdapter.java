package com.example.aleksadencic.bebysitterapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aleksadencic.bebysitterapplication.R;
import com.example.aleksadencic.bebysitterapplication.domain.Post;
import com.example.aleksadencic.bebysitterapplication.domain.Product;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Post> postList;

    //getting the context and product list with constructor
    public PostAdapter(Context mCtx, List<Post> postList) {
        this.mCtx = mCtx;
        this.postList = postList;
    }

    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_post_cards, null);
        return new PostAdapter.PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostAdapter.PostViewHolder holder, int position) {

        Post post = postList.get(position);
        holder.txtUser.setText(post.getUser().toString());
        holder.txtDescription.setText(post.getDescription());
        holder.txtDate.setText(String.valueOf(post.getDate()));
        holder.txtTimeFrom.setText(String.valueOf(post.getTimeFrom().getHours() + ":" + post.getTimeFrom().getMinutes()));
        holder.txtTimeTo.setText(String.valueOf(post.getTimeTo().getHours() + ":" + post.getTimeTo().getMinutes()));
        holder.txtSettlement.setText(post.getLocation().getName() + ", " + post.getLocation().getCity().getName());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    class PostViewHolder extends RecyclerView.ViewHolder {

        TextView txtUser, txtDescription, txtDate, txtTimeFrom, txtTimeTo, txtSettlement;

        public PostViewHolder(View itemView) {
            super(itemView);

            txtUser = itemView.findViewById(R.id.txtUser);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtTimeFrom = itemView.findViewById(R.id.txtTimeFrom);
            txtTimeTo = itemView.findViewById(R.id.txtTimeTo);
            txtSettlement = itemView.findViewById(R.id.txtSettlement);
        }
    }
}
