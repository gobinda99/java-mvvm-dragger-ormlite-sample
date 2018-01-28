package com.gobinda.test;

import android.app.Activity;
import android.app.Application;

import com.gobinda.test.data.local.db.dao.DaoUtils;
import com.gobinda.test.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

/**
 * Application Class
 */

public class App  extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mInjector;

    @Inject DaoUtils mDaoUtils;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }

    @Override
    public void onTerminate() {
        mDaoUtils.shutdown();
        super.onTerminate();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return mInjector;
    }

}
