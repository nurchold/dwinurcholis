package com.example.dwi_n.sampleapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dwi_n.sampleapplication.CommonClass.CommonUtils;
import com.example.dwi_n.sampleapplication.CommonClass.Constant;
import com.example.dwi_n.sampleapplication.CommonClass.PreferenceManager;
import com.example.dwi_n.sampleapplication.Enumeration.FragmentType;
import com.example.dwi_n.sampleapplication.Model.UserAccount;
import com.example.dwi_n.sampleapplication.Model.UserProfile;
import com.example.dwi_n.sampleapplication.R;

import java.util.regex.Pattern;

/**
 * This class handle instance view & controller for login.
 */
public class LoginScreenFragment extends CustomFragment{

    //Static pattern for validation.
    public final static Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{8,30}$");
    public final static Pattern EMAIL_ADDRESS =  Patterns.EMAIL_ADDRESS;

    private EditText mEmail;
    private EditText mPassword;

    //Parse title screen.
    @Override
    protected int getTitleScreen() {
        return 0;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEmail = view.findViewById(R.id.email);
        mPassword = view.findViewById(R.id.password);
        Button mSubmitButton = view.findViewById(R.id.submit_button);

        //Instance on click listener.
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButtonClicked();
            }
        });

        //Hide toolbar only for login screen.
        if (getActivity() != null) {
            TextView textView = getActivity().findViewById(R.id.title_screen);
            textView.setVisibility(View.GONE);
        }
    }

    /**
     * Submit button clicked.
     */
    private void submitButtonClicked() {
        boolean inputValid = isValidEmailAndPassword();
        //validation email & password.
        if (inputValid) {
            boolean userRegistered = isUserRegistered();
            //validation user register.
            if (userRegistered) {
                //Insure data is correct with parse to UserProfile model.
                UserProfile userProfile = CommonUtils.convertStreamToJsonClass(getActivity(), "UserProfile.json", UserProfile.class);
                if (userProfile != null) {
                    //Save to local storage.
                    PreferenceManager.setString(getActivity(), Constant.USER_PROFILE, CommonUtils.convertClassToJson(userProfile));
                    PreferenceManager.setBoolean(getActivity(), Constant.IS_LOGIN, true);
                    //Set navigation to home screen.
                    setFragmentNavigation(FragmentType.HOME_SCREEN, null);
                }
            }
        }
    }

    /**
     * Check user is registered or not.
     * @return status instance of boolean.
     */
    private boolean isUserRegistered() {
        UserAccount userAccount = CommonUtils.convertStreamToJsonClass(getActivity(), "UserAccount.json", UserAccount.class);
        if (userAccount != null) {
            String emailStr = mEmail.getText().toString();
            String passwordStr = mPassword.getText().toString();
            return userAccount.getEmail().equalsIgnoreCase(emailStr) && userAccount.getPassword().equalsIgnoreCase(passwordStr);
        }
        return false;
    }

    /**
     * Check mandatory field for login.
     * @return input email & password instance of boolean.
     */
    private boolean isValidEmailAndPassword() {
        String emailStr = mEmail.getText().toString();
        String passwordStr = mPassword.getText().toString();

        //check email & password empty or not.
        if (CommonUtils.isNotEmptyTextOrNull(emailStr) && CommonUtils.isNotEmptyTextOrNull(passwordStr)) {
            boolean validEmail = CommonUtils.isPatternValid(emailStr, EMAIL_ADDRESS);
            boolean validPassword = CommonUtils.isPatternValid(passwordStr, PASSWORD_PATTERN);
            //check email & password pattern is valid
            return validEmail && validPassword;
        }
        return false;
    }
}
