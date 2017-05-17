package com.youngport.app.projectvlayout.VLayout;

import android.app.Activity;
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

        init();
        initScrollFixLayout();
    }

    public void init(){
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        adapter =new DelegateAdapter(manager, true);

        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(15,15);
        //show_always:总是显示
        //show_on_enter:当页面滚动到这个视图的位置的时候，才显示
        //show_on_leave:当页面滚出这个视图的位置的时候显示
        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_ENTER);
        adapter.addAdapter(new FixLayoutAdapter(this, scrollFixLayoutHelper));

        LinearLayoutHelper linearLayoutHelper=new LinearLayoutHelper();
        delegateRecyclerAdapter=new DelegateRecyclerAdapter(this,linearLayoutHelper);
        adapter.addAdapter(delegateRecyclerAdapter);
    }

    public void initScrollFixLayout(){
//        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_LEFT,15,15);
//        //show_always:总是显示
//        //show_on_enter:当页面滚动到这个视图的位置的时候，才显示
//        //show_on_leave:当页面滚出这个视图的位置的时候显示
//        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_ENTER);
//        adapter.addAdapter(new FixLayoutAdapter(this, scrollFixLayoutHelper));

        recyclerview.setAdapter(adapter);
    }

}
