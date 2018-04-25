package com.gobinda.test.di.builder;

import com.gobinda.test.ui.registerLogin.RegisterLoginActivity;
import com.gobinda.test.di.module.RegisterLoginActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = RegisterLoginActivityModule.class)
    abstract RegisterLoginActivity bindLoginActivity();

}
