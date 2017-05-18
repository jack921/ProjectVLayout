package com.youngport.app.projectvlayout.VLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.youngport.app.projectvlayout.Adapter.DelegateRecyclerAdapter;
import com.youngport.app.projectvlayout.Adapter.StickyLayoutAdapter;
import com.youngport.app.projectvlayout.R;

/**
 * Created by admin on 2017/5/16.
 */

public class StickyLayoutHelperActivity extends Activity{

    private RecyclerView recyclerView;
    private DelegateAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter =new DelegateAdapter(manager, true);

        adapter.addAdapter(initStickyLayoutHelper(this));
        adapter.addAdapter(initLinearLayoutHelper(this));

        recyclerView.setAdapter(adapter);
    }

    public static DelegateRecyclerAdapter initLinearLayoutHelper(Context context){
        LinearLayoutHelper linearLayoutHelper=new LinearLayoutHelper();
        //设置间隔高度
        linearLayoutHelper.setDividerHeight(5);
        DelegateRecyclerAdapter delegateRecyclerAdapter=new DelegateRecyclerAdapter(context,linearLayoutHelper,"StickyLayoutHelper");
        return delegateRecyclerAdapter;
    }

    public static StickyLayoutAdapter initStickyLayoutHelper(Context context){
        StickyLayoutHelper stickyLayoutHelper=new StickyLayoutHelper();
        return new StickyLayoutAdapter(context,stickyLayoutHelper);
    }

}
