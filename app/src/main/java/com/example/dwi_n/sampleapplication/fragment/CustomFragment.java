package com.example.dwi_n.sampleapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.example.dwi_n.sampleapplication.Enumeration.FragmentType;
import com.example.dwi_n.sampleapplication.Interface.CommonInterface;
import com.example.dwi_n.sampleapplication.R;

public abstract class CustomFragment extends Fragment {

    private CommonInterface.FragmentNavigationUtils mFragmentNavigation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitleScreen();
    }

    /**
     * Set header title scree.
     */
    private void setTitleScreen() {
        int titleId = getTitleScreen();
        boolean isShow = titleId > 0;
        TextView textView = getActivity().findViewById(R.id.title_screen);
        textView.setText(isShow ? getActivity().getResources().getString(titleId) : "");
        textView.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void setNavigationData(CommonInterface.FragmentNavigationUtils fragmentNavigationUtils) {
        mFragmentNavigation = fragmentNavigationUtils;
    }

    /**
     * Set navigation fragment listener.
     * @param fragmentType - fragment navigation type.
     * @param bundle - bundle data.
     */
    protected void setFragmentNavigation(FragmentType fragmentType, Bundle bundle){
        if(mFragmentNavigation != null){
            mFragmentNavigation.onScreenNavigation(fragmentType, bundle);
        }
    }

    /**
     * Returns the string for the activity title
     * that should be shown for the fragment.
     *
     * @return - activity title
     */
    protected abstract int getTitleScreen();

}
