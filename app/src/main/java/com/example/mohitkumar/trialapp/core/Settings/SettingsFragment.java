package com.example.mohitkumar.trialapp.core.Settings;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mohitkumar.trialapp.R;
import com.example.mohitkumar.trialapp.Util.Constants;
import com.example.mohitkumar.trialapp.Util.PrefManager;
import com.example.mohitkumar.trialapp.databinding.SettingsBinding;
import static com.example.mohitkumar.trialapp.MainApplication.TAG;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements ISettingsView{


    public SettingsFragment() {
        // Required empty public constructor
    }

    ISettingsPresenter presenter;
    SettingsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        presenter = new SettingsPresenter();
        presenter.onAttach(this);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.bio.setText(PrefManager.getString(Constants.BIO, ""));
        binding.email.setText(PrefManager.getString(Constants.EMAIL,""));
        binding.userName.setText(PrefManager.getString(Constants.USERNAME, ""));

        binding.updateSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
    }

    private void logOut() {

    }

    private void updateProfile() {
        JSONObject object = new JSONObject();
        JSONObject item = new JSONObject();
        try {
            object.put("user", item);
            if (!TextUtils.isEmpty(binding.newPassword.getText())) {
                item.put("password", binding.newPassword.getText().toString());
            }
            if (!TextUtils.isEmpty(binding.email.getText()) &&
                        !binding.email.getText().toString().equals(PrefManager.getString(Constants.EMAIL, ""))) {
                item.put("email", binding.email.getText().toString());
            }
            if (!TextUtils.isEmpty(binding.bio.getText()) &&
                    !binding.bio.getText().toString().equals(PrefManager.getString(Constants.BIO, "")))
                item.put("bio",binding.bio.getText().toString());
            if (!TextUtils.isEmpty(binding.userName.getText()) &&
                    !binding.userName.getText().toString().equals(PrefManager.getString(Constants.USERNAME, ""))) {
                item.put("username", binding.userName.getText().toString());
            }
            if (!TextUtils.isEmpty(binding.urlImage.getText()))
                item.put("image",binding.urlImage.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, object.toString());

        presenter.updateSettings(PrefManager.getString(Constants.USERNAME, ""), object.toString());
    }

    @Override
    public void onSettingsUpdateSuccess(String result) {
        Toast.makeText(getActivity(), "Settings Updated", Toast.LENGTH_LONG).show();
        Log.d(TAG, result);
    }

    @Override
    public void onSettingsUpdateError(String message) {
        Toast.makeText(getActivity(), "Unable to update your settings", Toast.LENGTH_LONG).show();
    }
}
