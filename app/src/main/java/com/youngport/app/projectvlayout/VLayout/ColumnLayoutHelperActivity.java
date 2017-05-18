package com.youngport.app.projectvlayout.VLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.youngport.app.projectvlayout.Adapter.ColumnLayoutAdapter;
import com.youngport.app.projectvlayout.Adapter.DelegateRecyclerAdapter;
import com.youngport.app.projectvlayout.R;

/**
 * Created by admin on 2017/5/16.
 */

public class ColumnLayoutHelperActivity extends Activity{

    private RecyclerView recyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        DelegateAdapter adapter =new DelegateAdapter(manager, true);

        adapter.addAdapter(initColumnLayout(this));
        recyclerview.setAdapter(adapter);
    }

    public static ColumnLayoutAdapter initColumnLayout(Context context){
        ColumnLayoutHelper columnLayoutHelper=new ColumnLayoutHelper();
        columnLayoutHelper.setWeights(new float[]{20,20,20,20,20});
        columnLayoutHelper.setMarginBottom(20);
        ColumnLayoutAdapter columnLayoutAdapter=new ColumnLayoutAdapter(context,columnLayoutHelper,"ColumnLayoutHelper");
        return columnLayoutAdapter;
    }


}
