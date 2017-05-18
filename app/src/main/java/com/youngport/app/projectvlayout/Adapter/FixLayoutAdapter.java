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
 * Created by admin on 2017/5/16.
 */

public class FixLayoutAdapter extends DelegateAdapter.Adapter {

    private Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;
    private String text;

    public FixLayoutAdapter(Context context, LayoutHelper helper,String text) {
        this.context = context;
        this.helper = helper;
        this.inflater = LayoutInflater.from(context);
        this.text=text;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewholder(inflater.inflate(R.layout.layout_text,null));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position%2==0){
            holder.itemView.setBackgroundColor(0xaa3F51B5);
        }else{
            holder.itemView.setBackgroundColor(0xccFF4081);
        }
        MyViewholder myViewholder=(MyViewholder)holder;
        myViewholder.textView.setText(text);
    }

    public int getItemCount() {
        return 1;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        private TextView textView;
        public MyViewholder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.fix_name);
        }
    }

}

