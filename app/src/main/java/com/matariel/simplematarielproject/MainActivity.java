package com.matariel.simplematarielproject;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.matariel.simplematarielproject.flabbyview.FlabbyActivity;
import com.matariel.simplematarielproject.multirecycleview.BBaseBean;
import com.matariel.simplematarielproject.multirecycleview.MaterialItemViewType;
import com.matariel.simplematarielproject.nice_spinner.NiceSpinnerActivity;
import com.matariel.simplematarielproject.parallax.ParallaxActivity;
import com.matariel.simplematarielproject.pulltozoom.PullToZoomActivity;
import com.matariel.simplematarielproject.stickyheader.StickyHeaderActivity;
import com.matariel.simplematarielproject.swipedismissrecyclerview.SwipeDismissRecyclerActivity;
import com.matariel.simplematarielproject.swipemenu.SimpleActivity;
import com.matariel.simplematarielproject.swipemenu.SwipemenuActivity;
import com.multilsupport.recycleview.BaseBean;
import com.multilsupport.recycleview.CommonViewHolder;
import com.multilsupport.recycleview.MultiItemCommonAdapter;
import com.multilsupport.recycleview.MultiItemTypeSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected View getTitleView() {
        return View.inflate(this, R.layout.layout_title_base, null);
    }

    @Override
    protected View getRootView() {
        return View.inflate(this, R.layout.activity_main, null);
    }

    @Override
    protected void initToolBar() {
        TextView tv_title = getView(R.id.tv_title);
        tv_title.setText("MatarielDemos");
    }

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.materialdesign_recycle);
    }

    @Override
    protected void initData() {
        mRecyclerView = (RecyclerView) findViewById(R.id.materialdesign_recycle);


        List<BaseBean> mDatas = new ArrayList<>();
        BBaseBean bBaseBean1 = new BBaseBean();
        bBaseBean1.setFlag(1);
        bBaseBean1.setName("滑动删除的recycleview");
        mDatas.add(bBaseBean1);

        BBaseBean bBaseBean2 = new BBaseBean();
        bBaseBean2.setFlag(2);
        bBaseBean2.setName("FlabbyView");
        mDatas.add(bBaseBean2);

        BBaseBean bBaseBean3 = new BBaseBean();
        bBaseBean3.setFlag(3);
        bBaseBean3.setName("带头部的recycleview");
        mDatas.add(bBaseBean3);

        BBaseBean bBaseBean4 = new BBaseBean();
        bBaseBean4.setFlag(4);
        bBaseBean4.setName("NiceSpinner");
        mDatas.add(bBaseBean4);

        BBaseBean bBaseBean5 = new BBaseBean();
        bBaseBean5.setFlag(5);
        bBaseBean5.setName("ParallaxScollListView");
        mDatas.add(bBaseBean5);

        BBaseBean bBaseBean6 = new BBaseBean();
        bBaseBean6.setFlag(6);
        bBaseBean6.setName("PullToZoomVIew");
        mDatas.add(bBaseBean6);

        BBaseBean bBaseBean7 = new BBaseBean();
        bBaseBean7.setFlag(7);
        bBaseBean7.setName("listview左右滑动弹出meun");
        mDatas.add(bBaseBean7);


        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        mRecyclerView.setLayoutManager(layoutmanager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(new MultiItemCommonAdapter<BaseBean>(this, mDatas, new MultiItemTypeSupport() {
            @Override
            public int getLayoutId(int itemType) {
                int layoutId = -1;
                switch (itemType){
                    case MaterialItemViewType.BASE:
                        layoutId =  R.layout.item_base;
                        break;
                }
                return layoutId;
            }

            @Override
            public int getItemViewType(int position, Object o) {
                return MaterialItemViewType.BASE;
            }
        }) {
            @Override
            public void convert(CommonViewHolder holder, BaseBean baseBean) {
                //填充
                TextView type;
                TextView mame;
                switch (baseBean.getItemViewType()){
                    case MaterialItemViewType.BASE:
                        final BBaseBean bbaseBean = (BBaseBean) baseBean;
                        mame = holder.getView(R.id.id_item_materialname);
                        mame.setText(bbaseBean.getName());
                        type = holder.getView(R.id.id_item_materialtype);
                        type.setText(String.valueOf(bbaseBean.getItemViewType()));
                        holder.setOnClickListener(R.id.root, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(bbaseBean.getFlag() == 1){
                                    startActivity(SwipeDismissRecyclerActivity.class);
                                }else if(bbaseBean.getFlag() == 2){
                                    startActivity(FlabbyActivity.class);
                                }else if(bbaseBean.getFlag() == 3){
                                    startActivity(StickyHeaderActivity.class);
                                }else if(bbaseBean.getFlag() == 4){
                                    startActivity(NiceSpinnerActivity.class);
                                }else if(bbaseBean.getFlag() == 5){
                                    startActivity(ParallaxActivity.class);
                                }else if(bbaseBean.getFlag() == 6){
                                    startActivity(PullToZoomActivity.class);
                                }else if(bbaseBean.getFlag() == 7){
                                    startActivity(SwipemenuActivity.class);
                                }

                            }
                        });
                        break;
                }
            }
        });
    }

    @Override
    protected void initListener() {

    }
}
