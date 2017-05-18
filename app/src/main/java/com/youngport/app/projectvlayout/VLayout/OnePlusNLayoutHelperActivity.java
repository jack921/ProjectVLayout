package com.youngport.app.projectvlayout.VLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.youngport.app.projectvlayout.Adapter.DelegateRecyclerAdapter;
import com.youngport.app.projectvlayout.Adapter.OnePlusNLayoutAdapter;
import com.youngport.app.projectvlayout.R;

/**
 * Created by admin on 2017/5/16.
 */

public class OnePlusNLayoutHelperActivity extends Activity{

    private RecyclerView recyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        DelegateAdapter adapter =new DelegateAdapter(manager, true);

        initOnePlusNLayout(this);
        adapter.addAdapter(initOnePlusNLayout(this));
        recyclerview.setAdapter(adapter);
    }

    public static OnePlusNLayoutAdapter initOnePlusNLayout(Context context){
        OnePlusNLayoutHelper onePlusNLayoutHelper=new OnePlusNLayoutHelper();
        //设置布局底部与下个布局的间隔
        onePlusNLayoutHelper.setMarginBottom(20);
        OnePlusNLayoutAdapter onePlusNLayoutAdapter=new OnePlusNLayoutAdapter(context,onePlusNLayoutHelper,"OnePlusNLayoutHelper");
        return onePlusNLayoutAdapter;
    }


}
