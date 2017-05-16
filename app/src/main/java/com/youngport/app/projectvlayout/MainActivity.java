package com.youngport.app.projectvlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.youngport.app.projectvlayout.VLayout.AllActivity;
import com.youngport.app.projectvlayout.VLayout.ColumnLayoutHelperActivity;
import com.youngport.app.projectvlayout.VLayout.FixLayoutHelperActivity;
import com.youngport.app.projectvlayout.VLayout.FloatLayoutHelperActivity;
import com.youngport.app.projectvlayout.VLayout.GridLayoutHelperActivity;
import com.youngport.app.projectvlayout.VLayout.LinearLayoutHelperActivity;
import com.youngport.app.projectvlayout.VLayout.OnePlusNLayoutHelperActivity;
import com.youngport.app.projectvlayout.VLayout.ScrollFixLayoutHelperActivity;
import com.youngport.app.projectvlayout.VLayout.SingleLayoutHelperActivity;
import com.youngport.app.projectvlayout.VLayout.StaggeredGridLayoutHelperActivity;
import com.youngport.app.projectvlayout.VLayout.StickyLayoutHelperActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.linearLayoutHelper:
                startActivity(new Intent(this, LinearLayoutHelperActivity.class));
                break;
            case R.id.gridLayoutHelper:
                startActivity(new Intent(this, GridLayoutHelperActivity.class));
                break;
            case R.id.staggeredGridLayoutHelper:
                startActivity(new Intent(this, StaggeredGridLayoutHelperActivity.class));
                break;
            case R.id.fixLayoutHelper:
                startActivity(new Intent(this, FixLayoutHelperActivity.class));
                break;
            case R.id.scrollFixLayoutHelper:
                startActivity(new Intent(this, ScrollFixLayoutHelperActivity.class));
                break;
            case R.id.columnLayoutHelper:
                startActivity(new Intent(this, ColumnLayoutHelperActivity.class));
                break;
            case R.id.singleLayoutHelper:
                startActivity(new Intent(this, SingleLayoutHelperActivity.class));
                break;
            case R.id.onePlusNLayoutHelper:
                startActivity(new Intent(this, OnePlusNLayoutHelperActivity.class));
                break;
            case R.id.floatLayoutHelper:
                startActivity(new Intent(this, FloatLayoutHelperActivity.class));
                break;
            case R.id.stickyLayoutHelper:
                startActivity(new Intent(this, StickyLayoutHelperActivity.class));
                break;
            case R.id.all:
                startActivity(new Intent(this,AllActivity.class));
                break;
        }
    }


}
