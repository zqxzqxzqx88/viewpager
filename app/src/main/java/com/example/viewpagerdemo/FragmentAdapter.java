package com.example.viewpagerdemo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
  List<Fragment> mFragments;

  public FragmentAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
    super(fm);
    mFragments = fragmentList;
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    return mFragments.get(position);
  }

  @Override
  public int getCount() {
    return mFragments.size();
  }
}
