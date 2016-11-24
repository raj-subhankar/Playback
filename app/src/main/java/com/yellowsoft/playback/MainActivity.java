package com.yellowsoft.playback;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CustomListAdapter adapter;
    private ArrayList<Video> videoList = new ArrayList<Video>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.list);
        adapter = new CustomListAdapter(getApplicationContext(), videoList);
        recycler.setAdapter(adapter);

        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "Playback");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }

        String path = Environment.getExternalStorageDirectory().toString()+"/Playback";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            Log.d("Files", "FileName:" + files[i].getName());
        }
    }
}
