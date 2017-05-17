package com.youngport.app.projectvlayout.VLayout;

import android.app.Activity;
import android.content.Context;
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
    private static StaggeredAdapter staggeredAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        DelegateAdapter adapter =new DelegateAdapter(manager, true);

        adapter.addAdapter(init(this));
        recyclerview.setAdapter(adapter);
    }

    public static StaggeredAdapter init(Context context){
        StaggeredGridLayoutHelper staggeredGridLayoutHelper=new StaggeredGridLayoutHelper(3,20);
        staggeredAdapter=new StaggeredAdapter(context,staggeredGridLayoutHelper);
        return staggeredAdapter;
    }


}
