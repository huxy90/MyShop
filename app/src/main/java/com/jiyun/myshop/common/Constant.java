package com.jiyun.myshop.common;

import com.jiyun.myshop.apps.MyApp;

import java.io.File;

public class Constant {

    public static final String PATH_DATA = MyApp.myApp.getCacheDir().getAbsolutePath()+ File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + File.separator+"shopapp";

    //商城的基础地址
    public static final String BASE_SHOP_URL = "https://cdwan.cn/api/";
}
