
package com.mvvm;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.mvvm.api.APIEndPoint;
import com.mvvm.api.PeopleService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class PeopleApplication extends MultiDexApplication {

    private PeopleService peopleService;
    private Scheduler scheduler;

    private static PeopleApplication get(Context context) {
        return (PeopleApplication) context.getApplicationContext();
    }

    public static PeopleApplication create(Context context) {
        return PeopleApplication.get(context);
    }

    public PeopleService getPeopleService() {
        if (peopleService == null) {
            peopleService = APIEndPoint.create();
        }

        return peopleService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }
        return scheduler;
    }
}
