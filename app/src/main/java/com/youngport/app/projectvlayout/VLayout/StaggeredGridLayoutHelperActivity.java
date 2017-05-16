package com.youngport.app.projectvlayout.VLayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.youngport.app.projectvlayout.Adapter.StaggeredAdapter;
import com.youngport.app.projectvlayout.R;

/**
 * Created by admin on 2017/5/16.
 */

public class StaggeredGridLayoutHelperActivity extends Activity{

    private RecyclerView recyclerview;
    private StaggeredAdapter staggeredAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        init();
    }

    public void init(){
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        DelegateAdapter adapter =new DelegateAdapter(manager, true);

        StaggeredGridLayoutHelper staggeredGridLayoutHelper=new StaggeredGridLayoutHelper(3,20);
        staggeredAdapter=new StaggeredAdapter(this,staggeredGridLayoutHelper);
        adapter.addAdapter(staggeredAdapter);
        recyclerview.setAdapter(adapter);
    }


}
