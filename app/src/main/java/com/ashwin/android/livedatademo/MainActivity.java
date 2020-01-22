package com.ashwin.android.livedatademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {
    private TextView contentTextView;
    private Button loadButton;

    private MyViewModel myViewModel;

    private LiveData<List<String>> myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        initViews();

        myList = myViewModel.getMyLiveData();

        myList.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                if (strings != null && !strings.isEmpty()) {
                    StringBuilder sb = new StringBuilder("");
                    for (String item : strings) {
                        sb.append(item);
                        sb.append("\n");
                    }
                    contentTextView.setText(sb.toString());
                } else {
                    contentTextView.setText("Loading...");
                }
            }
        });
    }

    private void initViews() {
        contentTextView = (TextView) findViewById(R.id.content_textview);

        loadButton = (Button) findViewById(R.id.load_button);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.loadData();
            }
        });
    }
}
