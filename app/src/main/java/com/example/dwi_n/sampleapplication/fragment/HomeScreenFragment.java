package com.example.dwi_n.sampleapplication.fragment;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dwi_n.sampleapplication.Adapter.CatImagesAdapter;
import com.example.dwi_n.sampleapplication.CommonClass.CommonUtils;
import com.example.dwi_n.sampleapplication.CommonClass.Constant;
import com.example.dwi_n.sampleapplication.CommonClass.PreferenceManager;
import com.example.dwi_n.sampleapplication.Enumeration.FragmentType;
import com.example.dwi_n.sampleapplication.Interface.CommonInterface;
import com.example.dwi_n.sampleapplication.Model.CatDetails;
import com.example.dwi_n.sampleapplication.Model.CatImageList;
import com.example.dwi_n.sampleapplication.Model.UserProfile;
import com.example.dwi_n.sampleapplication.R;
import com.squareup.picasso.Picasso;

/**
 * This class handle view & controller home screen or profile screen
 */
public class HomeScreenFragment extends CustomFragment implements View.OnClickListener, CommonInterface.RecyclerListener {
    //Tab content.
    private LinearLayout mCatImageListContent;
    private LinearLayout mProfileContent;
    //Image list view variable.
    private RecyclerView mImageListView;
    //Profile view variable.
    private ImageView mPhotoProfile;
    private TextView mUserName;
    private TextView mGender;
    private TextView mPhoneNumber;
    //Tab content.
    private RelativeLayout mHomeTab;
    private RelativeLayout mProfileTab;
    //Data.
    private UserProfile mUserProfile;
    private CatImageList mCatImageList;

    @Override
    protected int getTitleScreen() {
        return R.string.title_screen;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Content Tab
        mCatImageListContent = view.findViewById(R.id.cat_list_screen);
        mProfileContent = view.findViewById(R.id.profile_screen);

        //Content in cat image list.
        mImageListView = view.findViewById(R.id.image_list_content);

        //Content in profile tab.
        mPhotoProfile = view.findViewById(R.id.photo_user_profile);
        mUserName = view.findViewById(R.id.user_name);
        mGender = view.findViewById(R.id.gender);
        mPhoneNumber = view.findViewById(R.id.phone_number);

        //button Tab.
        mHomeTab = view.findViewById(R.id.home_tab);
        mProfileTab = view.findViewById(R.id.profile_tab);

        setClickListener();
        prepareAllData();
        mappingAllData();

        //Init home screen view.
        showCatImageListOrProfile(true);
    }

    /**
     * Mapping all data base on tab screen.
     */
    private void mappingAllData() {
        mappingHomeScreenData();
        mappingProfileData();
    }

    /**
     * Mapping home screen data.
     */
    private void mappingHomeScreenData() {
        if (mCatImageList != null) {
            CatImagesAdapter mCatImagesAdapter = new CatImagesAdapter(getActivity(), mCatImageList.getCatImageList());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            mCatImagesAdapter.setCatImageListListener(this);
            mImageListView.setLayoutManager(linearLayoutManager);
            mImageListView.setAdapter(mCatImagesAdapter);
        }
    }

    /**
     * Mapping profile data.
     */
    private void mappingProfileData() {
        if (mUserProfile != null) {
            mUserName.setText(mUserProfile.getName());
            mGender.setText(mUserProfile.getGender());
            mPhoneNumber.setText(mUserProfile.getPhone());
            setPhotoProfile();
        }
    }

    /**
     * Prepare all data require. for home screen & profile screen.
     */
    private void prepareAllData() {
        //Home screen data.
        mCatImageList = getHomeScreenData();
        //Profile data.
        mUserProfile = getUserProfileData();
    }

    /**
     * Get home screen data from mock.
     * @return - cat list.
     */
    private CatImageList getHomeScreenData() {
        //Get from mock data.
        return CommonUtils.convertStreamToJsonClass(getActivity(), "CatImageList.json", CatImageList.class);
    }

    /**
     * Get user profile data from local storage.
     * @return - user profile obj.
     */
    private UserProfile getUserProfileData() {
        if (getActivity() != null) {
            String profileStr = PreferenceManager.getString(getActivity(), Constant.USER_PROFILE);
            return CommonUtils.convertJsonToClass(profileStr, UserProfile.class);
        }
        return new UserProfile();
    }

    /**
     * Set onclick listener.
     */
    private void setClickListener() {
        mHomeTab.setOnClickListener(this);
        mProfileTab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_tab :
                showCatImageListOrProfile(true);
                break;
            case R.id.profile_tab:
                showCatImageListOrProfile(false);
                break;
            default:
                break;
        }
    }

    /**
     * Change screen by tap tab option home & profile.
     * @param isHomeTabclick - boolean.
     */
    private void showCatImageListOrProfile(boolean isHomeTabclick) {
        mCatImageListContent.setVisibility(isHomeTabclick ? View.VISIBLE : View.GONE);
        mProfileContent.setVisibility(isHomeTabclick ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onImageSelected(CatDetails catDetails) {
        if (catDetails != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.IMAGE_DETAILS_DATA , catDetails);
            setFragmentNavigation(FragmentType.DETAILS_SCREEN, bundle);
        }
    }

    /**
     * Set photo profile to view.
     * Photo save in local storage.
     * Call image by name from mock data.
     */
    private void setPhotoProfile() {
        if (getActivity() != null) {
            Resources resources = getActivity().getResources();
            String imageName = mUserProfile.getPhotoProfile().replace(".jpeg", "");
            final int resourceId = resources.getIdentifier(imageName, "drawable", getActivity().getPackageName());
            Picasso.with(getActivity()).load(resourceId).fit().into(mPhotoProfile);
        }
    }
}
