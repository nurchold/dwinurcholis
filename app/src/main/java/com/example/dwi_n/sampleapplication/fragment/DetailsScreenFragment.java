package com.example.dwi_n.sampleapplication.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dwi_n.sampleapplication.Adapter.ViewPagerAdapter;
import com.example.dwi_n.sampleapplication.CommonClass.Constant;
import com.example.dwi_n.sampleapplication.Model.CatDetails;
import com.example.dwi_n.sampleapplication.R;

/**
 * This class handle ui & controller : mapping data image list and dot indicator.
 */
public class DetailsScreenFragment extends CustomFragment {

    //Content view variable.
    private LinearLayout mPhotoIndicator;
    private ViewPager mViewPager;

    //Data variable.
    private int dotCount;
    private ImageView [] dota;

    @Override
    protected int getTitleScreen() {
        return R.string.title_screen;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Bullet indicator.
        mPhotoIndicator = view.findViewById(R.id.photo_indicator);
        //Image pager.
        mViewPager = view.findViewById(R.id.view_pager);

        CatDetails catDetails = getCatDetailsData();

        if (catDetails != null) {
            setAdapterViewDetails(catDetails);
            collectDotInList(catDetails);
            addViewPagerListener();
        }
    }

    /**
     * Get Cat details from bundle.
     * @return - cat details obj.
     */
    private CatDetails getCatDetailsData() {
        Bundle bundle = getArguments();
        return bundle != null ? (CatDetails) bundle.getSerializable(Constant.IMAGE_DETAILS_DATA) : null;
    }

    /**
     * Add view pager listener.
     * Listen every view pager swipe.
     */
    private void addViewPagerListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Do nothing but should override
            }

            @Override
            public void onPageSelected(int position) {
                if (getActivity() != null) {
                    for (int i = 0; i < dotCount; i++) {
                        dota[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.default_dot));
                    }
                    dota[position].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.selected_dot));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Do nothing but should override
            }
        });
    }

    /**
     * Collect dot in list.
     * @param catDetails - cat details data.
     */
    private void collectDotInList(CatDetails catDetails) {
        dotCount = catDetails.getImageList().size();
        dota = new ImageView[dotCount];
        if (getActivity() != null) {
            for (int i = 0; i < dotCount; i++) {
                dota[i] = new ImageView(getActivity());
                dota[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.default_dot));
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                param.setMargins(8,0,8,0);

                mPhotoIndicator.addView(dota[i], param);
            }
            dota[0].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.selected_dot));
        }
    }

    /**
     * Set adapter for view pager.
     * @param catDetails - cat details data.
     */
    private void setAdapterViewDetails(CatDetails catDetails) {
        if (catDetails != null) {
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity(), catDetails);
            mViewPager.setAdapter(viewPagerAdapter);
        }
    }


}
