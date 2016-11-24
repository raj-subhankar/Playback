package com.yellowsoft.playback;

import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CustomListAdapter adapter;
    private ArrayList<Video> videoList = new ArrayList<Video>();
    EditText editTextUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUrl = (EditText) findViewById(R.id.etUrl);

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
            Video v = new Video();
            v.setTitle(files[i].getName());
            videoList.add(v);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new CustomListAdapter(getApplicationContext(), videoList);
        recycler.setAdapter(adapter);

        Button btnPasteUrl = (Button) findViewById(R.id.btnPaste);
        btnPasteUrl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onClickButtonPasteToUrlField(view);
            }
        });
    }

    public void onClickButtonPasteToUrlField(View v) {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm.hasPrimaryClip()) {
            ClipDescription desc = cm.getPrimaryClipDescription();
            Log.e("asd", desc.toString());
            if (desc.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                CharSequence pasteText = cm.getPrimaryClip().getItemAt(0).getText();
                editTextUrl.setText(pasteText);
                Intent intent = new Intent(MainActivity.this,
                        PlayerActivity.class);
                intent.putExtra("url", pasteText);
                startActivity(intent);
            } else {
                Log.e("asd", "not text");
                //makeToast("Unable to paste non-text data. Please copy from Instagram again.");
                Snackbar.make(v, "Unable to paste non-text data. Please copy from Instagram again.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        } else {
            Log.e("asd", "nothing to paste");
            //makeToast("Clipboard is empty. Please copy from Instagram again.");
            Snackbar.make(v, "Clipboard is empty. Please copy from Instagram again.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
