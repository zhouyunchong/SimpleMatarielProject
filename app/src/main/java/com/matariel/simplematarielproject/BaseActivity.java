package com.matariel.simplematarielproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * 基类（模板方法设计模式）
 * Created by Q on 2018/3/1.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private ViewFlipper mContentView, mTitleRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化布局
        setContentView(R.layout.layout_base);
        mContentView = getView(R.id.layout_container);
        mTitleRoot = getView(R.id.title_root);
        toolbar = getView(R.id.base_tool_bar);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);

        View titleView = getTitleView();
        if(titleView != null){
            mTitleRoot.addView(titleView, lp);
        }
        mContentView.addView(getRootView(), lp);
        //初始化基类数据
        initBaseData();
        //初始化Toolbar
        initToolBar();
        //初始化控件
        initView();
        //初始化数据
        initData();
        //初始化监听
        initListener();
    }

    /**初始化基类数据*/
    private void initBaseData(){}{

    }
    protected abstract View getTitleView();
    /**初始化布局*/
    protected abstract View getRootView();
    /**初始化Toolbar*/
    protected abstract void initToolBar();
    /**初始化控件*/
    protected abstract void initView();
    /**初始化数据*/
    protected abstract void initData();
    /**初始化点击事件*/
    protected abstract void initListener();

    @Override
    public void onClick(View view) {

    }

    protected <T extends View>T getView(int resourcesId){
        return (T) findViewById(resourcesId);
    }

    /**
     * toolbar设置menu资源
     * @param resId
     */
    protected void inflateMenu(@MenuRes int resId, Toolbar.OnMenuItemClickListener listener){
        mTitleRoot.setVisibility(View.GONE);
        toolbar.inflateMenu(resId);
        toolbar.setOnMenuItemClickListener(listener);
    }

    /**
     * 启动Activity
     * @param clazz
     */
    protected void startActivity(Class<? extends Activity> clazz){
        Intent in = new Intent(this,clazz);
        startActivity(in);
    }
}
