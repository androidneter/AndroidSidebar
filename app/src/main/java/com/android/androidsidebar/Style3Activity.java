package com.android.androidsidebar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * ================================================
 * 项    目：AndroidSidebar
 * 作    者：zj
 * 版    本：1.0
 * 创建日期：2019/4/21
 * 描    述：侧边栏样式三  IOS风格侧滑
 * 修订历史：
 * ================================================
 */
public class Style3Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏时，获取状态栏高度
        int statusBarHeight = ScreenInfoUtils.getStatusBarHeight(this);
        //隐藏状态栏
        ScreenInfoUtils.fullScreen(this);
        //初始化布局
        setContentView(R.layout.activity_style3);
        //初始化状态栏的高度
        View statusbar = (View) findViewById(R.id.view_statusbar);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
        statusbar.setLayoutParams(params);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);//将toolbar与ActionBar关联
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);//初始化状态
        toggle.syncState();

        //蒙层颜色
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorGray));
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
            }

            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                View mContent = drawerLayout.getChildAt(0);
                View mMenu = drawerView;
                ViewHelper.setTranslationX(mContent, mMenu.getMeasuredWidth() * slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
            }
        });
    }
}
