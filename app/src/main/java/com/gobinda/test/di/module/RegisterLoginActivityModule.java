package com.gobinda.test.di.module;


import com.gobinda.test.data.DataManager;
import com.gobinda.test.ui.registerLogin.RegisterLoginViewModel;
import com.gobinda.test.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Register Login Activity Module
 */
@Module
public class RegisterLoginActivityModule {

    @Provides
    RegisterLoginViewModel provideLoginViewModel(DataManager dataManager,
                                                 SchedulerProvider schedulerProvider) {
        return new RegisterLoginViewModel(dataManager, schedulerProvider);
    }

}
