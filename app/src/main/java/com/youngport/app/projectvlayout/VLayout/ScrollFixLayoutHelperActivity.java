package com.youngport.app.projectvlayout.VLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.youngport.app.projectvlayout.Adapter.DelegateRecyclerAdapter;
import com.youngport.app.projectvlayout.Adapter.FixLayoutAdapter;
import com.youngport.app.projectvlayout.R;

/**
 * Created by admin on 2017/5/16.
 */

public class ScrollFixLayoutHelperActivity extends Activity{
    private RecyclerView recyclerview;
    private DelegateRecyclerAdapter delegateRecyclerAdapter;
    private DelegateAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        adapter =new DelegateAdapter(manager, true);

        adapter.addAdapter(initScrollFixLayout(this));
        adapter.addAdapter(initLinearLayout(this));
        recyclerview.setAdapter(adapter);
    }

    public DelegateRecyclerAdapter initLinearLayout(Context context){
        LinearLayoutHelper linearLayoutHelper=new LinearLayoutHelper();
        delegateRecyclerAdapter=new DelegateRecyclerAdapter(context,linearLayoutHelper);
        return delegateRecyclerAdapter;
    }

    public static FixLayoutAdapter initScrollFixLayout(Context context){
        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(15,15);
        //show_always:总是显示
        //show_on_enter:当页面滚动到这个视图的位置的时候，才显示
        //show_on_leave:当页面滚出这个视图的位置的时候显示
        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_ENTER);
        return new FixLayoutAdapter(context, scrollFixLayoutHelper);
    }

}
