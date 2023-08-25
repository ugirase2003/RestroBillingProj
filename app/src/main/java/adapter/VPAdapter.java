package adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class VPAdapter extends FragmentStateAdapter {
    private ArrayList<Fragment> fragments; //variable holds the fragments the ViewPager2 allows us to swipe to.

    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

   //we are setting data to fragments arraylist so it will populate fragment by fragment in viewpager
    public void setData(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }
}