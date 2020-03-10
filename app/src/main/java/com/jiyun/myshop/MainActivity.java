package com.jiyun.myshop;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.jiyun.myshop.ui.login.LoginActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getSupportActionBar().hide();//隐藏标题栏
//        mToolBar = findViewById(R.id.toolBar);
//        mToolBar.setTitle(R.string.title);
        //默认的样式里面有ActionBar,需要使用toolbar代替
        //需要在样式中设置为<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
//        setSupportActionBar(mToolBar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //tab大于3需要设置允许3个以上
        navView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_cart,R.id.navigation_own)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        //login();
    }

    private void login() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //添加菜单2种方式
        //groupid,菜单组的id
        //itemid,菜单的id
        //order,排序,数字越小排位靠上
        //title,菜单标题
        //添加方式1
        /*menu.add(0,0,1,"删除");
        menu.add(0,1,0,"添加");*/
        //添加方式2
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //2.菜单的点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                showToast(item.getTitle());
                break;
            case R.id.delete:
                showToast(item.getTitle());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
