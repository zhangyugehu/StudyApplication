package com.thssh.okhttpexample;

import android.content.Context;
import android.support.annotation.Nullable;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by zhang on 2017/5/4.
 */

public class OkHttpConfig {

    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;

    public void config(Context context) {

    }

    /**
     * /**
     * Created by zhang on 2017/5/4.
     * 1. 开启响应数据缓存到文件系统功能
     * 允许缓存响应数据时需要往请求头里加入Cache-Control，默认情况下OkHttp不会缓存响应数据。
     * 所以，客户端就会浪费时间和流量去多次请求同样的数据。
     * 相反的，如果缓存了响应数据，只需要在第一次请求的时候从网络获取，以后就可以直接从缓存文件中获取数据。
     * <p>
     * 为了开启缓存响应数据功能，你需要创建一个com.squareup.okhttp.Cache对象并将其作为参数传给OkHttpClient的setCache方法。
     * 在创建Cache对象的时候，你必须为其指定一个File参数和以byte为单位的最大容量参数，这个File代表了缓存的路径。
     * 缓存的数据会被存储在这个路径中。当缓存的大小超过指定的最大容量时，OkHttp会根据LRU算法对缓存数据进行清理操作。
     * <p>
     * 根据Jesse Wilson的推荐，我们把缓存的数据存放在context.getCacheDir()的子目录中:
     *
     * @param okHttpClient
     * @param context
     */
    public void cache(Context context, OkHttpClient okHttpClient) {
        // Base directory recommended by http://stackoverflow.com/a/32752861/400717.
        // Guard against null, which is possible according to
        // https://groups.google.com/d/msg/android-developers/-694j87eXVU/YYs4b6kextwJ and
        // http://stackoverflow.com/q/4441849/400717.
        try{
            final File baseDir = context.getCacheDir();
            if (baseDir != null) {
                final File cacheDir = new File(baseDir, "HttpResponseCache");
                okHttpClient.setCache((new Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE)));
            }
        }catch (Exception e){

        }
    }

    /**
     * 2. 集成Stetho
     * Stetho是由Facebook开发的一个实用的库，它可以让你使用Chrome提供的Chrome Developer Tools来审查你的Android应用的代码。
     * <p>
     * 除了可以让你审查应用中的SQLite数据库和View的继承层次外，Stetho也可以审查OkHttp发起的每一个请求和收到的每一个响应:
     * 这个拦截器可以确保服务端返回的HTTP头允许缓存相关数据，而且也可以保证如果有缓存数据的话不会进行网络请求。
     * <p>
     * 使用Stetho也很简单，往网络拦截器列表中添加一个StethoInterceptor对象就可以了:
     * 然后运行你的应用并且打开Chrome，导航到chrome://inspect。
     * 这时应该就会有一个设备和应用id的列表。点击’inspect’链接来打开Developer Tools，然后打开NetWork标签就可以观察OkHttp的请求了。
     */
    public void stetho(OkHttpClient okHttpClient) {
        okHttpClient.networkInterceptors().add(new StethoInterceptor());
    }

    public void picasso(Context context, OkHttpClient okHttpClient){
        final Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttpDownloader(okHttpClient))
                .build();

        // The client should inject this instance whenever it is needed, but replace the singleton
        // instance just in case.
        Picasso.setSingletonInstance(picasso);
    }

    public void retrofit(){

    }
}
