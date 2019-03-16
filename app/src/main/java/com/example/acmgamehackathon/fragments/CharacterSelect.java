package com.example.acmgamehackathon.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.acmgamehackathon.R;
import com.example.acmgamehackathon.menu.MenuItems;

import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PageItemClickListener;
import me.crosswall.lib.coverflow.core.PagerContainer;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterSelect extends Fragment {


    public CharacterSelect() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_character_select, container, false);

        PagerContainer mContainer = (PagerContainer) rootView.findViewById(R.id.pager_container);
        mContainer.setOverlapEnabled(true);

        final ViewPager pager = mContainer.getViewPager();

        PagerAdapter adapter = new MyPagerAdapter();
        pager.setAdapter(adapter);

        pager.setOffscreenPageLimit(adapter.getCount());

        pager.setClipChildren(false);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int i1) {
                pager.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(position == 0){
                            Toast.makeText(getContext(), "Deric", Toast.LENGTH_LONG).show();
                        }
                        else if(position == 1){
                            Toast.makeText(getContext(), "Deric", Toast.LENGTH_LONG).show();

                        }
                        else if(position == 2){
                            Toast.makeText(getContext(), "Deric", Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mContainer.setPageItemClickListener(new PageItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),"position:" + position,Toast.LENGTH_SHORT).show();
            }
        });


        boolean showRotate = getActivity().getIntent().getBooleanExtra("showRotate",true);

        if(showRotate){
            new CoverFlow.Builder()
                    .with(pager)
                    .scale(0.3f)
                    .pagerMargin(0f)
                    .spaceSize(0f)
                    .rotationY(25f)
                    .build();
        }
        return rootView;
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.character_item,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.character);
            imageView.setImageDrawable(getResources().getDrawable(MenuItems.items[position]));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return MenuItems.items.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }

}
