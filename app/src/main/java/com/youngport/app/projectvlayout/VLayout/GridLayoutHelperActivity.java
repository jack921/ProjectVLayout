package com.youngport.app.projectvlayout.VLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.youngport.app.projectvlayout.Adapter.DelegateRecyclerAdapter;
import com.youngport.app.projectvlayout.R;

/**
 * Created by admin on 2017/5/16.
 */

public class GridLayoutHelperActivity extends Activity{

    public RecyclerView recyclerView;
    private static DelegateRecyclerAdapter delegateRecyclerAdapter;
    private DelegateAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter =new DelegateAdapter(manager, true);

        adapter.addAdapter(init(this));
        recyclerView.setAdapter(adapter);
    }

    public static DelegateRecyclerAdapter init(Context context){
        GridLayoutHelper gridLayoutHelper=new GridLayoutHelper(4);
//        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if (position > 13) {
//                    return 2;
//                }else {
//                    return 1;
//                }
//            }
//        });
        gridLayoutHelper.setAutoExpand(false);
        delegateRecyclerAdapter=new DelegateRecyclerAdapter(context,gridLayoutHelper);
        return delegateRecyclerAdapter;
    }


}
