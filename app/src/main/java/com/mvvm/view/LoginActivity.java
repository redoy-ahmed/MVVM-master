package com.mvvm.view;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.mvvm.PeopleApplication;
import com.mvvm.Util;
import com.mvvm.databinding.ActivityLoginBinding;

import com.mvvm.R;
import com.mvvm.viewmodel.LoginViewModel;

import java.util.Observable;
import java.util.Observer;

import io.reactivex.annotations.Nullable;

public class LoginActivity extends AppCompatActivity implements Observer {

    private LoginViewModel loginViewModel;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupObserver(loginViewModel);
        decideNextActivity();
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = new LoginViewModel(getApplicationContext());
        binding.setLoginViewModel(loginViewModel);
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    private void decideNextActivity() {

        boolean isUserLoggedIn = getApplicationContext().getSharedPreferences(Util.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).getBoolean(Util.USER_LOGIN_STATUS, false);

        if (isUserLoggedIn) {
            loginViewModel.openMainActivity();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginViewModel.reset();
    }

    @Override
    public void update(Observable observable, Object data) {
        /*if (observable instanceof LoginViewModel) {
            PeopleAdapter peopleAdapter = (PeopleAdapter) binding.recyclerPeople.getAdapter();
            PeopleViewModel peopleViewModel = (PeopleViewModel) observable;
            if (peopleAdapter != null) {
                peopleAdapter.setPeopleList(peopleViewModel.getPeopleList());
            }
        }*/
    }
}