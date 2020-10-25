package com.mvvm.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mvvm.Util;
import com.mvvm.db.UserRepository;
import com.mvvm.model.User;
import com.mvvm.view.PeopleActivity;

import java.util.List;
import java.util.Observable;

import io.reactivex.disposables.CompositeDisposable;

public class LoginViewModel extends Observable {

    private UserRepository repository;
    private SharedPreferences sharedpreferences;

    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LoginViewModel(@NonNull Context context) {
        this.context = context;
        repository = new UserRepository(context);
        sharedpreferences = context.getSharedPreferences(Util.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void reset() {
        compositeDisposable.dispose();
        compositeDisposable = null;
        context = null;
    }

    public void onServerLoginClick(String userName, String password) {
        AsyncTask.execute(() -> {
            User user = repository.getUser(userName, password);

            if (user == null) {
                Toast.makeText(context, "Invalid user name or password", Toast.LENGTH_LONG).show();
            } else {
                sharedpreferences.edit().putBoolean(Util.USER_LOGIN_STATUS, true).apply();
                openMainActivity();
            }
        });
    }

    public void decideNextActivity() {
        boolean isUserLoggedIn = sharedpreferences.getBoolean(Util.USER_LOGIN_STATUS, false);

        if (isUserLoggedIn) {
            openMainActivity();
        }
    }

    public void openMainActivity() {
        Intent intent = new Intent(context, PeopleActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
