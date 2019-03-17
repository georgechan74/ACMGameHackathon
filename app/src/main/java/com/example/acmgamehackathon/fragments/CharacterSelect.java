package com.example.acmgamehackathon.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.acmgamehackathon.R;
import com.example.acmgamehackathon.menu.Character;
import com.example.acmgamehackathon.menu.MenuItems;

import java.util.ArrayList;
import java.util.Arrays;

import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PageItemClickListener;
import me.crosswall.lib.coverflow.core.PagerContainer;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterSelect extends Fragment {

    final ArrayList<Character> selectionArray = new ArrayList<Character>();
    final ArrayList<Character> tempArray = new ArrayList<Character>(Arrays.asList(MenuItems.items));

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
        public Object instantiateItem(ViewGroup container, final int position) {

            final View view = LayoutInflater.from(getActivity()).inflate(R.layout.character_item,null);

            ImageView imageView = (ImageView) view.findViewById(R.id.character);
            Log.e("THIS TEST", MenuItems.items[position].getFirstImage() + "");
            imageView.setImageDrawable(getResources().getDrawable(MenuItems.items[position].getFirstImage()));
            container.addView(view);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        if(selectionArray.size()!= 1){
                            selectionArray.add(tempArray.get(position));
                            ImageView imageView = (ImageView) view.findViewById(R.id.taken);
                            imageView.setVisibility(view.VISIBLE);
                            Log.e("size", selectionArray.size() + " ");
                        }else{
                            selectionArray.add(tempArray.get(position));
                            VersusFragment versusFragment = new VersusFragment();

                            //Bundle
                            Bundle bundle = new Bundle();
                            bundle.putString("plyr1Name", selectionArray.get(0).getUrlName());
                            bundle.putString("plyr1Image", selectionArray.get(0).getUrlImage());
                            bundle.putString("plyr2Name", selectionArray.get(1).getUrlName());
                            bundle.putString("plyr2Image", selectionArray.get(1).getUrlImage());
                            versusFragment.setArguments(bundle);

                            android.support.v4.app.FragmentTransaction fragmentTransaction =
                                    getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.content, versusFragment);
                            fragmentTransaction.commit();
                            Toast.makeText(getContext(), selectionArray.size() + " is full ", Toast.LENGTH_SHORT).show();
                        }
//                        Toast.makeText(getContext(), tempArray.get(position).getName() + " ", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getContext(), tempArray.get(position).getName() + " ", Toast.LENGTH_SHORT).show();
                }
            });
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
