package com.example.dwi_n.sampleapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dwi_n.sampleapplication.CommonClass.Constant;
import com.example.dwi_n.sampleapplication.CommonClass.PreferenceManager;
import com.example.dwi_n.sampleapplication.Enumeration.FragmentType;
import com.example.dwi_n.sampleapplication.Interface.CommonInterface;
import com.example.dwi_n.sampleapplication.fragment.DetailsScreenFragment;
import com.example.dwi_n.sampleapplication.fragment.HomeScreenFragment;
import com.example.dwi_n.sampleapplication.fragment.LoginScreenFragment;

public class MainActivity extends AppCompatActivity implements CommonInterface.FragmentNavigationUtils {

    private FragmentType mCurrentFragmentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isLogin = PreferenceManager.getBoolean(this, Constant.IS_LOGIN);
        if (isLogin) {
            openHomeScreenFragment();
        } else {
            openLoginScreenFragment();
        }
    }

    @Override
    public void onScreenNavigation(FragmentType fragmentType, Bundle bundle) {
        switch (fragmentType) {
            case LOGIN_SCREEN:
                openLoginScreenFragment();
                break;
            case HOME_SCREEN:
                openHomeScreenFragment();
                break;
            case DETAILS_SCREEN:
                openDetailsScreenFragment(bundle);
                break;
        }
    }

    private void openLoginScreenFragment() {
        mCurrentFragmentType = FragmentType.LOGIN_SCREEN;
        LoginScreenFragment loginScreenFragment = getLoginScreenFragment();
        if (loginScreenFragment == null) {
            loginScreenFragment = new LoginScreenFragment();
        }
        loginScreenFragment.setNavigationData(this);
        makeFragmentTransaction(loginScreenFragment, false);
    }

    private void openHomeScreenFragment() {
        mCurrentFragmentType = FragmentType.HOME_SCREEN;
        HomeScreenFragment homeScreenFragment = getHomeScreenFragment();
        if (homeScreenFragment == null) {
            homeScreenFragment = new HomeScreenFragment();
        }
        homeScreenFragment.setNavigationData(this);
        makeFragmentTransaction(homeScreenFragment, false);
    }



    private void openDetailsScreenFragment(Bundle bundle) {
        mCurrentFragmentType = FragmentType.DETAILS_SCREEN;
        DetailsScreenFragment detailsScreenFragment = getDetailsScreenFragment();
        if (detailsScreenFragment == null) {
            detailsScreenFragment = new DetailsScreenFragment();
        }
        if(detailsScreenFragment.getArguments() != null){
            detailsScreenFragment.getArguments().putAll(bundle);
        }else{
            detailsScreenFragment.setArguments(bundle);
        }
        detailsScreenFragment.setNavigationData(this);
        makeFragmentTransaction(detailsScreenFragment, true);
    }



    /**
     * Make fragment transaction
     * @param aFragment - fragment name
     * @param shouldAdd - should add to back stack.
     */
    private void makeFragmentTransaction(Fragment aFragment, boolean shouldAdd) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, aFragment, mCurrentFragmentType.name());
        if(shouldAdd) {
            fragmentTransaction.addToBackStack(mCurrentFragmentType.name());
        }
        fragmentTransaction.commit();
    }

    /**
     * Returns the fragment by type
     * @return - instance of fragment
     */
    private Fragment getFragment() {
        return getSupportFragmentManager().findFragmentByTag(mCurrentFragmentType.name());
    }

    /**
     * Returns the login screen fragment
     * @return - instance of discover fragment
     */
    private LoginScreenFragment getLoginScreenFragment() {
        Fragment fragment = getFragment();
        if (fragment != null && (fragment instanceof LoginScreenFragment)) {
            return (LoginScreenFragment) fragment;
        }
        return null;
    }

    /**
     * Returns the home screen fragment
     * @return - instance of discover fragment
     */
    private HomeScreenFragment getHomeScreenFragment() {
        Fragment fragment = getFragment();
        if (fragment != null && (fragment instanceof HomeScreenFragment)) {
            return (HomeScreenFragment) fragment;
        }
        return null;
    }

    /**
     * Returns the details screen fragment
     * @return - instance of discover fragment
     */
    private DetailsScreenFragment getDetailsScreenFragment() {
        Fragment fragment = getFragment();
        if (fragment != null && (fragment instanceof DetailsScreenFragment)) {
            return (DetailsScreenFragment) fragment;
        }
        return null;
    }
}
