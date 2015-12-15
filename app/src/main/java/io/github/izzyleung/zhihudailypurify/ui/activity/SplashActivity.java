package io.github.izzyleung.zhihudailypurify.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.Random;

import io.github.izzyleung.zhihudailypurify.R;

public class SplashActivity extends Activity {
    private ImageView mImageview;
    private Animation mFadeIn;// 渐显动画
    private Animation mFadeInScale;// 放大动画
    private Animation mFadeOut;// 渐隐动画
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        int index = new Random().nextInt(2);
        if (index == 1) {
            mImageview.setImageResource(R.drawable.qilixiang);
        } else {
            mImageview.setImageResource(R.drawable.qilixiang);
        }

        initAnim();
        setListener();
       /* try {
            String imgInfo=Http.get("http://news-at.zhihu.com/api/4/start-image/720*1184");

            *//**
             * {"text":"","img":"http:\/\/pic1.zhimg.com\/e2ced47bc45d83b5ee20e74dabdedbc0.jpg"}
             *//*
//            GsonBuilder().create().fromJson(cursor.getString(2), Constants.Type.newsListType);
            if(!imgInfo.isEmpty()){
            List<DailyImage> dailyImage=new GsonBuilder().create().fromJson(imgInfo, Constants.Type.imgsListType);
            if (dailyImage.size()>0){
                String img_uri=dailyImage.get(0).getImg();
                Log.d("img","Img url = "+dailyImage.get(0).getImg());
                loadImageVolley(img_uri);
            }}else{
                Log.d("img", "ERROR!!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
//        loadImageVolley("http://p2.zhimg.com/10/7b/107bb4894b46d75a892da6fa80ef504a.jpg");
    }

    private void initView() {
        mImageview = (ImageView) findViewById(R.id.image);

    }
    /**
     * 初始化 动画效果
     */
    private void initAnim() {
        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in);
        mFadeIn.setDuration(300);
        mFadeInScale = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in_scale);
        mFadeInScale.setDuration(1000);
        mFadeOut = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_out);
        mFadeOut.setDuration(300);
        mImageview.startAnimation(mFadeIn);
    }
    /**
     * 设置动画效果 监听事件
     */
    public void setListener() {
        /**
         * 动画切换原理:开始时是用第一个渐现动画,当第一个动画结束时开始第二个放大动画,当第二个动画结束时调用第三个渐隐动画,
         * 第三个动画结束时修改显示的内容并且重新调用第一个动画,从而达到循环效果
         */
        mFadeIn.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                mImageview.startAnimation(mFadeInScale);
            }
        });
        mFadeInScale.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                // 跳转Activity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                // mImageView.startAnimation(mFadeOut);
            }
        });
        mFadeOut.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
//				 startActivity(MenuActivity.class);
            }
        });
    }
    public void loadImageVolley(String imageurl) {
//        String imageurl = "http://10.0.0.52/lesson-img.png";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final LruCache<String, Bitmap> lurcache = new LruCache<String, Bitmap>(
                20);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {

            @Override
            public void putBitmap(String key, Bitmap value) {
                lurcache.put(key, value);
            }

            @Override
            public Bitmap getBitmap(String key) {

                return lurcache.get(key);
            }
        };
        ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
        ImageLoader.ImageListener listener = imageLoader.getImageListener(mImageview,
                R.drawable.qilixiang, R.drawable.qilixiang);
        imageLoader.get(imageurl, listener);
    }
}