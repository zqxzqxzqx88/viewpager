package com.example.viewpagerdemo;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
  List<Fragment> mViewList;
  ImageView mCousor;
  int mBmpw = 0;
  int mOffset = 0;
  private int mCurrIndex = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ViewPager pager = findViewById(R.id.viewpager);
    // 继承PagerAdapter
//    TextView view1 = new TextView(this);
//    view1.setText("1");
//    TextView view2 = new TextView(this);
//    view2.setText("2");
//    TextView view3 = new TextView(this);
//    view3.setText("3");
//    mViewList = new ArrayList<>();
//    mViewList.add(view1);
//    mViewList.add(view2);
//    mViewList.add(view3);
//    pager.setAdapter(new FragmentAdapter(mViewList));
    // 继承FragmentPagerAdapter
    mViewList = new ArrayList<>();
    mViewList.add(new FirstFragment());
    mViewList.add(new SecondFragment());
    mViewList.add(new ThirdFragment());
    pager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), mViewList));
    initCursorPos();
    pager.addOnPageChangeListener(new MyPageChangeListener());
  }

  //初始化指示器位置
  public void initCursorPos() {
    mCousor = findViewById(R.id.cursor);
    mBmpw = getBitmap(R.drawable.cursor).getWidth();

    DisplayMetrics metrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(metrics);
    int screenW = metrics.widthPixels;
    mOffset = (screenW / mViewList.size() - mBmpw) / 2;

    Matrix matrix = new Matrix();
    matrix.postTranslate(mOffset, 0);
    mCousor.setImageMatrix(matrix);
  }

  public Bitmap getBitmap(int vectorDrawableId) {
    final VectorDrawableCompat
        drawable = VectorDrawableCompat.create(getResources(), vectorDrawableId, null);
    if (drawable == null) {
      return null;
    }
    Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
        drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
    drawable.draw(canvas);
    return bitmap;
  }


  //页面改变监听器
  public class MyPageChangeListener implements ViewPager.OnPageChangeListener {

    int one = mOffset * 2 + mBmpw;// 页卡1 -> 页卡2 偏移量
    int two = one * 2;// 页卡1 -> 页卡3 偏移量

    @Override
    public void onPageSelected(int arg0) {
      Animation animation = null;
      switch (arg0) {
        case 0:
          if (mCurrIndex == 1) {
            animation = new TranslateAnimation(one, 0, 0, 0);
          } else if (mCurrIndex == 2) {
            animation = new TranslateAnimation(two, 0, 0, 0);
          }
          break;
        case 1:
          if (mCurrIndex == 0) {
            animation = new TranslateAnimation(mOffset, one, 0, 0);
          } else if (mCurrIndex == 2) {
            animation = new TranslateAnimation(two, one, 0, 0);
          }
          break;
        case 2:
          if (mCurrIndex == 0) {
            animation = new TranslateAnimation(mOffset, two, 0, 0);
          } else if (mCurrIndex == 1) {
            animation = new TranslateAnimation(one, two, 0, 0);
          }
          break;
      }
      mCurrIndex = arg0;
      animation.setFillAfter(true);// True:图片停在动画结束位置
      animation.setDuration(300);
      mCousor.startAnimation(animation);
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }
  }
}