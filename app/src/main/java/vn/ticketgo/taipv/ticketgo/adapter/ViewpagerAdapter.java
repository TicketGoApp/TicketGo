package vn.ticketgo.taipv.ticketgo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.ticketgo.taipv.ticketgo.model.Fragments;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/11/2018
 * Email: tai97nd@gmail.com
 */

public class ViewpagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragments> fragmentses;

    public ViewpagerAdapter(FragmentManager fm, List<Fragments> fragmentses) {
        super(fm);

        this.fragmentses = fragmentses;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentses.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragmentses.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (fragmentses.get(position).getTitle() == null)
            return super.getPageTitle(position);
        else
            return fragmentses.get(position).getTitle();
    }
}

