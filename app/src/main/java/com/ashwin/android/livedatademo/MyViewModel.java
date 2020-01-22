package com.ashwin.android.livedatademo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<String>> myLiveData;

    public MyViewModel() {
        super();
    }

    public LiveData<List<String>> getMyLiveData() {
        if (myLiveData == null) {
            myLiveData = new MutableLiveData<>();
        }
        return myLiveData;
    }

    public void loadData() {
        myLiveData.setValue(new ArrayList<String>());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> myList = new ArrayList<>();
                myList.add("one");
                myList.add("two");
                myList.add("three");
                myList.add("four");

                long seed = System.nanoTime();

                Collections.shuffle(myList, new Random(seed));

                myLiveData.setValue(myList);
            }
        }, 5000);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.w("livedata-demo", "main-viewmodel: on-cleared");
    }
}
