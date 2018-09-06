package com.example.dwi_n.sampleapplication.Interface;

import android.os.Bundle;

import com.example.dwi_n.sampleapplication.Enumeration.FragmentType;
import com.example.dwi_n.sampleapplication.Model.CatDetails;

public class CommonInterface {
    /**
     * This class handle fragment navigation.
     */
    public interface FragmentNavigationUtils {
        /**
         * on screen navigation interface method.
         * @param fragmentType - fragment
         * @param bundle - bundle data.
         */
        void onScreenNavigation(FragmentType fragmentType, Bundle bundle);
    }

    public interface RecyclerListener {
        void onImageSelected(CatDetails catDetails);
    }

}
