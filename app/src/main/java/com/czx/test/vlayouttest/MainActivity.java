package com.czx.test.vlayouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VirtualLayoutManager layoutManager;
    private DelegateAdapter delegateAdapter;
    private GridAdapter gridAdapter;
    private List<String> dataList = new ArrayList<>();
    List<DelegateAdapter.Adapter> adapterList = new LinkedList<>();
    private CommonAdapter commonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        viewPool.setMaxRecycledViews(0, 20);
        recyclerView.setRecycledViewPool(viewPool);

        delegateAdapter = new DelegateAdapter(layoutManager, false);

        //第一个adapter
        GridLayoutHelper gridLayoutHelper1 = new GridLayoutHelper(4);
        gridLayoutHelper1.setAutoExpand(false);
        commonAdapter = new CommonAdapter(gridLayoutHelper1);
        adapterList.add(commonAdapter);

        //第二个adapter
        GridLayoutHelper gridLayoutHelper2 = new GridLayoutHelper(3);
        gridLayoutHelper2.setAutoExpand(false);
        gridLayoutHelper2.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                String item = gridAdapter.getItem(position);
                if (item.contains("标题")) {
                    return 3;
                }
                return 1;
            }
        });
        gridAdapter = new GridAdapter(gridLayoutHelper2);
        adapterList.add(gridAdapter);   //这里添加第二个adapter和第一个adapter的顺序换过来，则可以正常显示


        recyclerView.setAdapter(delegateAdapter);
        delegateAdapter.setAdapters(adapterList);

        initData();

    }

    private void initData() {
        List<String> commonList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            commonList.add("");
        }

        int j=0;
        for (int i = 0; i < 37; i++) {
            if (i % 5 == 0) {
                dataList.add("标题" + (++j));
            }else{
                dataList.add("j");
            }
        }

        commonAdapter.addAll(commonList);
        gridAdapter.addAll(dataList);

    }
}
