package com.gobinda.test.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.gobinda.test.data.AppDataManager;
import com.gobinda.test.data.DataManager;
import com.gobinda.test.data.local.db.dao.AppDbHelper;
import com.gobinda.test.data.local.db.dao.ORMLiteHelper;
import com.gobinda.test.utils.rx.SchedulerProvider;
import com.gobinda.test.utils.rx.AppSchedulerProvider;
import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Application Module class used in Dependency Injection
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


    @Provides
    @Singleton
    DataManager provideDataManager(Context context, AppDbHelper appDbHelper) {
        return new AppDataManager(context, appDbHelper);
    }

    @Provides
    @Singleton
    ORMLiteHelper provideAppDatabase(Context context) {
        return OpenHelperManager.getHelper(context, ORMLiteHelper.class);
    }
}
