package com.yellowsoft.playback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by subhankar on 11/23/2016.
 */

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.MyViewHolder> implements View.OnClickListener {

    private Context context;
    ArrayList<Video> videoList;

    public CustomListAdapter(Context context, ArrayList<Video> objects) {
        this.context = context;
        this.videoList = objects;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        public RelativeLayout listRow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            listRow = (RelativeLayout) view.findViewById(R.id.listrow);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        myViewHolder.listRow.setOnClickListener(this);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Video video = videoList.get(position);
        String title = video.getTitle();
        holder.title.setText(title);
        holder.listRow.setTag(holder);

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

    @Override
    public void onClick(View view) {
        MyViewHolder holder = (MyViewHolder) view.getTag();
        Integer position = holder.getAdapterPosition();

        final int viewId = view.getId();
        if(viewId == R.id.listrow) {
            Intent i = new Intent(context,
                    PlayerActivity.class);
            i.putExtra("url", Environment.getExternalStorageDirectory().toString() + "/Playback/"+ videoList.get(position).getTitle());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
