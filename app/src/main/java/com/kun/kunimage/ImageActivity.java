package com.kun.kunimage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import static com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.ZOOM_FOCUS_CENTER_IMMEDIATE;

/**
 * 作者：ZhaoYaKun on 2017/10/17.
 * 邮箱：13263181110@163.com
 */
public class ImageActivity extends AppCompatActivity {

    private static final String TAG = ImageActivity.class.getSimpleName();
    private SubsamplingScaleImageView mActionImage;
    private String[] strUrl = {
            "http://img2.imgtn.bdimg.com/it/u=634098145,264198475&fm=27&gp=0.jpg",
            "http://cache.attach.yuanobao.com/image/2016/10/24/332d6f3e63784695a50b782a38234bb7/da0f06f8358a4c95921c00acfd675b60.jpg",
            "http://short.im.rockhippo.cn/uploads/msg/201703/20170309/1485/1489068660846.jpg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/20171012073213_p4H630_joycechu_syc_12_10_2017_7_32_7_433.jpeg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/20171012073108_0y12KR_anri.kumaki_12_10_2017_7_30_58_141.jpeg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/20171011084856_0YQ0jN_joanne_722_11_10_2017_8_39_5_505.jpeg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/2017-10-10-sakura.gun_10_10_2017_12_33_34_751.jpg",
            "https://ws1.sinaimg.cn/large/610dc034ly1fjxu5qqdjoj20qo0xc0wk.jpg",
            "https://ws1.sinaimg.cn/large/610dc034ly1fk05lf9f4cj20u011h423.jpg",
            "https://ws1.sinaimg.cn/large/610dc034ly1fjs25xfq48j20u00mhtb6.jpg",
            "https://ws1.sinaimg.cn/large/610dc034ly1fjqw4n86lhj20u00u01kx.jpg",
            "https://ws1.sinaimg.cn/large/610dc034ly1fjppsiclufj20u011igo5.jpg",
            "https://ws1.sinaimg.cn/large/610dc034ly1fjndz4dh39j20u00u0ada.jpg",
            "https://ws1.sinaimg.cn/large/610dc034gy1fi502l3eqjj20u00hz41j.jpg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-21-17268102_763630507137257_3620762734536163328_n%20-1-.jpg",
    };
    private WebView mWebView;
    private ImageActivity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showAllVisual(true);
        setContentView(R.layout.activity_image);
        mContext = ImageActivity.this;

        initView();

        initData();

    }

    private void initData() {
        mActionImage.setMaxScale(15);
        mActionImage.setZoomEnabled(true);
        mActionImage.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        Glide.with(mContext).load(strUrl[new Random().nextInt(strUrl.length)]).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
// 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
                Bitmap bitmap = drawableToBitmap(resource);
                int sWidth = bitmap.getWidth();
                int sHeight = bitmap.getHeight();
                WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                int height = wm.getDefaultDisplay().getHeight();
                if (sHeight >= height && sHeight / sWidth >= 3) {
                    mActionImage.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
                    mActionImage.setImage(ImageSource.bitmap(bitmap), new ImageViewState(2.0F, new PointF(0, 0), 0));
                } else {
                    mActionImage.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
                    mActionImage.setImage(ImageSource.bitmap(bitmap));
                    mActionImage.setDoubleTapZoomStyle(ZOOM_FOCUS_CENTER_IMMEDIATE);
                }
            }
        });
        mActionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(mContext).load(strUrl[new Random().nextInt(strUrl.length)]).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
// 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
                        Bitmap bitmap = drawableToBitmap(resource);
                        int sWidth = bitmap.getWidth();
                        int sHeight = bitmap.getHeight();
                        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                        int height = wm.getDefaultDisplay().getHeight();
                        if (sHeight >= height && sHeight / sWidth >= 3) {
                            mActionImage.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
                            mActionImage.setImage(ImageSource.bitmap(bitmap), new ImageViewState(2.0F, new PointF(0, 0), 0));
                        } else {
                            mActionImage.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
                            mActionImage.setImage(ImageSource.bitmap(bitmap));
                            mActionImage.setDoubleTapZoomStyle(ZOOM_FOCUS_CENTER_IMMEDIATE);
                        }
                    }
                });
            }
        });
//        startJavascript(mWebView);
//        mWebView.loadUrl(strUrl[new Random().nextInt(strUrl.length)]);
    }

    /*Java代码  将Drawable转化为Bitmap */
    private Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        //canvas.drawColor(0xff33B5E5);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);

        return bitmap;
    }

    private void initView() {
        mActionImage = findViewById(R.id.action_image);
        mWebView = findViewById(R.id.wv_view);
    }

    private void startJavascript(WebView webView) {
        WebSettings mWebSettings = webView.getSettings();
        webView.setHorizontalScrollBarEnabled(false);//水平不显示
        webView.setVerticalScrollBarEnabled(false); //垂直不显示
        mWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        mWebSettings.setBlockNetworkImage(false); // 是否阻止网络图像
        mWebSettings.setBlockNetworkLoads(false); // 是否阻止网络请求
        mWebSettings.setJavaScriptEnabled(true); // 是否加载JS
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebSettings.setAppCacheEnabled(false);
        mWebSettings.setUseWideViewPort(true); // 使用广泛视窗
        mWebSettings.setLoadWithOverviewMode(false);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setBuiltInZoomControls(false);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mWebSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        mWebSettings.setUserAgentString(mWebSettings.getUserAgentString()
                + ";native-android");// 获得浏览器的环境
        //webview在安卓5.0之前默认允许其加载混合网络协议内容
// 在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    public void showAllVisual(boolean b) {
        if (b) {
            //取消标题栏
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            //取消状态栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            hideBottomUIMenu();
        }
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

}
