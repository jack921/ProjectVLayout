package com.youngport.app.projectvlayout.VLayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.youngport.app.projectvlayout.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by admin on 2017/5/16.
 */

public class AllActivity extends Activity{

    private RecyclerView recyclerview;
    private DelegateAdapter delegateAdapter ;
    final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);

        initView();
    }

    public void initView(){
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerview.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0,10);

        adapters.add(LinearLayoutHelperActivity.init(this));
        adapters.add(ColumnLayoutHelperActivity.initColumnLayout(this));
        adapters.add(GridLayoutHelperActivity.init(this));
        adapters.add(FixLayoutHelperActivity.initFixLayoutHelper(this));
        adapters.add(ScrollFixLayoutHelperActivity.initScrollFixLayout(this));
        adapters.add(SingleLayoutHelperActivity.initSingleLayout(this));
        adapters.add(OnePlusNLayoutHelperActivity.initOnePlusNLayout(this));
        adapters.add(FloatLayoutHelperActivity.initFloatLayoutHelper(this));
        adapters.add(StickyLayoutHelperActivity.initStickyLayoutHelper(this));
        adapters.add(StaggeredGridLayoutHelperActivity.init(this));

        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        delegateAdapter = new DelegateAdapter(manager);

        delegateAdapter.setAdapters(adapters);
        recyclerview.setAdapter(delegateAdapter);
    }

}
