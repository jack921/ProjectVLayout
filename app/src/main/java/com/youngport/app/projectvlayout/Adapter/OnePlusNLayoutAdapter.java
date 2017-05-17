package com.youngport.app.projectvlayout.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.youngport.app.projectvlayout.R;

/**
 * Created by admin on 2017/5/17.
 */

public class OnePlusNLayoutAdapter extends DelegateAdapter.Adapter{
    public Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;

    public OnePlusNLayoutAdapter(Context context,LayoutHelper helper){
        this.inflater = LayoutInflater.from(context);
        this.helper = helper;
        this.context=context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return this.helper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.layout_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {}

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.item_name);
        }
    }
}
