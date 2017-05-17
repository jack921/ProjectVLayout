package com.youngport.app.projectvlayout.VLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.youngport.app.projectvlayout.Adapter.DelegateRecyclerAdapter;
import com.youngport.app.projectvlayout.Adapter.FixLayoutAdapter;
import com.youngport.app.projectvlayout.R;

/**
 * Created by admin on 2017/5/16.
 */

public class FloatLayoutHelperActivity extends Activity{

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

        adapter.addAdapter(initLinearLayout(this));
        adapter.addAdapter(initFloatLayoutHelper(this));

        recyclerView.setAdapter(adapter);
    }

    public static DelegateRecyclerAdapter initLinearLayout(Context context){
        LinearLayoutHelper linearLayoutHelper=new LinearLayoutHelper();
        DelegateRecyclerAdapter delegateRecyclerAdapter=new DelegateRecyclerAdapter(context,linearLayoutHelper);
        return delegateRecyclerAdapter;
    }

    public static FixLayoutAdapter initFloatLayoutHelper(Context context){
        FloatLayoutHelper floatLayoutHelper=new FloatLayoutHelper();
        floatLayoutHelper.setDefaultLocation(20,250);
        FixLayoutAdapter fixLayoutAdapter=new FixLayoutAdapter(context,floatLayoutHelper,"floatlayouthelper");
        return  fixLayoutAdapter;
    }


}
