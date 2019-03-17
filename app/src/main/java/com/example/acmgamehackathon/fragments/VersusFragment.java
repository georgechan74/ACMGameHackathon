package com.example.acmgamehackathon.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.acmgamehackathon.R;
import com.example.acmgamehackathon.menu.MenuItems;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class VersusFragment extends Fragment {

    public VersusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_versus, container, false);
        Bundle bundle = getArguments();

        if (bundle != null) {
            String bundleImagePlyr1 = bundle.getString("plyr1Image");
            String bundleNamePlyr1 = bundle.getString("plyr1Name");
            String bundleImagePlyr2 = bundle.getString("plyr2Image");
            String bundleNamePlyr2 = bundle.getString("plyr2Name");

            Log.e("Test", bundleImagePlyr1 + "");

            ImageView img1 = (ImageView) rootView.findViewById(R.id.plyr1_image);
            ImageView img2 = (ImageView) rootView.findViewById(R.id.plyr1_name);
            ImageView img3 = (ImageView) rootView.findViewById(R.id.plyr2_image);
            ImageView img4 = (ImageView) rootView.findViewById(R.id.plyr2_name);

            Picasso.with(container.getContext()).load(bundleImagePlyr1).into(img1);
            Picasso.with(container.getContext()).load(bundleNamePlyr1).into(img2);
            Picasso.with(container.getContext()).load(bundleImagePlyr2).into(img3);
            Picasso.with(container.getContext()).load(bundleNamePlyr2).into(img4);

        }
        return inflater.inflate(R.layout.fragment_versus, container, false);
    }

}
