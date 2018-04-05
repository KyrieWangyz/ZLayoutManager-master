package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mcxtzhang.flowlayoutmanager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/19 0019.
 */

public class SecondmainVipFragment extends Fragment implements View.OnClickListener{
    SeconfvipFragment seconfFragment;
    SecondFragment appointmentFragment;
    private ImageView m1;
    private ImageView m2;




    private FragmentPagerAdapter mAdapter;
    private RelativeLayout first;
    private  RelativeLayout second;
    private ViewPager mPage;
    private List<Fragment> mFragments;
    private View view;


    private TrainPresenter coachPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_secondmain, container, false);

        init();


        mAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mPage.setAdapter(mAdapter);
        mPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                switch (position){
                    case 0:
                        m2.setVisibility(View.GONE);
                        m1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        m1.setVisibility(View.GONE);
                        m2.setVisibility(View.VISIBLE);
                        break;

                }
            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }

    private void init() {
        first= (RelativeLayout) view.findViewById(R.id.recommand_layout);
        second= (RelativeLayout) view.findViewById(R.id.search_layout);
        first.setOnClickListener(this);
        second.setOnClickListener(this);
        m1= (ImageView) view.findViewById(R.id.recommand_picture);

        m2= (ImageView) view.findViewById(R.id.search_picture);

        mPage= (ViewPager) view.findViewById(R.id.viewpager);
        mFragments = new ArrayList<Fragment>();
        seconfFragment=new SeconfvipFragment();
        appointmentFragment=new SecondFragment();
        mFragments.add(seconfFragment);
        mFragments.add(appointmentFragment);

//        getExpressFragment = ThirdFragment.newInstance();
//        historyFragment = ThirdFragment.newInstance();
//
//        TrainTask trainTask=TrainTask.getINSTANCE();
//        coachPresenter=new TrainPresenter(getExpressFragment,trainTask);
//        getExpressFragment.setPresenter(coachPresenter);
//
//        coachPresenter=new TrainPresenter(historyFragment,trainTask);
//        historyFragment.setPresenter(coachPresenter);
//
//        mFragments.add(historyFragment);
//        mFragments.add(getExpressFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.recommand_layout:
                mPage.setCurrentItem(0);
                break;
            case R.id.search_layout:
                mPage.setCurrentItem(1);
                break;
        }
    }
}
