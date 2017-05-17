package com.youngport.app.projectvlayout.VLayout;

import android.app.Activity;
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

        initLinearLayoutHelper();
        initFloatLayoutHelper();
    }

    public void initLinearLayoutHelper(){
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);

        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter =new DelegateAdapter(manager, true);
        LinearLayoutHelper linearLayoutHelper=new LinearLayoutHelper();
        DelegateRecyclerAdapter delegateRecyclerAdapter=new DelegateRecyclerAdapter(this,linearLayoutHelper);
        adapter.addAdapter(delegateRecyclerAdapter);
    }

    public void initFloatLayoutHelper(){
        FloatLayoutHelper floatLayoutHelper=new FloatLayoutHelper();
        floatLayoutHelper.setDefaultLocation(20,250);
        FixLayoutAdapter fixLayoutAdapter=new FixLayoutAdapter(this,floatLayoutHelper);
        adapter.addAdapter(fixLayoutAdapter);

        recyclerView.setAdapter(adapter);
    }


}
