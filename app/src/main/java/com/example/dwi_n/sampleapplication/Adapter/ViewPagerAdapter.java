package com.example.dwi_n.sampleapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dwi_n.sampleapplication.Model.CatDetails;
import com.example.dwi_n.sampleapplication.R;
import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private CatDetails catDetails;

    public ViewPagerAdapter(Context context, CatDetails catDetails) {
        this.context = context;
        this.catDetails = catDetails;
    }

    @Override
    public int getCount() {
        return catDetails.getImageList().size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        String imageUrl = catDetails.getImageList().get(position);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.image_pager, collection, false);
        ImageView pagerImage = layout.findViewById(R.id.pagerImage);
        Picasso.with(context).load(imageUrl).fit().into(pagerImage);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
