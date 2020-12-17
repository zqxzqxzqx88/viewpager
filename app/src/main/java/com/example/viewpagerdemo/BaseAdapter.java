package com.example.viewpagerdemo;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class BaseAdapter extends PagerAdapter {
  private List<View> mViewList;

  public BaseAdapter(List<View> viewList) {
    mViewList = viewList;
  }

  @Override
  public int getCount() {
    return mViewList.size();
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view == object;
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    container.addView(mViewList.get(position));
    return mViewList.get(position);
  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView(mViewList.get(position));
  }
}
