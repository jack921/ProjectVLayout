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

public class ColumnLayoutAdapter extends DelegateAdapter.Adapter {

    private Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;

    public ColumnLayoutAdapter(Context context, LayoutHelper helper) {
        this.context = context;
        this.helper = helper;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewholder(inflater.inflate(R.layout.layout_text, parent, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewholder myViewholder=(MyViewholder)holder;
        myViewholder.textView.setText(position+"");
    }

    public int getItemCount() {
        return 5;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        private TextView textView;
        public MyViewholder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.fix_name);
        }
    }


}
