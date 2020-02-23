package com.ashehata.handydoctor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ashehata.handydoctor.R;
import com.ashehata.handydoctor.helper.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater mLayoutInflater;

    private List<Integer> images = new ArrayList<>();

    public SliderAdapter(Context context) {
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addPage(int drawable) {
        images.add(drawable);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // Your custom view
        View itemView = mLayoutInflater.inflate(R.layout.custom_slider, container, false);

        //ButterKnife.bind(this, itemView);
        CircleImageView circleImageView = (CircleImageView) itemView.findViewById(R.id.image);
        HelperMethod.onLoadImage(circleImageView,context,images.get(position));
        ViewPager viewPager = (ViewPager) container ;
        viewPager.addView(itemView,0);

        return itemView;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
