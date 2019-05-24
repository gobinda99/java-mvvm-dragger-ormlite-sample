package com.gobinda.test.di.builder;

import com.gobinda.test.ui.registerLogin.RegisterLoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract RegisterLoginActivity bindLoginActivity();

}
