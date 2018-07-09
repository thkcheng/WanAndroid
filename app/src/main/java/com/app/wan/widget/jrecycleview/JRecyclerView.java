package com.app.wan.widget.jrecycleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.app.wan.R;

/**
 * Created by thkcheng on 2018/7/9.
 *
 * https://github.com/JackZhous/RecylerViewPaer
 *
 */
public class JRecyclerView extends RecyclerView{

    private static final String TAG = "JRecyclerView";

    private JAdapter adapter;
    private Context context;
    private int header_layout;                          //头部布局文件
    private int body_layout;                            //胸部布局文件
    private LayoutManager manager;
    private GridLayoutManager default_manager;          //默认布局是表格布局
    private int grid_count = 2;                         //表格布局列数

    private JViewHolder viewHolder;


    public JRecyclerView(Context context){

        super(context);
        this.context = context;

        initDefult(null);

    }

    public JRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        initDefult(attrs);

    }

    public JRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        this.context = context;

        initDefult(attrs);
    }



    /**
     * 初始化默认数据
     */
    public void initDefult(AttributeSet attrs){
        if(null != attrs){
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.recycler);
            header_layout = array.getResourceId(R.styleable.recycler_head, 0);
            body_layout = array.getResourceId(R.styleable.recycler_body, 0);
            array.recycle();
        }

    }

    /**
     * 初始化适配器
     */
    public void initAdapter(){

        default_manager = new GridLayoutManager(context, grid_count, LinearLayoutManager.VERTICAL, false);
        default_manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (0 == position) {
                    return default_manager.getSpanCount();
                }
                return 1;
            }
        });

        adapter = new JAdapter(viewHolder, header_layout, body_layout);

    }


    public void setViewHolder(JViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }


    public void setManager(RecyclerView.LayoutManager manager) {
        this.manager = manager;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setGrid_count(int grid_count) {
        this.grid_count = grid_count;
    }

    /**
     * 设置头部布局文件索引
     * @param header_layout
     */
    public void setHeader_layout(int header_layout) {
        this.header_layout = header_layout;
    }

    /**
     * 设置胸部布局文件索引
     * @param body_layout
     */
    public void setBody_layout(int body_layout) {
        this.body_layout = body_layout;
    }

    /**
     * 设置其recycleview并开始显示
     */
    public void startToShow(){
        initAdapter();

        if(null == manager){
            this.setLayoutManager(default_manager);
        }else{
            this.setLayoutManager(manager);
        }

        setAdapter(adapter);
    }

}
