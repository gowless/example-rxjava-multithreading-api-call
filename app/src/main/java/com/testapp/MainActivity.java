package com.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.testapp.model.Item;
import com.testapp.model.Main;
import com.testapp.network.Initializator;
import com.testapp.network.Interface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function3;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    public static List<Item> listItems;

    RecyclerView recyclerView;

    Button buttonClear, buttonFetch;
    ItemAdapter adapterAllMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonClear = findViewById(R.id.clear);
        buttonFetch = findViewById(R.id.fetch);

        Interface apiInterfaceCount = Initializator.getClient().create(Interface.class);
        Observable<Main> call = apiInterfaceCount.getData("java", "15", "1", "desc");
        Observable<Main> call2 = apiInterfaceCount.getData("java", "15", "2", "desc");
        Observable<Main> call3 = apiInterfaceCount.getData("java", "15", "3", "desc");


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        buttonFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Observable<List<Main>> result =
                        Observable.zip(call.subscribeOn(Schedulers.io()), call2.subscribeOn(Schedulers
                                .io()), call3.subscribeOn(Schedulers.io()), new Function3<Main, Main, Main, List<Main>>() {
                            @Override
                            public List<Main> apply(Main type1, Main type2, Main type3) {
                                List<Main> list = new ArrayList<>();
                                listItems = new ArrayList<>();
                                listItems.addAll(type1.getItems());
                                listItems.addAll(type2.getItems());
                                list.add(type1);
                                list.add(type2);
                                Collections.sort(listItems, new CustomComparator());
                                adapterAllMain = new ItemAdapter(listItems, getApplicationContext());
                                adapterAllMain.setData(listItems);
                                recyclerView.setAdapter(adapterAllMain);

                                return list;
                            }
                        });




                result.observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new Observer<List<Main>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<Main> s) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });


            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapterAllMain.data.clear(); // clear list
                adapterAllMain.notifyDataSetChanged();

            }
        });

    }

}