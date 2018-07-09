package com.app.wan.widget.jrecycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by thkcheng on 2018/7/9.
 */

public class JAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0;                           //头部
    public static final int TYPE_CONTENT = 1;                          //胸部
    private static final String TAG = "JAdapter";

    private int header_layout;
    private int body_layout;
    private JViewHolder viewHolder;


    public JAdapter(JViewHolder viewHolder, int header, int bodyer) {
        this.header_layout = header;
        this.body_layout = bodyer;
        this.viewHolder = viewHolder;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(header_layout, null);
            viewHolder.findHead(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(body_layout, null);
            viewHolder.findBody(view);
        }

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_CONTENT) {                                        //说明是胸部
            position = position - 1;                                                            //头部占用了一个位置，所以胸部必须减掉头部的1
            viewHolder.setBody(position);
        } else {
            viewHolder.setHead();
        }
    }

    @Override
    public int getItemCount() {
        return viewHolder.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_CONTENT;
    }


    private class Holder extends RecyclerView.ViewHolder{
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
