/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

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
