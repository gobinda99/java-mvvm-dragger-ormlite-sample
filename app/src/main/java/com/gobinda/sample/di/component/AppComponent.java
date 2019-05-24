package com.gobinda.sample.di.component;

import android.app.Application;


import com.gobinda.sample.App;
import com.gobinda.sample.di.builder.ActivityBuilder;
import com.gobinda.sample.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


/**
 * Application Component
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(App app);

}
