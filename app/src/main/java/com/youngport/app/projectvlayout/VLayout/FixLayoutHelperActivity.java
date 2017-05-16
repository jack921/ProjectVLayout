package com.youngport.app.projectvlayout.VLayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.youngport.app.projectvlayout.Adapter.DelegateRecyclerAdapter;
import com.youngport.app.projectvlayout.Adapter.FixLayoutAdapter;
import com.youngport.app.projectvlayout.R;

/**
 * Created by admin on 2017/5/16.
 */

public class FixLayoutHelperActivity extends Activity{

    private RecyclerView recyclerView;
    private DelegateAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        initLinearLayoutHelper();
        initFixLayoutHelper();
    }

    public void initLinearLayoutHelper(){
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);

        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter =new DelegateAdapter(manager, true);
        LinearLayoutHelper linearLayoutHelper=new LinearLayoutHelper();
        //设置间隔高度
        linearLayoutHelper.setDividerHeight(5);
        //设置布局底部与下个布局的间隔
        linearLayoutHelper.setMarginBottom(20);
        //设置间距
        linearLayoutHelper.setMargin(20,20,20,20);
        DelegateRecyclerAdapter delegateRecyclerAdapter=new DelegateRecyclerAdapter(this,linearLayoutHelper);
        adapter.addAdapter(delegateRecyclerAdapter);
    }

    public void initFixLayoutHelper(){
        FixLayoutHelper fixLayoutHelper=new FixLayoutHelper(FixLayoutHelper.BOTTOM_LEFT, 200, 200);
        FixLayoutAdapter fixLayoutAdapter=new FixLayoutAdapter(this,fixLayoutHelper);
        adapter.addAdapter(fixLayoutAdapter);
        recyclerView.setAdapter(adapter);
    }

}
