package com.yellowsoft.playback;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by subhankar on 11/23/2016.
 */

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.MyViewHolder> {

    private Context context;
    ArrayList<Video> videoList;

    public CustomListAdapter(Context context, ArrayList<Video> objects) {
        this.context = context;
        this.videoList = objects;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Video video = videoList.get(position);
        String title = video.getTitle();
        holder.title.setText(title);

        if(video.getThumbnailUrl() != "") {
            Picasso.with(context).load(R.drawable.ic_video_icon).into(holder.thumbnail);
        } else {
            Picasso.with(context).load(R.drawable.ic_video_icon).into(holder.thumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
