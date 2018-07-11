package com.app.wan.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.app.wan.R;
import com.app.wan.base.BaseRecyclerAdapter;
import com.app.wan.model.WanHomeBean;
import com.app.wan.ui.activity.ParticularsActivity;
import com.app.wan.util.TimeUtil;

import java.util.List;

/**
 * Created by thkcheng on 2018/7/5.
 */
public class HomeRecommendAdapter extends BaseRecyclerAdapter<WanHomeBean.DataBean.DatasBean> {

    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    public HomeRecommendAdapter(Context context, List<WanHomeBean.DataBean.DatasBean> beans) {
        super(context, beans);
    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_home_recommend_layout;
    }

    @Override
    protected void onItemClick(int position, View item) {
        Intent intent = new Intent(mContext, ParticularsActivity.class);
        intent.putExtra("link", mBeans.get(position).getLink());
        mContext.startActivity(intent);
    }

    @Override
    public void onBindData(RecyclerViewHolder holder, WanHomeBean.DataBean.DatasBean bean, int position) {
        holder.setText(R.id.tvTitle, bean.getTitle());
        holder.setText(R.id.tvTime, TimeUtil.getPublishTime(bean.getPublishTime()));
        holder.setText(R.id.tvChapterName, bean.getChapterName());
        holder.setText(R.id.tvAuthor, bean.getAuthor());
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
