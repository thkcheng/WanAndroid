package com.app.wan.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.app.wan.R;
import com.app.wan.base.BaseRecyclerAdapter;
import com.app.wan.model.WanHomeBean;
import com.app.wan.model.WanSystemBean;
import com.app.wan.ui.activity.SystemChildrenActivitty;

import java.util.List;

/**
 * Created by thkcheng on 2018/7/6.
 */
public class SystemListAdapter extends BaseRecyclerAdapter<WanSystemBean.DataBean> {

    public SystemListAdapter(Context context, List<WanSystemBean.DataBean> beans) {
        super(context, beans);
    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_system_list_layout;
    }

    @Override
    protected void onItemClick(int position, View item) {
        super.onItemClick(position, item);
        mContext.startActivity(new Intent(mContext, SystemChildrenActivitty.class));
    }

    @Override
    public void onBindData(RecyclerViewHolder holder, WanSystemBean.DataBean bean, int position) {
        holder.setText(R.id.tvName, bean.getName());
        String childrenName = "";
        for (int i = 0; i < bean.getChildren().size(); i++) {
            childrenName += bean.getChildren().get(i).getName() + "  ";
        }
        holder.setText(R.id.tvChildrenName, childrenName);
    }
}
