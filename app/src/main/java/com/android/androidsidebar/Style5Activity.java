package com.android.androidsidebar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nineoldandroids.view.ViewHelper;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * ================================================
 * 项    目：AndroidSidebar
 * 作    者：zj
 * 版    本：1.0
 * 创建日期：2019/4/21
 * 描    述：侧边栏样式五（仿QQ 8.0.0）
 * 修订历史：
 * ================================================
 */
public class Style5Activity extends AppCompatActivity {
    private final String TAG = "Style5Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏时，获取状态栏高度
        int statusBarHeight = ScreenInfoUtils.getStatusBarHeight(this);
        ScreenInfoUtils.fullScreen(this);

        setContentView(R.layout.activity_style5);

        //初始化状态栏的高度
        View statusbar = (View) findViewById(R.id.view_statusbar);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(MATCH_PARENT, statusBarHeight);
        statusbar.setLayoutParams(params);

        //初始化UI控件
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView ivClose = (ImageView) findViewById(R.id.iv_close_menu);
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        View leftMenu = findViewById(R.id.menu_frame);

        //获取侧边栏默认宽度
        ViewGroup.LayoutParams leftParams = leftMenu.getLayoutParams();
        //获取屏幕宽度
        final int width = ScreenInfoUtils.getWindowWidth(this);
        //获取屏幕高度
        final int height = ScreenInfoUtils.getFullActivityHeight(this);
        //设置侧边的宽高(如果不重新设置，即时设置match_parent也只能占屏幕百分80)
        leftParams.width = width;
        leftParams.height = FrameLayout.LayoutParams.MATCH_PARENT;
        leftMenu.setLayoutParams(leftParams);

        //将ToolBar与ActionBar关联
        setSupportActionBar(toolbar);
        //另外openDrawerContentDescRes 打开图片   closeDrawerContentDescRes 关闭图片
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, 0, 0);
        //初始化状态
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //关闭侧滑栏按钮
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        //设置背景蒙层颜色
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
                //侧边栏状态
            }

            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //获取主页内容view
                View mContent = drawerLayout.getChildAt(0);
                //获取侧边栏内容 view
                View mMenu = drawerView;
                //主页面移动
                ViewHelper.setTranslationX(mContent, width * slideOffset);

                Log.d(TAG, "mMenu width:" + mMenu.getMeasuredWidth() + " : " + "window width" + width);
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
